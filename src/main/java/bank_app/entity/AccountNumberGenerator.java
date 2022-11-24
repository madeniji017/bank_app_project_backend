package bank_app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class AccountNumberGenerator {

    @Id
    private int id;
    public long bankCode;
    public long branchCode;
    public long customerCode;


    public synchronized Long generateAcctNumber() {
        String tempAcctNum = String.format("%s%s%s", bankCode, branchCode, customerCode);
        Long l = Long.parseLong(tempAcctNum);
        customerCode++;
        return l;
    }

    public AccountNumberGenerator() {}



}
