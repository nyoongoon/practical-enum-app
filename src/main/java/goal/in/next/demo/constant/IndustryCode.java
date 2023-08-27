package goal.in.next.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IndustryCode {
    TECHNOLOGY("IC000001"),
    FINANCE("IC000002"),
    HEALTHCARE("IC000003");

    private String code;
}
