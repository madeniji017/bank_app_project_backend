package bank_app.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String acctFirstName;
    private String acctLastName;
    private String acctStatus;

    @Id
    private Long acctNumber;
    private Double acctBalance;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private User user;

    public Account(User user,
                   Long acctNumber) {
        this.acctFirstName = user.getFirstName();
        this.acctLastName = user.getLastName();
        this.acctNumber = acctNumber;
        this.acctBalance = 0.00;

    }

}
