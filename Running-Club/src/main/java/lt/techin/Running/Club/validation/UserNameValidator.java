package lt.techin.Running.Club.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserNameValidator implements ConstraintValidator<UserName, String> {


  @Override
  public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
    return username != null && username.matches("^[a-z0-9]+$");
  }
}
