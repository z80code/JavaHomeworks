package userService.auth.persistance;

import java.util.ResourceBundle;

public class DataSource {

    private final String url;
    private final String user;
    private final String password;
    private final String driver;

    public DataSource() {
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        this.url = bundle.getString("db.url");
        this.user = bundle.getString("db.user");
        this.password = bundle.getString("db.password");
        this.driver = bundle.getString("db.driver");
    }
    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }
}
