package goal.in.next.demo.controller;

import goal.in.next.demo.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParentController {
    private final ParentService parentService;

    @PostMapping("/text")
    public void insertPost() {
        parentService.insertParent();
    }
}
