package pl.jaczewski.sklepdemo.database;

import pl.jaczewski.sklepdemo.model.User;

import java.util.Arrays;
import java.util.List;

public class UserDAO {

    private List<User> userList = Arrays.asList(
            new User(1L, "Jan Kowalski", "jan@kowalski.pl", "kowal555", "444-333-555", false, false),
            new User(2L, "Jan Kowalski", "jan@kowalski.pl", "kowal555", "444-333-555", false, true),
            new User(3L, "Jan Kowalski", "jan@kowalski.pl", "kowal555", "444-333-555", true, true)
    );

    public List<User> allUsers() {
        return userList;
    }

    public User userByMail(String email) {
        for (User u : userList) {
            if (email.equals(u.getEmail())) {
                return u;
            }
        }
        return null;
    }
}
