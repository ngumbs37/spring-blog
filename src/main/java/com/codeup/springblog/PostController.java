package com.codeup.springblog;

import com.codeup.springblog.dao.PostRepository;
import com.codeup.springblog.dao.UserRepository;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postRepository;
    private final EmailService emailService;
    private final UserRepository userRepository;
    public PostController(PostRepository postRepository, EmailService emailService, UserRepository userRepository){
        this.postRepository = postRepository;
        this.emailService = emailService;
        this.userRepository = userRepository;
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
         if(id == post.getId()) {
             postRepository.delete(post);
         }
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
        return "posts/create";
    }

    @PostMapping("/post/create")
    public String createPost(@ModelAttribute Post post){

        post.setOwner(getDbUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        Post createdPost = postRepository.save(post);

        emailService.prepareAndSend(createdPost, "Post creation", "You have created a post with the id of " + createdPost.getId());

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

    private User getDbUser(User sessionUser){
        return userRepository.findOne(sessionUser.getId());
    }
}
