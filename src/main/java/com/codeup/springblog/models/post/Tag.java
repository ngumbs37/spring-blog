package com.codeup.springblog.models.post;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Tag")
@Table(name = "tag")
//@NaturalIdCache
//@Cache(
//        usage = CacheConcurrencyStrategy.READ_WRITE
//)
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    private String name;

    @OneToMany(
            mappedBy = "tag",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PostTag> posts = new ArrayList<>();

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostTag> getPosts() {
        return posts;
    }

    public void setPosts(List<PostTag> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}


//@Entity
//@Table(name = "tags")
//public class Tag {
//
//    @Id
//    @GeneratedValue
//    private long id;
//
//    @Id
//    @ManyToMany()
//    private long postId;
//
//
//    @Column(name = "name", nullable = false, length = 64)
//    private String name;
//
//    public Tag(String name) {
//        this.name = name;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
