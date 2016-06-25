package userService.auth;

import com.google.inject.Guice;
import com.google.inject.Injector;
import userService.auth.persistance.UserDao;
import userService.auth.persistance.models.User;

import java.util.function.Consumer;

public class Run {
    public static void main(String[] args) {
//        DataSource dataSource = new DataSource();
//        ConnectionManager conn  = new ConnectionManager(dataSource);
//        UserDao userDao = new UserDaoImp(conn);



        // with guice
        Injector injector = Guice.createInjector(new BillingModule());
        UserDao userDao = injector.getInstance(UserDao.class);


        System.out.println("-*-");
        /**
         *  вывод всего списка
         */
        out_all(userDao);
        System.out.println("-*-");
        /**
         *  добавоение пользователя
         */
        User us = new User(-1, "qwer","qwer");
        System.out.println("Add new user: " + us);
        userDao.save(us);
        System.out.println("-*-");
        /**
         * вывод всего списка
         */
        out_all(userDao);
        System.out.println("-*-");
        /**
         *  Поиск по "ид"
         */
        System.out.println("Find by Id = 3");
        System.out.println(userDao.findById(3));
        System.out.println("-*-");
        /**
         *  поиск по имени
         */
        System.out.println(userDao.findByName("sanya"));
        System.out.println("-*-");
        /**
         *  обновление данных пользователя
         */
        us = new  User(3, "Andrey","1111");
        System.out.println("New user: " + us);
        userDao.update(us);
        System.out.println("Updated.");
        System.out.println("-*-");
        /**
         *  вывод всего списка
         */
        out_all(userDao);
        System.out.println("-*-");
        /**
         *  Удаление из списка
         */
        us = new User(4, "qwer","qwer");
        System.out.println("Delete user: " + us);
        userDao.remove(us);
        System.out.println("Updated.");
        System.out.println("-*-");
        userDao.getAll().forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println(user);
            }
        });
    }

    private static void  out_all(UserDao ud)
    {
        ud.getAll().forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println(user);
            }
        });
    }


}
