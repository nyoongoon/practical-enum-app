package goal.in.next.demo.dto;

import goal.in.next.demo.entity.Post;

import java.time.LocalDateTime;

public record PostForm(
        String title,

        String content,

        String categoryCode
) {

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .categoryCode(categoryCode)
                .build();
    }
}
