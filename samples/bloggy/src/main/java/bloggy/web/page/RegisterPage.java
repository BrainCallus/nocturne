package bloggy.web.page;

import bloggy.dao.UserDao;
import bloggy.model.User;
import bloggy.validator.OptionalValidator;
import bloggy.web.annotation.PostOnly;
import com.google.inject.Inject;
import org.nocturne.annotation.Action;
import org.nocturne.annotation.Parameter;
import org.nocturne.annotation.Validate;
import org.nocturne.link.Link;
import org.nocturne.validation.ValidationException;
import org.nocturne.validation.Validator;

@Link("register")
public class RegisterPage extends WebPage {
    @Inject
    private UserDao userDao;

    @PostOnly
    @Validate("register")
    public boolean validateRegister(@Parameter(name = "login", stripMode = Parameter.StripMode.NONE) String login,
                                    @Parameter(name = "name", stripMode = Parameter.StripMode.NONE) String name,
                                    @Parameter(name = "password", stripMode = Parameter.StripMode.NONE) String password) {
        addValidator("name", new OptionalValidator("[[a-zA-Z]+\\s{1}]*[[a-zA-Z]+]+", 3L, 50L, "Only latin letters and delimiters expected"));
        addValidator("login", new OptionalValidator("[\\w+\\s{1}]*\\w+", 3L, 30L, "Only latin letters, numerals and delimiters expected"));
        addValidator("password", new OptionalValidator("\\S+", 5L, 64L, "Should not contain delimiters"));
        addValidator("password", new Validator() {
            @Override
            public void run(String ignored) throws ValidationException {
                if (userDao.findByLogin(login) != null) {
                    throw new ValidationException($("Login already in use!"));
                }
            }
        });
        if (runValidation()) {
            User user = new User();
            user.setName(name);
            user.setLogin(login);
            if (!userDao.save(user, password)) {
                put("err", true);
            } else {
                return true;
            }
        }
        return false;
    }

    @PostOnly
    @Action("register")
    public void onEnter(@Parameter(name = "login", stripMode = Parameter.StripMode.NONE) String login,
                        @Parameter(name = "password", stripMode = Parameter.StripMode.NONE) String password) {
        authenticate(userDao.findByLoginAndPassword(login, password));
        setMessage("Welcome " + login);
        abortWithRedirect(IndexPage.class);
    }

    @Override
    public void action() {
        User user = getUser();
        if (user != null) {
            abortWithRedirect(IndexPage.class);
        }
    }
}
