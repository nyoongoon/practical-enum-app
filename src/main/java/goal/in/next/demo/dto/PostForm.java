package goal.in.next.demo.dto;

import goal.in.next.demo.constant.CategoryCode;
import goal.in.next.demo.constant.DeleteType;
import goal.in.next.demo.constant.SomeCode;
import goal.in.next.demo.entity.Post;
import goal.in.next.demo.utils.EnumUtils;

import java.time.LocalDateTime;

public record PostForm(
        SomeCode someCode,

        String title,

        String content,

        CategoryCode categoryCode

) {

    public Post toEntity(){
        return Post.builder()
                .someCode(someCode)
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .categoryCode(categoryCode)
                .deleteType(DeleteType.N)
                .build();
    }

}
