package bank_app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Component
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user",
        uniqueConstraints = @UniqueConstraint(
                name = "email",
                columnNames = "email"
        ))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private String phoneNumber;
    private String address;

    private String username;

    private String password;

    private String confirmPassword;

    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Account> accounts;

    public User(String firstName, String lastName, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }
}
