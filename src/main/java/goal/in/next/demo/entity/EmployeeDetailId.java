package goal.in.next.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailId implements Serializable {
    @Column(name = "employee_detail_seq")
    private Long employeeDetailSeq;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name="name")
    private String name;
}
