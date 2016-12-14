# JUnit Extensions
Java 8 based extensions to the [JUnit 4](http://junit.org/junit4/) framework.

## Rules
Implementations of `org.junit.rules.TestRule`. For background info, see: [Rules](https://github.com/junit-team/junit4/wiki/Rules).

### IgnoreUntilRule
This rule is similar to JUnit's `@ignore` annotation, but allows tests to be temporarily ignored until a specified future date.
Once the future date is reached, suppression of the tests will cease. The tests will then be executed in the same way as other tests.

This annotation is designed for scenarios such as intergration tests which depend on an external system that is temporarily unavailable.
Rather than just ignoring the tests using the standard `@ignore` annotation (which may lead to the tests being forgotten about), the tests
can be temporarily ignored until a date when the external system dependency is expected to be functional again.
At this time the test will then run as normal.

Example method level usage:
```java
public class MarsColonyConnectionTest {
    @Rule
    public final IgnoreUntilRule rule = new IgnoreUntilRule();
    
    @IgnoreUntil(expiryDate="2100-01-01", reason="Ignore test until Mars colony is established in next century.")
    @Test
    public void shouldConnectToMarsColony() {
        ...
    }
}
```

Example class level usage:
```java
@IgnoreUntil(expiryDate="2100-01-01", reason="Ignore test until Mars colony is established in next century.")
public class MarsColonyConnectionTest2 {
    @ClassRule
    public static final IgnoreUntilRule rule = new IgnoreUntilRule();
    
    @Test
    public void shouldConnectToMarsColony() {
        ...
    }
}
```