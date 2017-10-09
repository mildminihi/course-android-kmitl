package kmitl.lab07.supanat.lazyinstagram.api;

/**
 * Created by mild supanat on 6/10/2560.
 */

public class UserProfile {
    private String user;
    private String bio;
    private String follower, following, post;
    private String urlProfile;

    public String[] getPosts() {
        return posts;
    }

    public void setPosts(String[] posts) {
        this.posts = posts;
    }

    private String[] posts;

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
