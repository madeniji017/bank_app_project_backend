package bank_app.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.xml.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Autowired
    Validator validator;

    @Test
    void whenRequiredFieldNotEmpty_thenNoConstraintValidation() {
       /* User user = new User("Sanwo", "Eko", "seko@gmail.com",
                "Government house");
        Set<ConstraintViolation<User>> violations = validator.validate();
        assertEquals(0, violations.size());

        */
    }

    @Test
    void whenRequiredFieldEmpty_thenConstraintValidation() {
      /*  User user = new User("", "Eko", "",
                "Government house");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(2, violations.size() == 2);

       */
    }

    private void asserTrue(boolean b) {
    }

}