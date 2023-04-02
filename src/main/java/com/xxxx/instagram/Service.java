package com.xxxx.instagram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    NotificationRepository notificationRepository;
    public String addUser(@RequestBody User user){
        userRepository.save(user);
        return "User added";
    }
    public String addPost(@RequestBody Poster poster, @RequestParam int userid){
        User user = userRepository.findById(userid).get();
        user.getPosterList().add(poster);
        poster.setUser(user);
        userRepository.save(user);
        return "Post Added";
    }
    public String likePost(int postid){
        Poster poster = postRepository.findById(postid).get();
        poster.setLikes(poster.getLikes()+1);
        Notification notification = new Notification();
        notification.setPosterId(poster.getId());
        notification.setDate(poster.getDate());
        postRepository.save(poster);
        notificationRepository.save(notification);
        return "You like a Post";
    }
    // Find the most number of notifications received by a user
    public int max(int userid){
        List<Poster> posters = postRepository.findAll();
        int ans=0;
        for(Poster poster : posters){
            if(poster.getUser().getId()==userid){
                List<Notification> notificationList = notificationRepository.findAll();
                for(Notification notification : notificationList){
                    if(notification.getPosterId()==poster.getId()){
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
}
