package bloggy;

import bloggy.dao.*;
import bloggy.dao.impl.*;
import com.codeforces.commons.io.http.HttpMethod;
import com.codeforces.commons.text.StringUtil;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.matcher.AbstractMatcher;
import org.aopalliance.intercept.MethodInterceptor;
import org.nocturne.link.Links;
import org.nocturne.main.ApplicationContext;
import org.nocturne.main.Component;
import bloggy.captions.dao.CaptionDao;
import bloggy.captions.dao.impl.CaptionDaoImpl;
import bloggy.web.annotation.PostOnly;
import bloggy.web.page.IndexPage;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author Mike Mirzayanov
 */
public class ApplicationModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(DateDao.class).to(DateDaoImpl.class);
        binder.bind(CaptionDao.class).to(CaptionDaoImpl.class);
        binder.bind(UserDao.class).to(UserDaoImpl.class);
        binder.bind(NoteDao.class).to(NoteDaoImpl.class);
        binder.bind(PostDao.class).to(PostDaoImpl.class);
        binder.bind(CommentDao.class).to(CommentDaoImpl.class);


        binder.bindInterceptor(new AbstractMatcher<Class<?>>() {
            @Override
            public boolean matches(Class<?> aClass) {
                return Component.class.isAssignableFrom(aClass);
            }
        }, new AbstractMatcher<Method>() {
            @Override
            public boolean matches(Method method) {
                return method.getAnnotation(PostOnly.class) != null;
            }
        }, invocation -> {
            HttpServletRequest request = ApplicationContext.getInstance().getRequest();
            if (request != null && StringUtil.equalsIgnoreCase(request.getMethod(), HttpMethod.POST.name())) {
                return invocation.proceed();
            } else {
                ApplicationContext.getInstance().getResponse().sendRedirect(Links.getLink(IndexPage.class));
                return null;
            }
        });
    }
}
