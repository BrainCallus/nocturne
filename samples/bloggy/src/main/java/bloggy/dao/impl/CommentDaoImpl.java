package bloggy.dao.impl;

import bloggy.dao.CommentDao;
import bloggy.model.Comment;
import bloggy.model.Post;
import com.google.inject.Singleton;

@Singleton
public class CommentDaoImpl extends NoteDaoImpl<Comment, Post> implements CommentDao {

}
