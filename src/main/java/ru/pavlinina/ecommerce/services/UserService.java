package ru.pavlinina.ecommerce.services;

import ru.pavlinina.ecommerce.models.User;

/**
 * @author Sofia Pavlinina
 */
public interface UserService {

    public User findByEmail(String email);

    public void save(User user);

    public void update(User user);
}
