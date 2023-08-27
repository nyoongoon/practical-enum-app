package goal.in.next.demo.controller;

import goal.in.next.demo.dto.PostForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    @PostMapping("/user")
    public void insertPost(@RequestBody PostForm postForm){

    }
}
