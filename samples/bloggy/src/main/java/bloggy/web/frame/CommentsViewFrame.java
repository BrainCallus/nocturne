package bloggy.web.frame;

import bloggy.dao.CommentDao;
import bloggy.model.Comment;
import bloggy.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public class CommentsViewFrame extends NotesViewFrame<Comment, CommentDao, Post> {

    @Override
    protected void parseNote(Comment note) {
        CommentViewFrame noteViewFrame = getInstance(CommentViewFrame.class);
        noteViewFrame.setNote(note);
        noteViewFrame.setShortMode(true);

        parse("comment" + note.getId(), noteViewFrame);
    }

    @Override
    protected void putProperties(List<Comment> notes) {
        put("commentIds", notes.stream().map(Comment::getId).collect(Collectors.toList()));
    }

    public long getCommentsAmount() {
        return noteDao.findBy(notesEntity).size();
    }
}
