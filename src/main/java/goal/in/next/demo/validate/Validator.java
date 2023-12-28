package goal.in.next.demo.validate;

import goal.in.next.demo.constant.CategoryCode;
import goal.in.next.demo.dto.PostForm;
import org.springframework.stereotype.Component;

import java.util.List;

import static goal.in.next.demo.constant.CategoryCode.*;
import static goal.in.next.demo.utils.EnumUtils.hasCode;
import static goal.in.next.demo.utils.EnumUtils.isEqual;

@Component
public class Validator {

    public void validateCategory(PostForm postForm) {
        CategoryCode categoryCode = postForm.categoryCode();

        if (hasCode(
                List.of(CUSTOMER_INQUIRY, REFUND_INQUIRY, TECH_SUPPORT), categoryCode)) {

            throw new IllegalArgumentException("잘못된 카테고리입니다");
        }

        if(isEqual(PRODUCT_REVIEWS, categoryCode)){

            throw new IllegalStateException("이상한 카테고리입니다");
        }
    }

    public void validateExpenditureCode(PostForm postForm) {

    }
}
