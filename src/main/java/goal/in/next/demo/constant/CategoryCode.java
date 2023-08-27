package goal.in.next.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryCode {
    CUSTOMER_INQUIRY("CT000001"),
    FREE_BOARD("CT000002"),
    REFUND_INQUIRY("CT000003"),
    TECH_SUPPORT("CT000004"),
    PRODUCT_REVIEWS("CT000005"),
    DISCUSSIONS("CT000006"),
    USER_MANUALS("CT000007");

    private String code;
}
