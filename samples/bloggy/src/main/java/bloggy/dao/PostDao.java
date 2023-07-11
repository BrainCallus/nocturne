package bloggy.dao;

import bloggy.model.Post;
import bloggy.model.User;

public interface PostDao extends NoteDao<Post, User> {
    String findNote();

    long getComments(Post post);
}
