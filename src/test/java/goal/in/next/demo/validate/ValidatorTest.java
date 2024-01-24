package goal.in.next.demo.validate;

import goal.in.next.demo.constant.CategoryCode;
import goal.in.next.demo.constant.SomeCode;
import goal.in.next.demo.dto.PostForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static goal.in.next.demo.constant.CategoryCode.*;
import static goal.in.next.demo.constant.CategoryCode.REFUND_INQUIRY;
import static goal.in.next.demo.constant.CategoryCode.TECH_SUPPORT;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidatorTest {
    @Autowired
    Validator validator;

//    @Test
//    public void 코드_밸리데이터_테스트() {
//        PostForm postForm1 = new PostForm(SomeCode.THING, "title", "content", REFUND_INQUIRY);
//        IllegalArgumentException exception1 =
//                assertThrows(IllegalArgumentException.class, () -> validator.validateCategory(postForm1));
//        assertEquals("잘못된 카테고리입니다", exception1.getMessage());
//
//        PostForm postForm2 = new PostForm(SomeCode.THING, "title", "content", PRODUCT_REVIEWS);
//        IllegalStateException exception2 =
//                assertThrows(IllegalStateException.class, () -> validator.validateCategory(postForm2));
//        assertEquals("이상한 카테고리입니다", exception2.getMessage());
//
//        PostForm postForm3 =
//                new PostForm(SomeCode.THING, "title", "content", DISCUSSIONS);
//        assertDoesNotThrow(() -> validator.validateCategory(postForm3));
//    }
}