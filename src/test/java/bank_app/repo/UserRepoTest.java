package bank_app.repo;

import bank_app.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        User buildUser = User.builder()
                .firstName("Peter")
                .lastName("Pan")
                .email("ppan@gmail.com")
                .bvn("12345678901")
                .phoneNumber("07011220000")
                .password("password")
                .confirmPassword("password")
                .build();

        entityManager.persist(buildUser);
    }

    @Test
    void whenFindByEmail_thenReturnUser() {
        User testUser = userRepo.findByEmail("ppan@gmail.com");
        assertEquals("12345678901", testUser.getBvn());
    }

}