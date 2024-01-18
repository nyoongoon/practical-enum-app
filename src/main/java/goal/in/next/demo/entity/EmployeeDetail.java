package goal.in.next.demo.entity;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee_detail")
@NoArgsConstructor
@IdClass(EmployeeDetailId.class)
public class EmployeeDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_detail_seq")
    private Long employeeDetailSeq;

    @Id
    @Column(name = "nick_name")
    private String nickName;

    @Id
    @Column(name = "name")
    private String name; //referenceColumnName 설명하기 위해 추가함

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "nick_name", insertable = false, updatable = false),
            @JoinColumn(name = "name", insertable = false, updatable = false)
//            @JoinColumn(name = "nick_name"),
//            @JoinColumn(name = "name")
    })
    private Employee employee;

    public EmployeeDetail(String nickName, String name) {
        this.nickName = nickName;
        this.name = name;
    }
}

