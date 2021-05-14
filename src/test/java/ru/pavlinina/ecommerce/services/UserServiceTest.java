package ru.pavlinina.ecommerce.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.pavlinina.ecommerce.models.User;
import ru.pavlinina.ecommerce.repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Sofia Pavlinina
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void loadUserByEmail() {
        User user = new User();
        user.setFirstName("user");
        user.setLastName("testing");
        user.setEmail("example_email@mail.org");
        user.setPassword("pswd");

        userService.save(user);

        Mockito.doReturn(user)
                .when(userRepository)
                .findByEmail(user.getEmail());
    }

    @Test
    void saveUser() {
        User user = new User();
        user.setEmail("myemail@example.com");
        user.setFirstName("John");
        user.setLastName("Watson");
        user.setPassword("password");

        userService.save(user);

        assertEquals("John", user.getFirstName());
        assertEquals("Watson", user.getLastName());
        Mockito.verify(userRepository, Mockito.times(1)).save(user);

    }
}
