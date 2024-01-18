package goal.in.next.demo.repository;

import goal.in.next.demo.entity.EmployeeDetail;
import goal.in.next.demo.entity.EmployeeDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, EmployeeDetailId> {
    Optional<EmployeeDetail> findEmployeeDetailByName(String name);
}
