package bloggy.web.frame;

import bloggy.dao.PostDao;
import bloggy.model.Post;
import bloggy.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class PostsViewFrame extends NotesViewFrame<Post, PostDao, User> {

    @Override
    protected void parseNote(Post note) {
        PostViewFrame noteViewFrame = getInstance(PostViewFrame.class);
        noteViewFrame.setNote(note);
        noteViewFrame.setShortMode(true);

        parse("post" + note.getId(), noteViewFrame);
    }

    @Override
    protected void putProperties(List<Post> notes) {
        put("commentNums", notes.stream().map(note -> noteDao.getComments(note)).collect(Collectors.toList()));
        put("posts", notes);
    }
}
