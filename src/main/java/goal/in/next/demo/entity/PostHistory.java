package goal.in.next.demo.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_history")
@IdClass(PostHistoryId.class)
@NoArgsConstructor
@ToString(exclude = "post")
public class PostHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_history_no")
    private Long postHistoryNo;

    @Id
    @Column(name = "post_no")
    private Long postNo;

//    @Id
//    @Column(name="some_code")
//    private SomeCode someCode;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "post_no", insertable = false, updatable = false)
    @JoinColumn(name = "post_no")
    private Post post;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumns({
//            @JoinColumn(name = "post_no", insertable = false, updatable = false),
//            @JoinColumn(name = "some_code", insertable = false, updatable = false)
//    })
//    private Post post;


    public PostHistory(Long postNo, String content) {
        this.postNo = postNo;
        this.content = content;
    }
//    public PostHistory(Long postNo, SomeCode someCode, String content) {
//        this.postNo = postNo;
//        this.someCode = someCode;
//        this.content = content;
//    }

//    @Builder
//    public PostHistory(Long postHistoryNo,
//                       Long postNo,
//                       SomeCode someCode,
//                       String content,
//                       LocalDateTime createdAt) {
//        this.postHistoryNo = postHistoryNo;
//        this.postNo = postNo;
//        this.someCode = someCode;
//        this.content = content;
//        this.createdAt = createdAt;
//    }
}
