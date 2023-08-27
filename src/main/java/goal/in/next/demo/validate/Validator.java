package goal.in.next.demo.validate;

import goal.in.next.demo.constant.CategoryCode;
import goal.in.next.demo.dto.PostForm;
import org.springframework.stereotype.Component;

import static goal.in.next.demo.constant.CategoryCode.*;

@Component
public class Validator {

    public void validateCategory(PostForm postForm) {
        String categoryCode = postForm.categoryCode();

        if (categoryCode.equals(CUSTOMER_INQUIRY.getCode()) ||
                categoryCode.equals(FREE_BOARD.getCode()) ||
                categoryCode.equals(REFUND_INQUIRY.getCode()) ||
                categoryCode.equals(TECH_SUPPORT.getCode())) {

            throw new IllegalArgumentException("잘못된 카테고리입니다");
        }
    }

    public void validateExpenditureCode(PostForm postForm) {

    }
}
