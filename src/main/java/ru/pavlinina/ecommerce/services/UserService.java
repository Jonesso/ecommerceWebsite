package ru.pavlinina.ecommerce.services;

import ru.pavlinina.ecommerce.models.User;

import java.util.List;

/**
 * @author Sofia Pavlinina
 */
public interface UserService {

    public User findByEmail(String email);

    public void save(User user);

    public void update(User user);

    public List<User> findAllUser();

    public void deleteUser(long userId);
}
