package goal.in.next.demo.entity;

import goal.in.next.demo.constant.*;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor
@IdClass(PostId.class)
@ToString(exclude = "postHistoryList")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_no")
    private Long postNo;

    @Id
//    @Enumerated(EnumType.STRING)
    @Column(name="some_code")
    private SomeCode someCode;

//    @Enumerated(EnumType.STRING)
    @Column(name = "category_code")
    private CategoryCode categoryCode;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "delete_type")
    private DeleteType deleteType;

    @Column(name = "expenditure_code")
    private ExpenditureCode expenditureCode;

    @Column(name = "industry_code")
    private IndustryCode industryCode;

    @OneToMany(mappedBy = "post")
    private List<PostHistory> postHistoryList;


    @Builder
    public Post(
            Long postNo,
            SomeCode someCode,
            String title,
            String content,
            LocalDateTime createdAt,
            CategoryCode categoryCode,
            DeleteType deleteType,
            ExpenditureCode expenditureCode,
            IndustryCode industryCode) {
        this.postNo = postNo;
        this.someCode = someCode;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.categoryCode = categoryCode;
        this.deleteType = deleteType;
        this.expenditureCode = expenditureCode;
        this.industryCode = industryCode;
    }

    public void deletePost(){
        this.deleteType = DeleteType.Y;
    }

    public void updateIndustryCode(IndustryCode code){
        this.industryCode = code;
    }
}
