package goal.in.next.demo.service;

import goal.in.next.demo.constant.DeleteType;
import goal.in.next.demo.dto.PostForm;
import goal.in.next.demo.entity.Post;
import goal.in.next.demo.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostServiceTest {
    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;

    @Test
    public void toInsertEntityTest() {
        PostForm postForm = new PostForm(
                "테스트 제목",
                "테스트 내용",
                "CT000001");
        Post post = postForm.toEntity();

        assertThat(postForm.title()).isEqualTo(post.getTitle());
        assertThat(postForm.content()).isEqualTo(post.getContent());
        assertThat(postForm.categoryCode()).isEqualTo(post.getCategoryCode());
    }


    @Test
    @Rollback(value = false)
    public void deletePostTest() {
        Post post = Post.builder()
                .title("테스트 제목")
                .content("테스트 내용")
                .createdAt(LocalDateTime.now())
                .categoryCode("CT000001")
                .deleteType(DeleteType.N)
                .build();
        Post savedPost = postRepository.save(post);

        assertThat(savedPost.getDeleteType()).isEqualTo(DeleteType.N);

        postService.deletePost(savedPost.getId());

        Post foundPost = postRepository.findById(savedPost.getId()).orElseThrow();
        assertThat(foundPost.getDeleteType()).isEqualTo(DeleteType.Y);
    }
}