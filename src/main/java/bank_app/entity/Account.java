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

    @Setter(AccessLevel.NONE)
    @Id
    private Integer acctNumber;
    private Double acctBalance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Account(String acctFirstName,
                   String acctLastName,
                   Integer acctNumber,
                   Double acctBalance) {
        this.acctFirstName = user.getFirstName();
        this.acctLastName = user.getLastName();
        this.acctNumber = acctNumber;
        this.acctBalance = acctBalance;

    }

}
