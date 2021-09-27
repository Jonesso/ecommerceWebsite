package ru.pavlinina.ecommerce.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.pavlinina.ecommerce.models.Product;
import ru.pavlinina.ecommerce.models.User;
import ru.pavlinina.ecommerce.repositories.UserRepository;

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
  void findUserByEmail() {
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

  @Test
  void updateUserCart() {
    User user = new User();
    user.setEmail("emailbox@someserver.com");
    user.setFirstName("Sherlock");
    user.setLastName("Holmes");
    user.setPassword("root");

    Mockito.doReturn(user)
        .when(userRepository)
        .findByEmail(user.getEmail());

    List<Product> userProductList = new ArrayList<>();
    Product product1 = new Product();
    product1.setProductName("eyeballs");
    userProductList.add(product1);
    Product product2 = new Product();
    product2.setProductName("bruises");
    userProductList.add(product2);
    user.setProductList(userProductList);

    userService.save(user);

    Product product3 = new Product();
    product3.setProductName("rope");
    userProductList.add(product3);
    user.setProductList(userProductList);
    userService.update(user);

    verify(userRepository, times(2)).save(user);
  }


  @Test
  void deleteUser() {
    User user = new User();
    user.setUserId(100L);
    user.setEmail("email@server.net");
    user.setFirstName("Mary");
    user.setLastName("Jane");
    user.setPassword("qwerty1234");

    userService.save(user);

    userService.deleteUser(100L);

    verify(userRepository, times(1)).deleteById(100L);
  }

  @Test
  void findAllUsers() {
    User user1 = new User();
    user1.setFirstName("Mary");
    user1.setLastName("Jane");
    user1.setPassword("1234");

    User user2 = new User();
    user2.setFirstName("Sherlock");
    user2.setLastName("Holmes");
    user2.setPassword("newpass");
    List<User> usersList = new ArrayList<>();

    userService.save(user1);
    userService.save(user2);

    usersList.add(user1);
    usersList.add(user2);

    Mockito.doReturn(usersList)
        .when(userRepository)
        .findAll();

    assertEquals(usersList, userService.findAllUser());
  }
}
