package com.codeup.springblog;

import com.codeup.springblog.dao.post.PostRepository;
import com.codeup.springblog.models.post.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @GetMapping("/posts")
    public String posts(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/post/{id}")
    public String post(@PathVariable long id, Model model){
        Post post = postRepository.findOne(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @PostMapping("/post/delete")
    public String deletePost(@RequestParam("id") long id){
        postRepository.delete(id);
        return "redirect:/posts";
    }
    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable long id, Model model){
        Post post = postRepository.findOne(id);
        model.addAttribute("post", post);
        return "posts/delete";
    }

    @GetMapping("/post/create")
    public String createPost(){
        return "posts/create";
    }
    @PostMapping("/post/create")
    public String createPost(@RequestParam("title") String title,
                              @RequestParam("body") String body
//            ,@RequestParam("tags") String[] tags
    ){

        Post post = new Post(title,body);
        postRepository.save(post);
        return "redirect:/posts";
    }



}
