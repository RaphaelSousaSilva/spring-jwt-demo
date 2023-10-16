package br.com.raph.demo.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Invalid password: must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, and one special character.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}