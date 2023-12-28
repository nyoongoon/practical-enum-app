package goal.in.next.demo.repository;

import goal.in.next.demo.entity.Post;
import goal.in.next.demo.entity.PostId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, PostId> {
}
