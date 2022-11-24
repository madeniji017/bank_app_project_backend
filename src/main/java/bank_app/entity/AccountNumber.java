package bank_app.entity;

import org.springframework.stereotype.Component;


@Component
public class AccountNumber {

    public long bankCode = 10;
    public long branchCode = 22;
    public long customerCode = 100023;


    public synchronized Long generateAcctNumber() {
        String tempAcctNum = String.format("%s%s%s", bankCode, branchCode, customerCode);
        Long l = Long.parseLong(tempAcctNum);
        customerCode++;
        return l;
    }

    public AccountNumber() {}



}
