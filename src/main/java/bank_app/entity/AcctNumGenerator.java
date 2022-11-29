package bank_app.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

@Component
@Setter
@Getter
@Entity
public class AcctNumGenerator {

    @Id
    private Long id;
    private Long bankCode;
    private Long branchCode;
    private Long customerCode;



}
