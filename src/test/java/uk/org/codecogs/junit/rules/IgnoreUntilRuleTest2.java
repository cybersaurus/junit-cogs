package uk.org.codecogs.junit.rules;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import uk.org.codecogs.junit.annotation.IgnoreUntil;

import java.time.LocalDate;

@IgnoreUntil(expiryDate="2099-12-31", reason="Ignore all the tests in this class.")
public class IgnoreUntilRuleTest2 {
    /** Example of using the IgnoreUntilRule to ignore all tests at the class level. */
    @ClassRule
    public static final IgnoreUntilRule rule = new IgnoreUntilRule(LocalDate.parse("2099-12-31"));

    @Test
    public void shouldIgnoreTestUntilYear3000() {
        Assert.fail("Test should have been ignored!");
    }
}
