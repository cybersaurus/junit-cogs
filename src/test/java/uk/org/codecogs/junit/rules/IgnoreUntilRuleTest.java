package uk.org.codecogs.junit.rules;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import uk.org.codecogs.junit.annotation.IgnoreUntil;

import java.time.LocalDate;

public class IgnoreUntilRuleTest {
    @Rule
    public final IgnoreUntilRule rule = new IgnoreUntilRule(LocalDate.parse("2099-12-31"));

    @IgnoreUntil(expiryDate="2099-12-31", reason="Only run in next century.")
    @Test
    public void shouldIgnoreTestUntilYear3000() {
        Assert.fail("Test should have been ignored!");
    }

    @IgnoreUntil(expiryDate="2000-01-01", reason="Don't run until the year 2000.")
    @Test
    public void shouldRunTestAfterYear2000() {
    }


    @Ignore // Unignore this line to verify the test correctly fails due to the date being too far in the future.
    @IgnoreUntil(expiryDate="3000-01-01", reason="Only run in next millennium")
    @Test
    public void shouldFailTestAsIgnoreUntilDateIsTooLarge() {
    }

    @Ignore // Unignore this line to verify the test correctly fails due to the expiryAction being FAIL.
    @IgnoreUntil(expiryDate="2000-01-01", reason="Fail the test after the year 2000.", expiryAction= IgnoreUntil.ExpiryAction.FAIL)
    @Test
    public void shouldFailTestAfterYear2000AsExpiryActionIsFail() {
        System.out.println("Test should be automatically failed before this line is executed.");
    }
}