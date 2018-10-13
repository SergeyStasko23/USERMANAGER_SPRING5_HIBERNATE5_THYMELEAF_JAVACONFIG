package ru.stacy.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.stacy.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(user);

        logger.info("User successfully saved. User details: " + user);
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);

        logger.info("User successfully updated. User details: " + user);
    }

    @Override
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(getUserById(id));

        logger.info("User successfully removed. User id: " + id);
    }

    @Override
    public void removeAllUser() {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(listUsers());

        logger.info("All users successfully removed");
    }

    @Override
    public User getUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);

        logger.info("User successfully loaded. User details: " + user);

        return user;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("FROM User ").list();

        for(User user : userList) {
             logger.info("User list: " + user);
        }

        return userList;
    }
}
