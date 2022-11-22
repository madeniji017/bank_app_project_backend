package bank_app.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AccountNumber {

    public long bankCode = 10;
    public long branchCode = 22;
    public long customerCode = 100006;

    @Bean
    public Long acctNumGenerator() {
        String tempAcctNum = String.format("%s%s%s", bankCode, branchCode, customerCode);
        System.out.println(tempAcctNum);
        Long l = Long.parseLong(tempAcctNum);
        customerCode++;
        return l;
    }

    public AccountNumber() {}

}
