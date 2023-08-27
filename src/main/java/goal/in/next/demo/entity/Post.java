package goal.in.next.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "post")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name="content")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "delete_type")
    private String deleteType;

    @Column(name = "expenditure_code")
    private String expenditureCode;

    @Column(name = "industry_code")
    private String industryCode;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentsList;

    public void deletePost(){
        this.deleteType = "Y";
    }

    public void updateIndustryCode(String code){
        this.industryCode = code;
    }
}
