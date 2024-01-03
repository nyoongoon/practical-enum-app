package goal.in.next.demo.entity;

import goal.in.next.demo.constant.SomeCode;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@IdClass(CommentId.class)
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
    @Column(name="post_id")
    private Long postId;

    @Id
    @Column(name="some_code")
    private SomeCode someCode;


    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "post_id"),
            @JoinColumn(name = "some_code")
    })
    private Post post;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    // Constructors, getters, setters, and other methods

    public Comment(Post post, String content) {
        this.post = post;
        this.content = content;
    }
}
