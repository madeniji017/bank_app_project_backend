package bank_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;
@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user",
        uniqueConstraints = {@UniqueConstraint
                (columnNames = {"email", "phoneNumber"})
        })

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @NotBlank
    @Length(min = 3)
    private String firstName;

    private String middleName;
    @NotBlank
    @Length(min = 3)
    private String lastName;

    @NotBlank
    private String BVN;

    @Email
    @NotEmpty(message = "Invalid email address")
    private String email;

    @NotBlank
    private String phoneNumber;
    private String address;

    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private LocalDate dateOfBirth;

    @Transient
    private Integer acctType;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


    @OneToMany(mappedBy = "user")
    @Getter(AccessLevel.NONE)
    //@Setter(AccessLevel.NONE)
    private List<Account> accounts;

    public User(String firstName, String lastName, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }


    @JsonManagedReference
    public List<Account> getAccounts() {
        return accounts;
    }
}
