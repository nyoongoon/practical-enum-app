package goal.in.next.demo._employee;

import goal.in.next.demo._employee.EmployeeDetail;
import goal.in.next.demo._employee.EmployeeDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, EmployeeDetailId> {
    Optional<EmployeeDetail> findEmployeeDetailByName(String name);
}
