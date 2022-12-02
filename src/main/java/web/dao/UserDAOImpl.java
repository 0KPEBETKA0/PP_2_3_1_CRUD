package web.dao;


import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//@Service
@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    //@Transactional
    public void save(User user) {
        entityManager.merge(user);
    }

    @Override
    //@Transactional
    public void delete(int id) {
        User user = getUser(id);
        entityManager.remove(user);
    }

    @Override
    //@Transactional
    public void update(User user) {
        entityManager.merge(user);
    }
}
