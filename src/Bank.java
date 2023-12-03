
import java.util.*;

class Bank {

    List<User> users;
    Admin admin;

    Bank() {
        this.users = new ArrayList<>();
        this.admin = new Admin("admin", "admin");
    }

    void addUser(User user) {
        this.users.add(user);
    }

    User getUser(String username) {
        for (User user : this.users) {
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null;
    }

    boolean isAdmin(String username, String password) {
        return this.admin.username.equals(username) && this.admin.password.equals(password);
    }
}
