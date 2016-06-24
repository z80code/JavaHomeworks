package userService.auth;

import com.google.inject.AbstractModule;
import userService.auth.persistance.UserDao;
import userService.auth.persistance.UserDaoImp;

public class BillingModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserDao.class).to(UserDaoImp.class);
    }
}
