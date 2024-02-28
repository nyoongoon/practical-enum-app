package goal.in.next.demo.point;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;

    @Transactional
    public void subtractPoint(Long id, Long point){
        Point post = findPost(id);
        if(post.getPoints() - point >= 0){ //차감결과가 0 이상인 경우
            post.substractPoint(point);
        }
    }

    public Point findPost(Long id) {
        return pointRepository.findById(id).orElseThrow();
    }
}
