package uk.org.codecogs.junit.rules;

import org.junit.Assume;
import org.junit.internal.runners.statements.Fail;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import uk.org.codecogs.junit.annotation.IgnoreUntil;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public final class IgnoreUntilRule implements TestRule {
    private static final int MAX_IGNORE_DAYS_AHEAD = 7;
    private final ChronoLocalDate maxIgnoreDateInclusive;

    public IgnoreUntilRule(ChronoLocalDate maxIgnoreDateInclusive) {
        this.maxIgnoreDateInclusive = maxIgnoreDateInclusive;
    }

    public IgnoreUntilRule(int daysToIgnore) {
        this.maxIgnoreDateInclusive = LocalDate.now().plusDays(daysToIgnore);
    }

    public IgnoreUntilRule() {
        this(MAX_IGNORE_DAYS_AHEAD);
    }

    @Override
    public Statement apply(Statement base, Description description) {
        Statement result = base;

        final IgnoreUntil annotation = description.getAnnotation(IgnoreUntil.class);

        // If there is no IgnoreUntil annotation for this method then let it proceed as usual.
        if (annotation == null) return result;

        final String expiryDateStr = annotation.expiryDate();

        final LocalDate expiryDate = LocalDate.parse(expiryDateStr);

        if (expiryDate.isAfter(maxIgnoreDateInclusive)) {
            // IgnoreUntil expiry date is too far in the future.
            result = new Fail(new InvalidIgnoreUntilDateException("Maximum IgnoreUntil expiry date is is :"+ maxIgnoreDateInclusive));
        } else if (expiryDate.plusDays(1).isBefore(LocalDate.now())) {
            // IgnoreUntil date has expired.
            final IgnoreUntil.ExpiryAction expiryAction = annotation.expiryAction() != null ? annotation.expiryAction() : IgnoreUntil.ExpiryAction.RUN;

            if (IgnoreUntil.ExpiryAction.FAIL.equals(expiryAction)) {
                result = new Fail(new ExpiredIgnoreUntilDateException("IgnoreUntil date has expired: ["+expiryDateStr+ "] for reason: "+annotation.reason()));
            } else {
                // Leave the original statement unaltered. i.e. run the test as normal.
            }
        } else {
            // IgnoreUntil date is fine, so ignore the test.
            result = new IgnoredStatement();
        }

        return result;
    }

    private static final class IgnoredStatement extends Statement {
        @Override public void evaluate() throws Throwable { Assume.assumeTrue(false); }
    }

    private final class ExpiredIgnoreUntilDateException extends Exception {
        private ExpiredIgnoreUntilDateException(String message) {
            super(message);
        }
    }

    private final class InvalidIgnoreUntilDateException extends Exception {
        private InvalidIgnoreUntilDateException(String message) {
            super(message);
        }
    }
}
