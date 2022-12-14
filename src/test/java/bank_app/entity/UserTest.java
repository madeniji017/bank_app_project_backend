package bank_app.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    @Autowired
    Validator validator;

    @Test
    public void whenRequiredFieldNotEmpty_thenNoConstraintValidation() {
        User user = new User("Sanwo", "Eko", "seko@gmail.com",
                "Government house");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    @Test
    public void whenRequiredFieldEmpty_thenConstraintValidation() {
        User user = new User("", "Eko", "",
                "Government house");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(2, violations.size() == 2);
    }

    private void asserTrue(boolean b) {
    }

}