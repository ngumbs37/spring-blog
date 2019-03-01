package com.codeup.springblog.models.post;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Post")
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body", length = 1024)
    private String body;

    @Column(name = "filepath", length = 512)
    private String filepath;

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PostTag> tags = new ArrayList<>();

    public Post() {
    }

    public Post(Long id,String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<PostTag> getTags() {
        return tags;
    }

    public void setTags(List<PostTag> tags) {
        this.tags = tags;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public void addTag(Tag tag) {
        PostTag postTag = new PostTag(this, tag);
        tags.add(postTag);
        tag.getPosts().add(postTag);
    }

    public void removeTag(Tag tag) {
        for (Iterator<PostTag> iterator = tags.iterator();
             iterator.hasNext(); ) {
            PostTag postTag = iterator.next();

            if (postTag.getPost().equals(this) &&
                    postTag.getTag().equals(tag)) {
                iterator.remove();
                postTag.getTag().getPosts().remove(postTag);
                postTag.setPost(null);
                postTag.setTag(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Post post = (Post) o;
        return Objects.equals(title, post.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}









//
//@Entity
//public class Post {
//    private String owner;
//
//    @Id
//    @GeneratedValue
//    private long postId;
//
//    @Column(name="title", nullable = false, length = 128)
//    private String title;
//
//    @Column(name="body", length = 1024)
//    private String body;
//
//    @ManyToOne
//    @JoinColumn(name ="FK_UserId")
//    private long ownerId;
//
//    @ManyToMany(mappedBy="mainMenu", cascade = CascadeType.ALL)
//    @Column(name="FK_Tags")
//    private List<String> tags;
//
//    public Post(String title, String body) {
//        this.title = title;
//        this.body = body;
//    }
//
//    public Post(long id, String title, String body) {
//        this.postId = id;
//        this.title = title;
//        this.body = body;
//    }
//
//    public Post(String title, String body, String owner, long ownerId, long postId, List<String> tags) {
//        this.title = title;
//        this.body = body;
//        this.owner = owner;
//        this.ownerId = ownerId;
//        this.postId = postId;
//        this.tags = tags;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getBody() {
//        return body;
//    }
//
//    public void setBody(String body) {
//        this.body = body;
//    }
//
//    public String getOwner() {
//        return owner;
//    }
//
//    public void setOwner(String owner) {
//        this.owner = owner;
//    }
//
//    public long getOwnerId() {
//        return ownerId;
//    }
//
//    public void setOwnerId(long ownerId) {
//        this.ownerId = ownerId;
//    }
//
//    public long getPostId() {
//        return postId;
//    }
//
//    public void setPostId(long postId) {
//        this.postId = postId;
//    }
//
//    public List<String> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<String> tags) {
//        this.tags = tags;
//    }
//
//}
