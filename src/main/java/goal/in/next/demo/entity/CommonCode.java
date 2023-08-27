package goal.in.next.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "common_code")
public class CommonCode {

    @Id
    private String code;

    private String description;

    // Constructors, getters, setters, and other methods
}
