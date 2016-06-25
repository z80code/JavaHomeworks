package userService.auth.persistance;


import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Singleton
public class ConnectionManager {

    private Connection connection;
    private DataSource dataSource;

    @Inject
    public ConnectionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        // TODO add forwarding exception
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(dataSource.getDriver());
                connection = DriverManager.getConnection(
                        dataSource.getUrl(),
                        dataSource.getUser(),
                        dataSource.getPassword());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
