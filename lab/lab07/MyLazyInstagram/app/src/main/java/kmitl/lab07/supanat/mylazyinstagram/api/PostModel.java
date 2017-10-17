package kmitl.lab07.supanat.mylazyinstagram.api;

/**
 * Created by mild supanat on 18/10/2560.
 */

public class PostModel {
    private String url;
    private int comment;
    private int like;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
