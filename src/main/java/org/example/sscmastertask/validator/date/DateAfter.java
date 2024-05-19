package org.example.sscmastertask.validator.date;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateAfterValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateAfter {
    String message() default "Date must be after the specified date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String date();
}
