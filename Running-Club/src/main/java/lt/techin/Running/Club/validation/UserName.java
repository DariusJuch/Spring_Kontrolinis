package lt.techin.Running.Club.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserNameValidator.class)
public @interface UserName {

  String message() default "Must be lowercase and numbers allowed";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};


}
