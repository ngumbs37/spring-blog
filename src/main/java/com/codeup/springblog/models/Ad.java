package com.codeup.springblog.models;

import javax.persistence.*;
import java.util.List;

public class Ad {
    private long id;
    private long userId;
    private String title;
    private String description;
    private String dateCreated;
    private List<String> categories;

    public Ad() {
    }

    public Ad(long id, long userId, String title, String description) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
    }
    public Ad(long id, long userId, String title, String description, String dateCreated) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
    }

    public Ad(long userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<String> getCategories(){
        return this.categories;
    }

    public void setCategories(List<String> list){
        this.categories = list;
    }
}
