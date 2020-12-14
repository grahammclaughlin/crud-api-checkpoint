package com.CRUDAPI.checkpoint;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersRepository repository;

    public UsersController(UsersRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    @JsonView(UserViews.Common.class)
    public Iterable<User> all() {
        return this.repository.findAll();
    }

    @PostMapping("")
    @JsonView(UserViews.Common.class)
    public User create(@RequestBody User user) {
        return this.repository.save(user);
    }

    @GetMapping("/{id}")
    @JsonView(UserViews.Common.class)
    public User read(@PathVariable Long id) {
        Optional<User> r = this.repository.findById(id);
        return r.get();
    }

    @DeleteMapping("/{id}")
    public HashMap<String,Long> delete(@PathVariable Long id) {
        this.repository.delete(this.repository.findById(id).get());
        Long users = this.repository.count();
        HashMap<String,Long> ret = new HashMap<>();
        ret.put("count",users);
        return ret;
    }

    @PatchMapping("/{id}")
    @JsonView(UserViews.Common.class)
    public User update(
            @PathVariable Long id,
            @RequestBody User user) {
        User u = read(id);
        u.update(user);
        return this.repository.save(u);
    }

    @PostMapping("/authenticate")
    @JsonView(UserViews.Common.class)
    public HashMap<String, Object> authenticate(@RequestBody User user) {
        User got = this.repository.findByEmail(user.getEmail());
        HashMap<String,Object> ret = new HashMap<>();
        Boolean auth = false;
        if(got instanceof User) {
            auth = user.equals(got);
        }
        ret.put("authenticated", auth);
        if (auth) {
            ret.put("user", got);
        }
        return ret;
    }

}
