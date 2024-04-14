package goal.in.next.demo.service;

import goal.in.next.demo.parent.Child;
import goal.in.next.demo.parent.Parent;
import goal.in.next.demo.parent.ChildRepository;
import goal.in.next.demo.parent.ParentRepository;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class ParentServiceTest {
    @Autowired
    ParentRepository parentRepository;
    @Autowired
    ChildRepository childRepository;
    @Autowired
    private MockMvc mockMvc;

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