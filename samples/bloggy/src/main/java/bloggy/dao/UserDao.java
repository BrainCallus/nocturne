package bloggy.dao;

import bloggy.model.User;

public interface UserDao {
    User find(long id);

    User findByName(String name);

    User findByLoginAndPassword(String login, String password);

    User findByLogin(String login);

    boolean save(User user, String password);

}
