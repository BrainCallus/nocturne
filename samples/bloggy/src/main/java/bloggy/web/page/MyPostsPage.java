package bloggy.web.page;

import com.google.inject.Inject;
import org.nocturne.link.Link;
import bloggy.model.User;
import bloggy.web.frame.PostsViewFrame;

@Link("my")
public class MyPostsPage extends WebPage {
    @Inject
    private PostsViewFrame postsViewFrame;

    @Override
    public void action() {
        User user = getUser();
        if (user == null) {
            abortWithRedirect(EnterPage.class);
        } else {
            postsViewFrame.setNotesEntity(user);
            parse("postsViewFrame", postsViewFrame);
        }
    }
}
