package goal.in.next.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Comments;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "post")
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

    @OneToMany(mappedBy = "post")
    private List<Comment> commentsList;
}
