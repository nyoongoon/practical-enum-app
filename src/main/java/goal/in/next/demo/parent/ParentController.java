package goal.in.next.demo.parent;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParentController {
    private final ParentService parentService;

    @PostMapping("/text")
    public void insertPost() {
        parentService.example();
    }


    @PostMapping("/insert")
    public ResponseEntity<ParentResponse> insert() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parentService.insertExample());
    }
}
