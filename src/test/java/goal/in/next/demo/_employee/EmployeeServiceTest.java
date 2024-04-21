package goal.in.next.demo._employee;

import goal.in.next.demo._parent.Child;
import goal.in.next.demo._parent.Parent;
import jdk.internal.org.jline.reader.History;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeDetailRepository employeeDetailRepository;

    @Test
    @Transactional
    public void persistenceExample()
        Employee employee1 = new Employee("닉네임1", "이름1");
        employeeRepository.save(employee1);

        EmployeeDetail employee1_detail = new EmployeeDetail("닉네임1", "이름1");
        employee1_detail.setEmployee(employee1);


        Employee foundEmployee = employeeRepository.findById(new EmployeeId("닉네임1", "이름1")).orElseThrow(() -> new IllegalArgumentException("부모엔티티 존재하지 않음"));
        assertThat(foundEmployee).isEqualTo(employee1);
        EmployeeDetail foundEmployeeDetail = employeeDetailRepository.findById(new EmployeeDetailId("닉네임1", "이름1")).orElseThrow(() -> new IllegalArgumentException("자식엔티티 존재하지 않음"));
        assertThat(foundEmployeeDetail).isEqualTo(employee1_detail);
    }
}