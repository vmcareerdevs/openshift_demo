package com.example.dataDemo.devconnect.developers;

import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/developers")
public class DeveloperController {
    private DeveloperRepository repository;

    public DeveloperController(DeveloperRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public @ResponseBody Iterable<Developer> getDevelopers() {
        return repository.findAll();
    }

    @PostMapping
    public @ResponseBody Developer addDeveloper(@RequestBody Developer newDeveloper) {
        newDeveloper = repository.save(newDeveloper);
        return newDeveloper;
    }

    @GetMapping("/{id}")
    public @ResponseBody Optional<Developer> getDeveloper(@PathVariable Long id) {
        return repository.findById(id);
    }

}