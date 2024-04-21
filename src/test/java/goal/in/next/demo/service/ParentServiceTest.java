package goal.in.next.demo.service;

import goal.in.next.demo._parent.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class ParentServiceTest {
    @Autowired
    EntityManager entityManager;
    @Autowired
    ParentRepository parentRepository;
    @Autowired
    ChildRepository childRepository;
    @Autowired
    ParentService parentService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    void persistenceCascadeExample() throws Exception {
        Parent parent = new Parent("부모"); //비영속(new) 상태의 엔티티(new)
        Parent save = parentRepository.save(parent);//save()호출하면 엔티티는 영속상태(persist)가 되어 영속성 컨텍스트의 관리를 받는다.

        Child child = new Child("자식");
        child.setParent(parent); // parent와 child를 연관관계 세팅시킴 -> 자식으로 인식하여 영속성 전이 발생 예정

        entityManager.flush(); //플러시 강제 발생 (트랜잭션 끝나는 상황 가정) --> 영속성 컨텍스트가 자식 엔티티도 저장하는 시점

        Parent foundParent = parentRepository.findById(parent.getId()).orElseThrow(() -> new IllegalArgumentException("부모엔티티 존재하지 않음"));
        assertThat(foundParent).isEqualTo(parent);
        Child foundChild = childRepository.findById(child.getId()).orElseThrow(() -> new IllegalArgumentException("자식엔티티 존재하지 않음"));
        assertThat(foundChild).isEqualTo(child);
    }


    @Test
    @Transactional
    void persistenceExample() throws Exception {
        Parent parent = new Parent("부모"); //비영속(new) 상태의 엔티티(new)
        Parent save = parentRepository.save(parent);//save()호출하면 엔티티는 영속상태(persist)가 되어 영속성 컨텍스트의 관리를 받는다.
        assertThat(save).isEqualTo(parent); // 정답은 같다입니다.
    }

    @Test
    @Transactional
    void test1() throws Exception {
        Parent parent = new Parent("부모");
        parentRepository.save(parent); // 부모 먼저 insert해도 상관 없음


        Child child = new Child("자식");
        child.setParent(parent);


        // Parent에서 케스케이스 옵션 없으면 아래 테스트 실패
        assertThat(childRepository.count()).isEqualTo(1L); // 테스트 성공
    }


    @Test
    @Transactional
    void test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/insert")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        assertThat(1L).isEqualTo(parentRepository.count());
        Parent parent = parentRepository.findAll().get(0);
        Assertions.assertEquals("부모입니다.", parent.getName());
        assertThat(1L).isEqualTo(childRepository.count());
        Child child = childRepository.findAll().get(0);
        Assertions.assertEquals("자식입니다.", child.getName());

        //연관관계로 조회 -> 트랜잭션으로 묶여야함
        Assertions.assertEquals("자식입니다.", parent.getChildren().get(0).getName());

        //하지만 response에 childId는 찍히지 않음 -> 플러시 되고 나서 id가 생기기 떄문..
    }
}