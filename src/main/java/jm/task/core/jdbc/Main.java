package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDaoHibernate = new UserDaoHibernateImpl();

        userDaoHibernate.createUsersTable();

        userDaoHibernate.saveUser("Вася", "Иванов", (byte) 9);
        userDaoHibernate.saveUser("Саша", "Петров", (byte) 8);
        userDaoHibernate.saveUser("Маша", "Краснова", (byte) 7);
        userDaoHibernate.saveUser("Катя", "Сидорова", (byte) 6);

        System.out.println(userDaoHibernate.getAllUsers());

        userDaoHibernate.removeUserById(1L);

        userDaoHibernate.cleanUsersTable();

        userDaoHibernate.dropUsersTable();
    }
}