package goal.in.next.demo.point;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

@Slf4j
@Service
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;

    public Point findPost(Long id) {
        return pointRepository.findById(id).orElseThrow();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void subtractPoint(Long id, Long point){
        Point post = findPost(id);
        if(post.getPoints() - point >= 0){ //차감결과가 0 이상인 경우
            post.substractPoint(point);
        }
        log.info("PointService.subtractPoint()");
    }
}
