package goal.in.next.demo.service;

import goal.in.next.demo.constant.ExpenditureCode;
import goal.in.next.demo.constant.IndustryCode;
import goal.in.next.demo.constant.SomeCode;
import goal.in.next.demo.dto.PostForm;
import goal.in.next.demo.entity.Post;
import goal.in.next.demo.entity.PostHistory;
import goal.in.next.demo.entity.PostId;
import goal.in.next.demo.repository.PostHistoryRepository;
import goal.in.next.demo.repository.PostRepository;
import goal.in.next.demo.validate.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class PostService {

    private final Validator validator;
    private final PostRepository postRepository;
    private final PostHistoryRepository postHistoryRepository;

    @Transactional
    public void insertPost() {
        Post post = new Post(new Random().nextLong(Long.MAX_VALUE), "post입니다.");
        Post saved = postRepository.save(post);

        PostHistory postHistory = new PostHistory(saved.getPostNo(), "history입니다.");
        postHistoryRepository.save(postHistory); // error
    }


//    public void weirdBusinessLogic(PostForm postForm) {
//        validator.validateExpenditureCode(postForm);
//
//        Post post = postForm.toEntity();
//
//        if (post.getExpenditureCode().equals(ExpenditureCode.FOOD)) {
//            post.updateIndustryCode(IndustryCode.HEALTHCARE);
//        }
//
//    }

    @Transactional
    public void deletePost(PostId postId) {
        Post foundPost = postRepository.findById(postId).orElseThrow();
        foundPost.deletePost();
    }
}
