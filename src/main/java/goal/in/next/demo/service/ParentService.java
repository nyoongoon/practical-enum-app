package goal.in.next.demo.service;

import goal.in.next.demo.entity.Child;
import goal.in.next.demo.entity.Parent;
import goal.in.next.demo.repository.ChildRepository;
import goal.in.next.demo.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
//        child.setParent(parent);
        Child save = childRepository.save(child);
        //select문 나가지 않았는데, 새로운 instance로 parent 생성 --> 1차 캐시로 조회한듯?
//        Parent parent2 = save.getParent();

        //select 안나감
//        List<Child> children = parent.getChildren();
//        int size = children.size();
        //select 안나감 -> 1차캐시?
        Parent parent1 = parentRepository.findById(parent.getId()).orElseThrow();
//        List<Child> children1 = parent1.getChildren();
//
    }

    @Transactional
    public void example2(){
//        Child child = new Child();
//        childRepository.save(child);
        Parent parent = new Parent();
        parent.setName("부모");

        Child child = new Child();
        child.setParent(parent);

        System.out.println(child.getParent().getName());

        Child found = childRepository.findById(child.getId()).orElseThrow();
        System.out.println(found.getParent().getName());
    }

    @Transactional
    public void example(){
        Parent parent = new Parent();
        parentRepository.save(parent);

        Child child = new Child();
        child.setParent(parent); //설정된 외래키 칼럼 필드에 해당하는 참조 변수 설정
        childRepository.save(child); // 해당하는 참조 변수의 키 값을 외래키 칼럼의 값으로 insert하는 sql문 자동으로 추가
    }
}
