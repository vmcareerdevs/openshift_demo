package com.example.dataDemo.devconnect.posts;

import com.example.dataDemo.devconnect.developers.Developer;
import com.example.dataDemo.devconnect.developers.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostRepository repository;

    @Autowired
    private DeveloperRepository devRepository;

    @GetMapping
    public @ResponseBody Iterable<Post> getPosts() {
        return repository.findAll();
    }

    @GetMapping("post/{id}")
    public @ResponseBody Optional<Post> getPost(@PathVariable Long id) {
        return repository.findById(id);
    }

    @GetMapping("/developer/{id}")
    public @ResponseBody Iterable<Post> getDeveloperPosts(@PathVariable Long id) {
        return repository.findByDeveloperId(id);
    }

    @PostMapping("/{devId}")
    public @ResponseBody Post newPost(@PathVariable Long devId, @RequestBody Post newPost) {
        Developer poster = devRepository.findById(devId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Developer Not Found"));
        newPost.setDeveloper(poster);
        return repository.save(newPost);
    }
}
