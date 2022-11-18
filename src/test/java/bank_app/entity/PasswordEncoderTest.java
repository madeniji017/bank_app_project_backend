package bank_app.entity;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncoderTest {

    @Test
    void bCryptPasswordEncoder() {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String testPassword = "password";
        String codedPassword = passwordEncoder.encode(testPassword);
        System.out.println(codedPassword);

        boolean confirmPassword = passwordEncoder.matches("password", codedPassword);
        assertTrue(confirmPassword);

    }
}