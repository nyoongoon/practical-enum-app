package goal.in.next.demo.service;

import goal.in.next.demo.dto.PostForm;
import goal.in.next.demo.entity.Post;
import goal.in.next.demo.repository.PostRepository;
import goal.in.next.demo.validate.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final Validator validator;
    private final PostRepository postRepository;


    public void insertPost(PostForm postForm) {
        validator.validateCategory(postForm);

        Post post = postForm.toEntity();
        postRepository.save(post);
    }


    public void weirdBusinessLogic(PostForm postForm) {
        validator.validateExpenditureCode(postForm);

        Post post = postForm.toEntity();

        if (post.getExpenditureCode().equals("CD000001")) {
            post.updateIndustryCode("IC000003");
        }

        postRepository.save(post);
    }


    public void deletePost(long id) {
        Post foundPost = postRepository.findById(id).orElseThrow();
        foundPost.deletePost();
    }
}
