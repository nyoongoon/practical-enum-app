package goal.in.next.demo._employee;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmployeeServiceTest {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeDetailRepository employeeDetailRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional
    public void persistenceExample() {
        //given
        Employee employee1 = new Employee("닉네임1", "이름1");
        Employee savedEmployee = employeeRepository.save(employee1);
        EmployeeDetail employee1_detail = new EmployeeDetail("닉네임1", "이름1", "abc@naver.com");
        employee1_detail.setEmployee(savedEmployee); //리턴받은 엔티티로 연관관계 설정
        //when
        entityManager.flush(); //플러시 강제 발생 (트랜잭션 끝나는 상황 가정) --> 영속성 컨텍스트가 자식 엔티티도 저장하는 시점
        EmployeeId parentId = new EmployeeId("닉네임1", "이름1");
        Employee foundEmployee = employeeRepository.findById(parentId)
                .orElseThrow(() -> new IllegalArgumentException("부모엔티티 존재하지 않음"));
        EmployeeDetailId childId = new EmployeeDetailId("닉네임1", "이름1", "abc@naver.com");
        EmployeeDetail foundEmployeeDetail = employeeDetailRepository.findById(childId)
                .orElseThrow(() -> new IllegalArgumentException("자식엔티티 존재하지 않음"));
        //th
        assertThat(savedEmployee).isNotEqualTo(employee1); //파라미터에 전달한 엔티티는 준영속, 리턴받은 엔티티는 영속이라 다른 엔티티 참조값을 가진다.
        assertThat(foundEmployee).isEqualTo(savedEmployee);
        assertThat(foundEmployeeDetail).isEqualTo(employee1_detail);
    }
}