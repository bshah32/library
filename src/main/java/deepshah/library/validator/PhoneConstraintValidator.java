package deepshah.library.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneConstraintValidator implements ConstraintValidator<Phone, String> {

        
        @Override
        public void initialize(Phone String) { }

        @Override
        public boolean isValid(String phoneField, ConstraintValidatorContext cxt) {
                if(phoneField == null) {
                        return false;
                }
                return phoneField.matches("^\\(?(\\d{3})\\)?[ ]?(\\d{3})[-]?(\\d{4})$");
        }

}