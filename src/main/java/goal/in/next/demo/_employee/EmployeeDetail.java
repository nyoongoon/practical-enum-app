package goal.in.next.demo._employee;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "employee_detail")
@NoArgsConstructor
@IdClass(EmployeeDetailId.class)
public class EmployeeDetail {
    @Id
    @Column(name = "nick_name")
    private String nickName;

    @Id
    @Column(name = "name")
    private String name; //referenceColumnName 설명하기 위해 추가함


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_no")
    private Long employeeNo;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "nick_name", insertable = false, updatable = false),
            @JoinColumn(name = "name", insertable = false, updatable = false)
    })
    private Employee employee;

    public EmployeeDetail(String nickName, String name) {
        this.nickName = nickName;
        this.name = name;
    }

    //연관관계 편의 메소드
    public void setEmployee(Employee employee) {
        if (this.employee != null) {
            this.employee.getEmployeeDetails().remove(this);
        }
        this.employee = employee;
        employee.getEmployeeDetails().add(this);
    }
}

