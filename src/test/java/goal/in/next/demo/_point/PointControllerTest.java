package goal.in.next.demo._point;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
    public void 포인트_차감_동시_요청_5() throws Exception { // 준비, 요청, 완료 카운트다운래치로 동시 요청
        class WaitingWorker implements Runnable {

            private Point point;
            private CountDownLatch readyThreadCounter;
            private CountDownLatch callingThreadBlocker;
            private CountDownLatch completedThreadCounter;

            public WaitingWorker(
                    Point point,
                    CountDownLatch readyThreadCounter,
                    CountDownLatch callingThreadBlocker,
                    CountDownLatch completedThreadCounter) {
                this.point = point;
                this.readyThreadCounter = readyThreadCounter;
                this.callingThreadBlocker = callingThreadBlocker;
                this.completedThreadCounter = completedThreadCounter;
            }

            @Override
            public void run() {
                readyThreadCounter.countDown();
                try {
                    callingThreadBlocker.await(); //요청 대기
                    mockMvc.perform(MockMvcRequestBuilders.post("/points/{id}/{point}", point.id, 1L)
                                    .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(MockMvcResultMatchers.status().isOk());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    completedThreadCounter.countDown();
                }
            }
        }

        Point point = pointRepository.save(new Point(10000L)); // 포인트저장

        int n = 1000; // 스레드 풀의 크기
        ExecutorService executorService = Executors.newFixedThreadPool(n); // 고정된 크기의 스레드 풀 생성
        CountDownLatch readyThreadCounter = new CountDownLatch(n); //준비 카운트
        CountDownLatch callingThreadBlocker = new CountDownLatch(1); //요청 카운트
        CountDownLatch completedThreadCounter = new CountDownLatch(n); //완료 카운트
        // 동시에 실행될 작업 정의
        Runnable task = new WaitingWorker(
                point, readyThreadCounter, callingThreadBlocker, completedThreadCounter);

        // n개의 작업을 스레드 풀에 제출  --> 작업과 실행의 분리!
        System.out.println("n개의 작업을 스레드 풀에 제출");
        for (int i = 0; i < n; i++) {
            executorService.submit(task); // 실행
        }

        readyThreadCounter.await(); // 모든 작업 준비 될 때까지 대기
        callingThreadBlocker.countDown(); // 준비 완료 시 요청 count 0으로 호출 -> 요청 동시에 n개 호출
        completedThreadCounter.await(); // 모든 작업 완료 될 때까지 대기
        System.out.println("모든 작업 완료");
        System.out.println("====================");
        Point after = pointRepository.findById(point.getId()).orElseThrow();
        System.out.println(after);
        assertThat(8000L).isEqualTo(after.getPoints());
    }


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
    public void 포인트_차감_단건_요청() throws Exception {
        Point point = pointRepository.save(new Point(10000L));

        mockMvc.perform(MockMvcRequestBuilders.post("/points/{id}/{point}", point.id, 1000L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1)) //성공
                .andExpect(MockMvcResultMatchers.jsonPath("$.points").value(9000L)); //성공

        System.out.println("==========");
        System.out.println(pointRepository.findById(point.getId()));
    }

    @Test
    void 스레드풀_사용한_동시요청() throws InterruptedException {
        int n = 2000;
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        Point point = pointRepository.save(new Point(10000L));


        for (int i = 1; i <= n; i++) {
            executorService.execute(() -> {
                try {
                    mockMvc.perform(MockMvcRequestBuilders.post("/points/{id}/{point}", point.id, 1000L)
                                    .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(MockMvcResultMatchers.status().isOk())
                            .andDo(MockMvcResultHandlers.print());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException("예외 발생");
                }
            });
        }
        // 작업이 모두 완료될 때까지 대기
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // 기다림
        }

        System.out.println("모든 작업이 완료되었습니다.");
        System.out.println("====================");
        System.out.println(pointRepository.findById(point.getId()).orElseThrow());
    }


    @Test
    public void 포인트_차감_동시_요청_1() throws Exception {
        Point point = pointRepository.save(new Point(10000L));

        int n = 2000;
        ExecutorService executorService = Executors.newFixedThreadPool(n); // 고정된 크기의 스레드풀 생성
        CountDownLatch countDownLatch = new CountDownLatch(n); //동시에 여러 스레드가 작업을 완료할 때까지 기다릴 수 있는 수
        for (int i = 1; i <= n; i++) {
            executorService.execute(() -> { // 스레드를 하나씩 할당해서 비동기적으로 실행하도록 한다
                try {
                    mockMvc.perform(MockMvcRequestBuilders.post("/points/{id}/{point}", point.id, 1000L)
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
//      스레드가 중단되지 않는 한 래치가 0으로 카운트다운될 때까지 현재 스레드가 대기
//      현재 래치의 개수가 0이면 메서드 즉시 종료
        countDownLatch.await(); // 요청 동시 요청

        System.out.println("====================");
        System.out.println(pointRepository.findById(point.getId()).orElseThrow());
    }


    @Test
    public void 포인트_차감_동시_요청_2() throws Exception { // Executor를 통한 작업과 실행 분리 적용
        Point point = pointRepository.save(new Point(10000L));

        int n = 2000; // 스레드 풀의 크기
        ExecutorService executorService = Executors.newFixedThreadPool(n); // 고정된 크기의 스레드 풀 생성
        CountDownLatch countDownLatch = new CountDownLatch(n); // CountDownLatch 생성

        // 동시에 실행될 작업 정의
        Runnable task = () -> {
            try {
                mockMvc.perform(MockMvcRequestBuilders.post("/points/{id}/{point}", point.id, 1000L)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown(); // 작업이 완료되면 countDownLatch의 카운트 감소
            }
        };

        // n개의 작업을 스레드 풀에 제출  --> 작업과 실행의 분리!
        System.out.println("n개의 작업을 스레드 풀에 제출");
        for (int i = 0; i < n; i++) {
            executorService.submit(task); // 실행
        }

        // 모든 작업이 완료될 때까지 기다림
        System.out.println("현재 스레드 대기...");
        countDownLatch.await(); // countDownLatch가 0이 될 때까지 기다림
        System.out.println("모든 작업 완료");

        System.out.println("====================");
        System.out.println(pointRepository.findById(point.getId()).orElseThrow());
    }
}