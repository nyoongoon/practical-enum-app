package goal.in.next.demo._point;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;

    public Point findPost(Long id) {
        return pointRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void subtractPoint(Long id, Long score) {
        Point point = pointRepository.findById(id).orElseThrow();
        if (point.getPoints() - score >= 0) { //차감결과가 0 이상인 경우
            point.substractPoint(score);
        }
        log.info("PointService.subtractPoint()");
    }
}
