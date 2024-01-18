package goal.in.next.demo.controller;

import goal.in.next.demo.dto.EmployeeDto;
import goal.in.next.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/employee")
    public void postEmployee(@RequestBody EmployeeDto employeeDto){
        employeeService.postEmployee(employeeDto);
    }
    @GetMapping("/employee")
    public void getEmployee(){
        employeeService.selectEmployee();
    }
}
