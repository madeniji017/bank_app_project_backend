package BnkApp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;
    private String firstName;
    private String lastName;
    private String email;
    private char gender;
    private String address;

    @OneToOne /*(
            mappedBy = "customer",
            fetch = FetchType.LAZY)*/
    @JoinColumn(
            name = "acctNum"
    )
    private Account account;

    public Customer(String firstName, String lastName, String email, char gender, String address) {
        this.firstName = account.getAcctFirstName();
        this.lastName = account.getAcctLastName();
        this.email = email;
        this.gender = gender;
        this.address = address;
    }



}
