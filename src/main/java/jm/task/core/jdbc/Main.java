package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();
        service.createUsersTable();

        service.saveUser("Влад", "Гуляев", (byte) 24);
        service.saveUser("Иван", "Иванов", (byte) 29);
        service.saveUser("Антон", "Антонов", (byte) 26);
        service.saveUser("Алексей", "Алексеев", (byte) 24);

        List<User> list = service.getAllUsers();
        service.cleanUsersTable();
        service.dropUsersTable();
    }

}
