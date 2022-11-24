package bank_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
@Component
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private char gender;

    private String userName;

    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    //@JsonBackReference
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

    public User(String firstName, String lastName, String emailAddress, String address, char gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.gender = gender;
    }
}
