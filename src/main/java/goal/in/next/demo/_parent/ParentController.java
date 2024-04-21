package goal.in.next.demo._parent;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParentController {
    private final ParentService parentService;

    @GetMapping("/child/{id}")
    public Child findChild(@PathVariable Long id) {
        return parentService.findChild(id);
    }

//    @PostMapping("/text")
//    public void insertPost() {
//        parentService.example();
//    }
//
//
//    @PostMapping("/insert")
//    public ResponseEntity<ParentResponse> insert() {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(parentService.insertExample());
//    }
}
