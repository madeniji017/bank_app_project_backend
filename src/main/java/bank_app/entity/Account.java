package bank_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account",
        uniqueConstraints = @UniqueConstraint(
                name = "acctNumber",
                columnNames = "acctNumber"
        ))
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String acctFirstName;
    private String acctLastName;

    private String acctType;
    private Long acctNumber;
    private Double acctBalance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter(AccessLevel.NONE)
    private User user;

    @JsonBackReference
    public User getUser() {
        return user;
    }

    public Account(User user,
                   Long acctNumber) {
        this.acctFirstName = user.getFirstName();
        this.acctLastName = user.getLastName();
        this.acctNumber = acctNumber;
        this.acctBalance = 0.00;

    }

}
