package bank_app.entity;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Login {

    private String email;

    private String password;

}
