package goal.in.next.demo._point;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Long> {
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Point> findById(Long id);
}
