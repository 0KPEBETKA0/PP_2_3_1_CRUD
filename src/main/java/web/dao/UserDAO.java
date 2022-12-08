package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    public User getUser(Long id);

    public void save(User user);

    public void delete(Long id);

    public void update(User user);
}
