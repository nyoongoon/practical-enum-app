package goal.in.next.demo.service;

import goal.in.next.demo.constant.ExpenditureCode;
import goal.in.next.demo.constant.IndustryCode;
import goal.in.next.demo.constant.SomeCode;
import goal.in.next.demo.dto.PostForm;
import goal.in.next.demo.entity.PostHistory;
import goal.in.next.demo.entity.Post;
import goal.in.next.demo.entity.PostId;
import goal.in.next.demo.repository.CommentRepository;
import goal.in.next.demo.repository.PostRepository;
import goal.in.next.demo.validate.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final Validator validator;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void insertPost(PostForm postForm) {
//        validator.validateCategory(postForm);
//        Post post = postForm.toEntity();
        Post post = Post.builder()
                .someCode(SomeCode.SOME)
                .content("post입니다.")
                .build();
        Post saved = postRepository.save(post);

        PostHistory postHistory = PostHistory.builder()
                .postNo(saved.getPostNo())
                .someCode(saved.getSomeCode())
                .content("history 입니다.")
                .build();
        commentRepository.save(postHistory);


        System.out.println("inserted!");

//        Post savedPost = postRepository.save(post);

//        PostId postId = new PostId(savedPost.getId(), savedPost.getSomeCode());
//        Post foundPost = postRepository.findById(postId).orElseThrow();
    }


    public void weirdBusinessLogic(PostForm postForm) {
        validator.validateExpenditureCode(postForm);

        Post post = postForm.toEntity();

        if (post.getExpenditureCode().equals(ExpenditureCode.FOOD)) {
            post.updateIndustryCode(IndustryCode.HEALTHCARE);
        }

    }

    @Transactional
    public void deletePost(PostId postId) {
        Post foundPost = postRepository.findById(postId).orElseThrow();
        foundPost.deletePost();
    }
}
