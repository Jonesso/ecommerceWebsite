package ru.pavlinina.ecommerce.services;

import ru.pavlinina.ecommerce.models.User;

import java.util.List;

/**
 * service for operating with  User table entities
 * @author Sofia Pavlinina
 */
public interface UserService {

    /**
     * method for getting User table entity by email
     * @param email - string of user's email
     * @return necessary entity
     */
    public User findByEmail(String email);

    /**
     * method for adding new entity to User table
     * @param user new entity to save in User table
     */
    public void save(User user);

    /**
     * method for updating User table entity's data
     * @param user entity with updated data to change the old one
     */
    public void update(User user);

    /**
     * method for returning all users from table
     * @return list of all entities from User table
     */
    public List<User> findAllUser();

    /**
     *  method for removing entity from User table
     * @param userId id of entity to delete
     */
    public void deleteUser(long userId);
}
