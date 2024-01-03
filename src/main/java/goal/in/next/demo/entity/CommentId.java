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
public class CommentId implements Serializable {
    private Long id;

    @Column(name="post_id")
    private Long postId;

    @Column(name="some_code")
    private SomeCode someCode;
}
