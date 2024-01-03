package goal.in.next.demo.service;

import goal.in.next.demo.entity.Child;
import goal.in.next.demo.entity.Parent;
import goal.in.next.demo.repository.ChildRepository;
import goal.in.next.demo.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentService {
    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;

    @Transactional
    public void insertParent() {
        Parent parent = new Parent();
        parentRepository.save(parent);

        Child child = new Child();
        child.setParent(parent);
        Child save = childRepository.save(child);
        //select문 나가지 않았는데, 새로운 instance로 parent 생성 --> 1차 캐시로 조회한듯?
        Parent parent2 = save.getParent();

        //select 안나감
        List<Child> children = parent.getChildren();
        int size = children.size();
        //select 안나감 -> 1차캐시?
        Parent parent1 = parentRepository.findById(parent.getId()).orElseThrow();
        List<Child> children1 = parent1.getChildren();
        int size1 = children1.size();
    }
}
