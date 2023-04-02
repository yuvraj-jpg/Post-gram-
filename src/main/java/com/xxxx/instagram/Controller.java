package com.xxxx.instagram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @Autowired
    Service service;

    @PostMapping("/adduser")
    public String addUser(@RequestBody User user){
        return service.addUser(user);
    }
    @PostMapping("/addpost/{userid}")
    public String addPost(@RequestBody Poster poster, @PathVariable int userid){
        return service.addPost(poster,userid);
    }

    @PutMapping("likepost/{postid}")
    public String likePost(@PathVariable int postid){
        return service.likePost(postid);
    }
    @GetMapping("maximumnotification/{userid}")
    public int max(@PathVariable int userid){
        return service.max(userid);
    }
}
