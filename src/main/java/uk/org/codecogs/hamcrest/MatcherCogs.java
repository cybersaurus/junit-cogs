package uk.org.codecogs.hamcrest;

import org.hamcrest.FeatureMatcher;

import java.util.function.Function;
import java.util.function.Supplier;

public class MatcherCogs {
    public static <F, T> org.hamcrest.Matcher<F> feature(Function<F, T> function, org.hamcrest.Matcher<T> expected, String description) {
        return feature(function, expected, description, "");
    }

    public static <F, T> org.hamcrest.Matcher<F> feature(Function<F, T> function, org.hamcrest.Matcher<T> expected) {
        return feature(function, expected, "");
    }

    public static <F, T> org.hamcrest.Matcher<F> feature(Function<F, T> function, org.hamcrest.Matcher<T> expected, String featureDescription, String featureName) {
        return new FeatureMatcher<F, T>(expected, featureDescription, featureName) {
            @Override
            protected T featureValueOf(F actual) {
                return function.apply(actual);
            }
        };
    }

    public static final <F, T> org.hamcrest.Matcher<F> feature(Supplier<T> supplier, org.hamcrest.Matcher<T> expected) {
        return feature(ignore -> supplier.get(), expected, "");
    }
}
