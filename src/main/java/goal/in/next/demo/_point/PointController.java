package goal.in.next.demo._point;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;

    @GetMapping("/points/{id}")
    public Point getPoints(@PathVariable Long id){
        return pointService.findPost(id);
    }

    @PostMapping("/points/{id}/{point}")
    public void subtractPoints(@PathVariable Long id, @PathVariable Long point){
        log.info("PointController.subtractPoints()");
        pointService.subtractPoint(id, point);
    }
}
