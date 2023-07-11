package bloggy.web.page;

import bloggy.dao.CommentDao;
import bloggy.dao.impl.CommentDaoImpl;
import bloggy.model.Comment;
import bloggy.validator.BaseValidator;
import bloggy.web.annotation.PostOnly;
import bloggy.web.frame.CommentsViewFrame;
import com.google.inject.Inject;
import org.nocturne.annotation.Action;
import org.nocturne.annotation.Parameter;
import org.nocturne.annotation.Validate;
import org.nocturne.link.Link;
import bloggy.dao.PostDao;
import bloggy.model.Post;
import bloggy.web.frame.PostViewFrame;

@Link("post/{postId}")
public class PostViewPage extends WebPage {
    @Parameter
    private long postId;

    private Post post;
    @Inject
    private PostDao postDao;

    @Inject
    private PostViewFrame postViewFrame;

    @Inject
    private CommentsViewFrame commentsViewFrame;

    @Override
    public void initializeAction() {
        super.initializeAction();

        post = postDao.find(postId);
        if (post == null) {
            setMessage("No such post");
            abortWithRedirect(IndexPage.class);
        }
    }

    @Override
    public void action() {
        postViewFrame.setNote(post);
        commentsViewFrame.setNotesEntity(post);
        put("post", post);
        put("commentsAmount", commentsViewFrame.getCommentsAmount());
        parse("commentsViewFrame", commentsViewFrame);
        postViewFrame.setShortMode(false);
        parse("postViewFrame", postViewFrame);
    }

    @PostOnly
    @Validate("addComment")
    public boolean validateComment(@Parameter(name = "text", stripMode = Parameter.StripMode.NONE) String text) {
        addValidator("text", new BaseValidator());
        if (runValidation()) {
            Comment comment = new Comment();
            comment.setText(text);
            comment.setPostId(this.post.getId());
            comment.setUserId(getUser().getId());
            CommentDao commentDao = new CommentDaoImpl();
            commentDao.insert(comment);
            return true;
        }
        put("showForm", true);
        action();
        return false;
    }

    @PostOnly
    @Action("addComment")
    public void finalizeAdd() {
        abortWithReload();
    }

}
