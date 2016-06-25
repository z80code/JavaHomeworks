package userService.auth.persistance;
import userService.auth.persistance.models.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    void remove(User user);
    void update(User user);

    User findByName(String Name);
    User findById(int id);
    List<User> getAll();
}
