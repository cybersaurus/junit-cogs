package uk.org.codecogs.hamcrest;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class MatcherCogsTest {
    @Test
    public void featureShouldMatchAllFunctionLambdas() {
        final String matchable = "Hello, World!";

        Assert.assertThat(matchable, CoreMatchers.allOf(
            MatcherCogs.feature(() -> matchable.toString(), equalTo("Hello, World!")),
            MatcherCogs.feature(() -> matchable.length(), equalTo(13))
        ));
    }

    @Test
    public void featureShouldMatchAllSupplierLambas() {
        Assert.assertThat("Hello, World!", CoreMatchers.allOf(
            MatcherCogs.feature(str -> str.toString(), equalTo("Hello, World!")),
            MatcherCogs.feature(str -> str.length(), equalTo(13))
        ));
    }
}