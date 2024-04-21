package goal.in.next.demo._employee;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "employee")
@Getter
@NoArgsConstructor
@IdClass(EmployeeId.class)
@Setter
public class Employee {

    @Id
    @Column(name = "nick_name")
    private String nickName;

    @Id
    @Column(name = "name")
    private String name;

    public Employee(String nickName, String name) {
        this.nickName = nickName;
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.PERSIST)
    List<EmployeeDetail> employeeDetails = new ArrayList<>();
}
