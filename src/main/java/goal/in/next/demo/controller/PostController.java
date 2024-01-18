package goal.in.next.demo.controller;

import goal.in.next.demo.dto.PostForm;
import goal.in.next.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/user")
    public void insertPost() {
        postService.insertPost();
    }
}
