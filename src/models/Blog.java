package models;

import java.util.Date;

public class Blog {
    private Long id;
    private User user;
    private String title;
    private String content;
    private Date postDate;

    public Blog(Long id, User user, String title, String content, Date postDate) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.postDate = postDate;
    }
    public Blog() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}
