package goal.in.next.demo.entity;

import goal.in.next.demo.constant.SomeCode;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostHistoryId implements Serializable {
    @Column(name="post_history_no")
    private Long postHistoryNo;

    @Column(name="post_no")
    private Long postNo;

    @Column(name="some_code")
    private SomeCode someCode;
}
