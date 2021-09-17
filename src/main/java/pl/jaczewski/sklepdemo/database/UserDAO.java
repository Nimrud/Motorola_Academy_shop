package pl.jaczewski.sklepdemo.database;

import org.springframework.stereotype.Component;
import pl.jaczewski.sklepdemo.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class UserDAO {

    private List<User> userList = Arrays.asList(
            new User(1L, "Jan Kowalski", "jan@kowalski.pl", "kowal555", "444-333-555", false, false),
            new User(2L, "Anna Potok", "anna_potok@op.pl", "anna555", "777-222-000", false, true),
            new User(3L, "Marek Admin", "admin@wp.pl", "admin555", "930-000-100", true, true)
    );

    public List<User> allUsers() {
        return userList;
    }

    public List<User> allCustomers() {
        List<User> customers = new ArrayList<>();
        for (User u : userList) {
            if (!u.isAdmin()) {
                customers.add(u);
            }
        }
        return customers;
    }

    public User userById(Long id) {
        for (User u : userList) {
            if (id.equals(u.getId())) {
                return u;
            }
        }
        return null;
    }
}
