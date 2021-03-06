package ru.pavlinina.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pavlinina.ecommerce.models.Product;
import ru.pavlinina.ecommerce.models.User;
import ru.pavlinina.ecommerce.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * implementation of {@link ru.pavlinina.ecommerce.services.ProductService}
 * @author Sofia Pavlinina
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(User user) {
        user.setActive(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        List<Product> productlist1 = user.getProductList();
        List<Product> productlist = (userRepository.findByEmail(user.getEmail())).getProductList();
        productlist1.addAll(productlist);
        user.setProductList(productlist1);

        userRepository.save(user);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }
}
