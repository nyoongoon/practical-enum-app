package goal.in.next.demo.repository;

import goal.in.next.demo.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Long> {
}
