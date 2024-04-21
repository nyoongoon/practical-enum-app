package goal.in.next.demo._employee;

import goal.in.next.demo._employee.Employee;
import goal.in.next.demo._employee.EmployeeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, EmployeeId> {
}
