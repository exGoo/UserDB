package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Vlad", "Gulyaev", (byte) 24);
        userService.saveUser("Ivan", "Ivanov", (byte) 32);
        userService.saveUser("Anton", "Antonov", (byte) 27);
        userService.saveUser("Boris", "Borisov", (byte) 21);

        List<User> list = userService.getAllUsers();

        userService.cleanUsersTable();
        System.out.println("Удалены " + list.size() + " строки с данными.");
    }

}
