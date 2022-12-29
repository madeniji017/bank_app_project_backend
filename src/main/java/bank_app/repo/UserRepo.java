package bank_app.repo;

import bank_app.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends GenericRepo<User> {

    User findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByBvn(String bvn);

    Boolean existsByPhoneNumber(String phoneNumber);

}
