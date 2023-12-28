package goal.in.next.demo.service;

import goal.in.next.demo.constant.CategoryCode;
import goal.in.next.demo.constant.DeleteType;
import goal.in.next.demo.constant.SomeCode;
import goal.in.next.demo.dto.PostForm;
import goal.in.next.demo.entity.Post;
import goal.in.next.demo.entity.PostId;
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
                SomeCode.THING,
                "테스트 제목",
                "테스트 내용",
                CategoryCode.CUSTOMER_INQUIRY);
        Post post = postForm.toEntity();

        assertThat(postForm.title()).isEqualTo(post.getTitle());
        assertThat(postForm.content()).isEqualTo(post.getContent());
        assertThat(postForm.categoryCode()).isEqualTo(post.getCategoryCode());
    }

    @Test
    @Rollback(value = false)
    public void 포스트_저장_테스트(){
        Post post = Post.builder()
                .someCode(SomeCode.THING)
                .title("테스트 제목")
                .content("테스트 내용")
                .createdAt(LocalDateTime.now())
                .categoryCode(CategoryCode.CUSTOMER_INQUIRY)
                .deleteType(DeleteType.N)
                .build();
        Post savedPost = postRepository.save(post);

        assertThat(savedPost.getCategoryCode()).isEqualTo(CategoryCode.CUSTOMER_INQUIRY);
    }


    @Test
    public void deletePostTest() {
        Post post = Post.builder()
                .someCode(SomeCode.THING)
                .title("테스트 제목")
                .content("테스트 내용")
                .createdAt(LocalDateTime.now())
                .categoryCode(CategoryCode.CUSTOMER_INQUIRY)
                .deleteType(DeleteType.N)
                .build();
        Post savedPost = postRepository.save(post);

        assertThat(savedPost.getDeleteType()).isEqualTo(DeleteType.N);

        PostId savedPostId = new PostId(savedPost.getId(), savedPost.getSomeCode());
        postService.deletePost(savedPostId);

        Post foundPost = postRepository.findById(savedPostId).orElseThrow();
        assertThat(foundPost.getDeleteType()).isEqualTo(DeleteType.Y);
    }
}