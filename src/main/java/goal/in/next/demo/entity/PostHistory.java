package goal.in.next.demo.entity;

import goal.in.next.demo.constant.SomeCode;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_history")
@IdClass(PostHistoryId.class)
@NoArgsConstructor
public class PostHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_history_no")
    private Long postHistoryNo;

    @Id
    @Column(name="post_no")
    private Long postNo;

    @Id
    @Column(name="some_code")
    private SomeCode someCode;


    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "post_no"),
            @JoinColumn(name = "some_code")
    })
    private Post post;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    // Constructors, getters, setters, and other methods

    @Builder
    public PostHistory(Long postHistoryNo,
                       Long postNo,
                       SomeCode someCode,
                       String content,
                       LocalDateTime createdAt) {
        this.postHistoryNo = postHistoryNo;
        this.postNo = postNo;
        this.someCode = someCode;
        this.content = content;
        this.createdAt = createdAt;
    }
}
