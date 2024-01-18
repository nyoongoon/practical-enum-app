package goal.in.next.demo.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeId implements Serializable {
    @Column(name = "nick_name")
    private String nickName;

    @Column(name="name")
    private String name;
}
