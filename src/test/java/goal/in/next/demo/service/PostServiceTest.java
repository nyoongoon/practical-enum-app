package goal.in.next.demo.service;

import goal.in.next.demo.dto.PostForm;
import goal.in.next.demo.entity.Post;
import goal.in.next.demo.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
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
    public void deletePostTest(){
        postService.deletePost(1L);

        Post foundPost = postRepository.findById(1L).orElseThrow();
        assertThat(foundPost.getDeleteType()).isEqualTo("N");
    }
}