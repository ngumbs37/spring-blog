package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String posts(){
        return "Posts index page where all the posts can be accessed";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String posts(@PathVariable int id){
        return "showing post for id: " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPosts(){
        return "creating post for page ";
    }
    @PostMapping("/posts/create")
    @ResponseBody
    public String createPosts(@RequestParam("title") String title,
                              @RequestParam("body") String body,
                              @RequestParam("tags") String tags){
        return "post created";
    }
}
