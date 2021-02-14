package com.ecare.services;

import com.ecare.dao.IUserDAO;
import com.ecare.dto.User;
import com.ecare.models.UserPO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  Describes handler of user
 */
@Service
public class UserService extends BaseService<IUserDAO, UserPO, User>  {

    /**
     * Seeks object by id
     * @param id id of object
     * @return object or empty
     */
    public Optional<User> get(long id) {
        Optional<UserPO> userPO = dao.get(id);
        return userPO.isPresent() ? Optional.of(new User(userPO.get())) : Optional.empty();
    }

    /**
     * Seeks N object in table. Used for pagination
     * @param from start id in table
     * @param number number of records to load
     * @return list of objects
     */
    public List<User> get(int from, int number) {
        return dao.get(from, number).stream().map(value -> User.of(value)).collect(Collectors.toList());
    }

    /**
     * @return all objects from table
     */
    public List<User> getAll() {
        return dao.getAll().stream().map(value -> User.of(value)).collect(Collectors.toList());
    }

    /**
     * Saves objects into database
     * @param value object for saving
     * @return updated object
     */
    public User save(User value) {
        return new User(dao.save(value.toEntity()));
    }

    /**
     * Saves objects into database
     * @param value object for saving
     * @return updated object
     */
    public User update(User value) {
        return new User(dao.update(value.toEntity()));
    }

    /**
     * Deletes object from database
     * @param value object to delete
     */
    public void delete(User value) { dao.delete(value.toEntity()); }

    /**
     * find user by email
     * @param email email of user
     * @return Found user or null if user is not found
     */
    public User findByEmail(String email) {
        UserPO user = dao.findByEmail(email);
        return user == null ? null : new User(user);
    }
}
