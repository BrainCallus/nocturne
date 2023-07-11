package bloggy.dao.impl;

import com.google.inject.Singleton;
import bloggy.dao.UserDao;
import bloggy.model.User;
import org.jacuzzi.core.Jacuzzi;

@Singleton
public class UserDaoImpl extends ApplicationDaoImpl<User> implements UserDao {
    private static final String PASSWORD_SALT = "5745e88385b83d0dd5e08336579d4bcf";

    @Override
    public User find(long id) {
        return super.find(id);
    }

    @Override
    public User findByName(String name) {
        return findOnlyBy("name=?", name);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        User user = findOnlyBy("login=?", login);
        if (user != null && findCountBy("login=? AND passwordSha=SHA1(CONCAT(id, ?, ?))",
                login, password, PASSWORD_SALT) == 1) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User findByLogin(String login) {
        return findOnlyBy("login=?", login);
    }

    @Override
    public boolean save(User user, String password) {
        super.save(user);
        user.setId(findByLogin(user.getLogin()).getId());
        try {
            setPassword(user, password);
        } catch (Exception e) {
            super.delete(user);
            return false;
        }
        return true;
    }

    private void setPassword(User user, String password) {
        String query = "UPDATE `user` SET `passwordSha` = SHA1(CONCAT(?, ?, ?)) WHERE `user`.`login` = ?;"; //password, salt
        getJacuzzi().execute(query, user.getId(), password, PASSWORD_SALT, user.getLogin());
    }

}
