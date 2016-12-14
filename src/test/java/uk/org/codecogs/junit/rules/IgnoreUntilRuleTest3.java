package uk.org.codecogs.junit.rules;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import uk.org.codecogs.datetime.Clocks;
import uk.org.codecogs.junit.annotation.IgnoreUntil;

import java.time.LocalDate;

// Tests based on setting the system Clock to a fixed point in time
public class IgnoreUntilRuleTest3 {
    @Rule
    public final IgnoreUntilRule rule = new IgnoreUntilRule(LocalDate.parse("2099-12-31"));

    @Before
    public void setUp() {
        Clocks.useFixedClockAt(LocalDate.parse("2016-12-14").atTime(14, 37));
    }

    @After
    public void tearDown() {
        Clocks.useSystenDefaultClock();
    }

    @IgnoreUntil(expiryDate="2016-12-13", reason="Run test - system clock frozen to past expiry date.")
    @Test
    public void shouldRunTestAsTimeFrozenToThePastExpiryDate() {
    }

    @IgnoreUntil(expiryDate="2016-12-14", reason="Run test - system clock frozen to current expiry date.")
    @Test
    public void shouldRunTestAsTimeFrozenToCurrentExpiryDate() {
    }

    @IgnoreUntil(expiryDate="2016-12-15", reason="Ignore test - system clock frozen to future expiry date.")
    @Test
    public void shouldIgnoreTestAsTimeFrozenToTheExpiryDateAfterSystemClock() {
        Assert.fail("Test should have been ignored!");
    }
}
