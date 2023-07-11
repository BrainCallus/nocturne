package bloggy.model;

@SuppressWarnings("unused")
public class Comment extends Note {
    private long postId;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }


}
