package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    private Session session;
    List<User> users;
    public UserDaoHibernateImpl() {}

    @Override
    public void createUsersTable() {
        session = hibernateUtilConnection();
        Transaction transaction = session.beginTransaction();
        String createUserTableQuery = "CREATE TABLE IF NOT EXISTS users2"
                + "(id SERIAL PRIMARY KEY,"
                + "name VARCHAR(50) NOT NULL,"
                + "lastname VARCHAR(50) NOT NULL,"
                + "age INTEGER NOT NULL);";
        session.createSQLQuery(createUserTableQuery).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        session = hibernateUtilConnection();
        Transaction transaction = session.beginTransaction();
        String dropUsersTableQuery = "DROP TABLE IF EXISTS users2";
        session.createSQLQuery(dropUsersTableQuery).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        session = hibernateUtilConnection();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        session = hibernateUtilConnection();
        Transaction transaction = session.beginTransaction();
        User removedUser = session.get(User.class, id);
        session.delete(removedUser);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        session = hibernateUtilConnection();
        Transaction transaction = session.beginTransaction();
        users = (List<User>) session.createCriteria(User.class).list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        session = hibernateUtilConnection();
        Transaction transaction = session.beginTransaction();
        List<User> cleanedUserList = (List<User>) session.createCriteria(User.class).list();
        for (User u : cleanedUserList) {
            session.delete(u);
        }
        transaction.commit();
        session.close();
    }
}