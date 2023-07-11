package bloggy.dao.impl;

import com.google.inject.Singleton;
import bloggy.dao.PostDao;
import bloggy.model.Post;
import bloggy.model.User;

@Singleton
public class PostDaoImpl extends NoteDaoImpl<Post, User> implements PostDao {
    @Override
    public String findNote() {
        return "note11";
    }

    @Override
    public long getComments(Post post) {
        return getJacuzzi().findLong("SELECT COUNT(*) FROM `comment` WHERE `postId`=?", post.getId());
    }
}
