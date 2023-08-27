package goal.in.next.demo.validate;

import goal.in.next.demo.dto.PostForm;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    public void validateCategory(PostForm postForm) {
        String categoryCode = postForm.categoryCode();

        if (categoryCode.equals("CT000001") ||
                categoryCode.equals("CT000002") ||
                categoryCode.equals("CT000003") ||
                categoryCode.equals("CT000004")) {
            throw new IllegalArgumentException("잘못된 카테고리입니다");
        }
    }

    public void validateExpenditureCode(PostForm postForm) {

    }
}
