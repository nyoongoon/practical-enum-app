package goal.in.next.demo._parent;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "child")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Child(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY) //연관관계프록시지연설정
    @JoinColumn(name = "parent_id") //연관관계 주인설정
    private Parent parent;
    //연관관계 편의 메소드
    public void setParent(Parent parent) {
        if (this.parent != null) {
            this.parent.getChildren().remove(this);
        }
        this.parent = parent;
        parent.getChildren().add(this);
    }
}
