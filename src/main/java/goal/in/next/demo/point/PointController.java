package goal.in.next.demo.point;

import lombok.RequiredArgsConstructor;
import org.eclipse.jdt.internal.compiler.lookup.ProblemFieldBinding;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;

    @GetMapping("/points/{id}")
    public Point getPoints(@PathVariable Long id){
        Point post = pointService.findPost(id);
        return post;
    }

    @GetMapping("/points/{id}/{point}")
    public void subtractPoints(@PathVariable Long id, @PathVariable Long point){
        pointService.subtractPoint(id, point);
    }
}
