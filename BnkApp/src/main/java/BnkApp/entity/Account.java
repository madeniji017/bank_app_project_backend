package BnkApp.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "Account")
public class Account {

    /*GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;*/
    private String acctFirstName;
    private String acctLastName;
    @Id
    private Long acctNum;
    private double acctBal;

   @OneToOne
    private Customer customer;


    public Account(String acctFirstName, String acctLastName, Long acctNum, double acctBal) {
        this.acctFirstName = acctFirstName;
        this.acctLastName = acctLastName;
        this.acctNum = acctNum;
        this.acctBal = acctBal;
    }

    public String getAcctFirstName() {
        return acctFirstName;
    }

    public void setAcctFirstName(String acctFirstName) {
        this.acctFirstName = acctFirstName;
    }

    public String getAcctLastName() {
        return acctLastName;
    }

    public void setAcctLastName(String acctLastName) {
        this.acctLastName = acctLastName;
    }

    public Long getAcctNum() {
        return acctNum;
    }

    public double getAcctBal() {
        return acctBal;
    }

    public void setAcctBal(double acctBal) {

        this.acctBal = acctBal;
    }
}
