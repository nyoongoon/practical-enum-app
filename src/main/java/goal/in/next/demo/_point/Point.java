package goal.in.next.demo._point;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private Long points;

    @Version
    Long version;

    public Point(Long points) {
        this.points = points;
    }

    public void substractPoint(Long point){
        points -= point;
    }
}
