package goal.in.next.demo.point;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;


//@WebMvcTest
@AutoConfigureMockMvc // @SpringBootTest와 함께 MockMvc 주입받고 싶을 때
@SpringBootTest
class PointControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    PointRepository pointRepository;

    @Test
    public void 포인트_조회_요청() throws Exception {
        Point point = pointRepository.save(new Point(10000L));

        mockMvc.perform(MockMvcRequestBuilders.get("/points/{id}", point.id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        assertThat(point.getPoints()).isEqualTo(10000L);
    }

    @Test
    public void 포인트_차감_동시_요청_1() throws Exception {
        Point point = pointRepository.save(new Point(10000L));

        int n = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i = 1; i <= n; i++) {
            executorService.execute(() -> {
                try {
                    mockMvc.perform(MockMvcRequestBuilders.get("/points/{id}/{point}", point.id, 1000L)
                                    .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(MockMvcResultMatchers.status().isOk());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException("예외 발생");
                } finally {
                    countDownLatch.countDown();
                }
            });
            Thread.sleep(1000);
        }
        countDownLatch.await(); // 요청 동시 요청
        System.out.println(point);

        System.out.println("==========");
        System.out.println(pointRepository.findById(point.getId()));
    }

    @Test
    public void 포인트_차감_동시_요청_2() throws Exception {
        Point point = pointRepository.save(new Point(10000L));

        int n = 50;
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i = 1; i <= n; i++) {
            executorService.execute(() -> {
                try {
                    mockMvc.perform(MockMvcRequestBuilders.get("/points/{id}/{point}", point.id, 1000L)
                                    .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(MockMvcResultMatchers.status().isOk());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException("예외 발생");
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(); // 요청 동시 요청
        System.out.println(point);

        System.out.println("==========");
        System.out.println(pointRepository.findById(point.getId()).orElseThrow());
    }
}