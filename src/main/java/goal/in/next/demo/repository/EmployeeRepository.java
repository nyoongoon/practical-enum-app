package goal.in.next.demo.repository;

import goal.in.next.demo.entity.Employee;
import goal.in.next.demo.entity.EmployeeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, EmployeeId> {
}
