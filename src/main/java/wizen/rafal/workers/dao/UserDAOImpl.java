package wizen.rafal.workers.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wizen.rafal.workers.entity.User;

import javax.persistence.EntityManager;

@Repository
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl (EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public User getUserByName(String name) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> theQuery = currentSession.createQuery(
                "from User U WHERE U.username = ?1");
        theQuery.setParameter(1, name);
        User user = theQuery.getSingleResult();
        return user;
    }
}
