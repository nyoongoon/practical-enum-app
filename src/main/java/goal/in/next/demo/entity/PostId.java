package goal.in.next.demo.entity;


import goal.in.next.demo.constant.CategoryCodeConverter;
import goal.in.next.demo.constant.SomeCode;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostId implements Serializable {
    @Column(name="id")
    private Long id;

    @Column(name="some_code")
    private SomeCode someCode;
}
