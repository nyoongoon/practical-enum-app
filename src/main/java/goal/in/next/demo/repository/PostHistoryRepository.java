package goal.in.next.demo.repository;

import goal.in.next.demo.entity.PostHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostHistoryRepository extends JpaRepository<PostHistory, Long> {
}
