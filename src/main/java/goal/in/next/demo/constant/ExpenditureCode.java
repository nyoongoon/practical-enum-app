package goal.in.next.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExpenditureCode {
    FOOD("EC000001"),
    TRANSPORTATION("EC000002"),
    HOUSING("EC00000");

    private String code;
}
