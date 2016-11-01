package uk.org.codecogs.junit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface IgnoreUntil {
    public String expiryDate();
    public ExpiryAction expiryAction() default ExpiryAction.RUN;
    public String reason();

    public enum ExpiryAction {
        RUN,
        FAIL;
    }
}
