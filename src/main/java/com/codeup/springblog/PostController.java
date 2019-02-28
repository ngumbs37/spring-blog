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

    @PostMapping("/post/{id}/delete")
    public String deletePost(@PathVariable long id, @ModelAttribute Post post){

        postRepository.delete(post);
        return "redirect:/posts";
    }
    @GetMapping("/post/{id}/delete")
    public String deletePost(@PathVariable long id, Model model){
        Post post = postRepository.findOne(id);
        model.addAttribute("post", post);
        return "posts/delete";
    }

    @GetMapping("/post/create")
    public String createPost(Model model){
        model.addAttribute("post", new Post());
//        form(model);
        return "posts/create";
    }

//    public String form(Model model){
//        model.addAttribute("post", new Post());
//        return "partials/form";
//    }
    @PostMapping("/post/create")
    public String createPost(@ModelAttribute Post post){
        postRepository.save(post);
        return "redirect:/posts";
    }


    @GetMapping("/post/{id}/edit")
    public String editPost(@PathVariable long id, Model model){
        model.addAttribute("post", postRepository.findOne(id));
        return "posts/edit";
    }
    @PostMapping("/post/{id}/edit")
    public String editPost(@PathVariable long id, @ModelAttribute Post post){

        Post oldPost = postRepository.findOne(id);

        post.setId(oldPost.getId());
        postRepository.save(post);
        return "redirect:/post/" + post.getId();
    }
}
