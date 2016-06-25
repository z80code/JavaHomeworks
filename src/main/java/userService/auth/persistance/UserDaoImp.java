package userService.auth.persistance;

import com.google.inject.Inject;
import userService.auth.persistance.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserDao {
    private final ConnectionManager connectionManager;

    @Inject
    public UserDaoImp(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void save(User user) {
        Connection conn = connectionManager.getConnection();
        try (PreparedStatement statement
                     = conn.prepareStatement(
                "INSERT INTO Users(name, password) VALUES(?,?)")) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());

            // TODO
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(User user) {
        if (user == null) throw new NullPointerException("Отсутствует параметр.");
        Connection conn = connectionManager.getConnection();
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("delete from users where id = " + user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        if (user == null) throw new NullPointerException("Отсутствует параметр.");
        Connection conn = connectionManager.getConnection();
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("UPDATE users set name = '" + user.getName() + "', password = '"+user.getPassword() + "' WHERE id = " + user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByName(String Name) {
        if (Name == null) throw new NullPointerException("Отсутствует параметр.");
        Connection conn = connectionManager.getConnection();
        int id = -1; // ????
        String name = "";
        String password = "";
        try (Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * from users where name = '" + Name + "'");
            result.next();
            id = result.getInt("id");
            name = result.getString("name");
            password = result.getString("password");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (id > 0) ? new User(id, name, password) : null;
    }

    @Override
    public User findById(int id) {
        if (id <= 0) throw new IllegalArgumentException("Неверный параметр.");
        Connection conn = connectionManager.getConnection();
        String name = "";
        String password = "";
        try (Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * from users where id = " + id);
            result.next();
            id = result.getInt("id");
            name = result.getString("name");
            password = result.getString("password");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (id > 0) ? new User(id, name, password) : null;
    }

    @Override
    public List<User> getAll() {
        Connection conn = connectionManager.getConnection();
        List<User> users = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {

            ResultSet result = statement.executeQuery("select * from users");
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String password = result.getString("password");

                users.add(new User(id, name, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
