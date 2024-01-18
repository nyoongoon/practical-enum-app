package goal.in.next.demo.service;

import goal.in.next.demo.dto.EmployeeDto;
import goal.in.next.demo.entity.Employee;
import goal.in.next.demo.entity.EmployeeDetail;
import goal.in.next.demo.entity.EmployeeDetailId;
import goal.in.next.demo.entity.EmployeeId;
import goal.in.next.demo.repository.EmployeeDetailRepository;
import goal.in.next.demo.repository.EmployeeQueryRepository;
import goal.in.next.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeDetailRepository employeeDetailRepository;
    private final EmployeeQueryRepository employeeQueryRepository;

    public void postEmployee(EmployeeDto employeeDto){
        Employee saved = employeeRepository.save(new Employee(employeeDto.getNickName(), employeeDto.getName()));

        // 연관관계는 일단 아직 모르겠고 해당되는 칼럼에 insert 한다..
        employeeDetailRepository.save(new EmployeeDetail(saved.getNickName(), saved.getName())); // success !

        // 하지만 논리적으로는 연관관계가 설정된 것이 맞지 않을까? JPA가 무엇인가를 해주지 않았을까..?
        int size = saved.getEmployeeDetailList().size();// select문 안나감 !
        System.out.println(size);

        Employee found = employeeRepository.findById(new EmployeeId(saved.getNickName(), saved.getName())).orElseThrow();
        size = found.getEmployeeDetailList().size(); // select문 안나감 !
        System.out.println(size);

        // querydsl로 찾아보기 !!!
        Employee foundByQuerydsl = employeeQueryRepository.findById(saved.getNickName(), saved.getName()).orElseThrow();
        size = foundByQuerydsl.getEmployeeDetailList().size();
        System.out.println(size);
    }
}
