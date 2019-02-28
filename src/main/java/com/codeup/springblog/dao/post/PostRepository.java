package com.codeup.springblog.dao.post;

import com.codeup.springblog.models.post.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
