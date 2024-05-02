package goal.in.next.demo._employee;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "employee_detail")
@NoArgsConstructor
@IdClass(EmployeeDetailId.class)
public class EmployeeDetail {
    @Id
    @Column(name = "nick_name")
    private String nickName;
    @Id
    @Column(name = "name")
    private String name;
    @Id
    @Column(name = "email")
    private String email;


    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "nick_name", referencedColumnName = "nick_name", insertable = false, updatable = false),
            @JoinColumn(name = "name", referencedColumnName = "name", insertable = false, updatable = false)
    })
    private Employee employee;

    public EmployeeDetail(String nickName, String name, String email) {
        this.nickName = nickName;
        this.name = name;
        this.email = email;
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

