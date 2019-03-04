package com.codeup.springblog.dao;

import com.codeup.springblog.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
