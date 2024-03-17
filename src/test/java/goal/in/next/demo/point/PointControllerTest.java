package goal.in.next.demo.point;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

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
    public void 포인트_차감_단건_요청() throws Exception {
        Point point = pointRepository.save(new Point(10000L));

        mockMvc.perform(MockMvcRequestBuilders.post("/points/{id}/{point}", point.id, 1000L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1)) //성공
                .andExpect(MockMvcResultMatchers.jsonPath("$.points").value(9000L)); //성공
    }

    @Test
    public void 포인트_차감_순차_요청_1() throws Exception {
        Point point = pointRepository.save(new Point(10000L));

        int n = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i = 1; i <= n; i++) {
            executorService.execute(() -> {
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
            Thread.sleep(1000);
        }
        countDownLatch.await(); // 요청 동시 요청
        System.out.println(point);

        System.out.println("==========");
        System.out.println(pointRepository.findById(point.getId()));
    }

    @Test
    public void 포인트_차감_동시_요청_1() throws Exception {
        Point point = pointRepository.save(new Point(10000L));

        int n = 50;
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
//      현재 래치의 개수가 0이면 메서드 즉시 반환
        countDownLatch.await(); // 요청 동시 요청 // 해당 코드 없으면 스레드 멈춤

        System.out.println("====================");
        System.out.println(pointRepository.findById(point.getId()).orElseThrow());
    }

    @Test
    public void test1(){

    }


    @Test
    public void 포인트_차감_동시_요청_2() throws Exception { // ExecutorService, CountDownLatch 기능 분석...
        Point point = pointRepository.save(new Point(10000L));

        int n = 50; // 스레드 풀의 크기
        ExecutorService executorService = Executors.newFixedThreadPool(n); // 고정된 크기의 스레드 풀 생성
        CountDownLatch countDownLatch = new CountDownLatch(n); // CountDownLatch 생성

        // 동시에 실행될 작업 정의
        Runnable task = () -> {
            try {
                System.out.println("작업 시작");
                mockMvc.perform(MockMvcRequestBuilders.post("/points/{id}/{point}", point.id, 1000L)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk());
                System.out.println("작업 완료");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown(); // 작업이 완료되면 countDownLatch의 카운트 감소
            }
        };

        // n개의 작업을 스레드 풀에 제출
        System.out.println("n개의 작업을 스레드 풀에 제출");
        for (int i = 0; i < n; i++) {
            executorService.submit(task);
        }

        // 모든 작업이 완료될 때까지 기다림
        try {
            System.out.println("현재 스레드 대기...");
            countDownLatch.await(); // countDownLatch가 0이 될 때까지 기다림
            System.out.println("모든 작업 완료");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 스레드 풀 종료
        executorService.shutdown();


        System.out.println("====================");
        System.out.println(pointRepository.findById(point.getId()).orElseThrow());
    }

    @Test
    public void 포인트_차감_동시_요청_3()
            throws InterruptedException {

        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch countDownLatch = new CountDownLatch(5);
        List<Thread> workers = Stream
                .generate(() -> new Thread(new Worker(outputScraper, countDownLatch)))
                .limit(5)
                .toList();

        workers.forEach(Thread::start);
        countDownLatch.await();
        outputScraper.add("Latch released");

        assertThat(outputScraper)
                .containsExactly(
                        "Counted down",
                        "Counted down",
                        "Counted down",
                        "Counted down",
                        "Counted down",
                        "Latch released"
                );
    }

    @Test
    public void 포인트_차감_동시_요청_4()
            throws InterruptedException {

        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch readyThreadCounter = new CountDownLatch(5);
        CountDownLatch callingThreadBlocker = new CountDownLatch(1);
        CountDownLatch completedThreadCounter = new CountDownLatch(5);
        List<Thread> workers = Stream
                .generate(() -> new Thread(new WaitingWorker(
                        outputScraper, readyThreadCounter, callingThreadBlocker, completedThreadCounter)))
                .limit(5)
                .toList();

        workers.forEach(Thread::start);
        readyThreadCounter.await();
        outputScraper.add("Workers ready");
        callingThreadBlocker.countDown();
        completedThreadCounter.await();
        outputScraper.add("Workers complete");

        assertThat(outputScraper)
                .containsExactly(
                        "Workers ready",
                        "Counted down",
                        "Counted down",
                        "Counted down",
                        "Counted down",
                        "Counted down",
                        "Workers complete"
                );
    }

    public class Worker implements Runnable {
        private List<String> outputScraper;
        private CountDownLatch countDownLatch;

        public Worker(List<String> outputScraper, CountDownLatch countDownLatch) {
            this.outputScraper = outputScraper;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
//                doSomeWork();
            outputScraper.add("Counted down");
            countDownLatch.countDown();
        }
    }

    public class WaitingWorker implements Runnable {

        private List<String> outputScraper;
        private CountDownLatch readyThreadCounter;
        private CountDownLatch callingThreadBlocker;
        private CountDownLatch completedThreadCounter;

        public WaitingWorker(
                List<String> outputScraper,
                CountDownLatch readyThreadCounter,
                CountDownLatch callingThreadBlocker,
                CountDownLatch completedThreadCounter) {

            this.outputScraper = outputScraper;
            this.readyThreadCounter = readyThreadCounter;
            this.callingThreadBlocker = callingThreadBlocker;
            this.completedThreadCounter = completedThreadCounter;
        }

        @Override
        public void run() {
            readyThreadCounter.countDown();
            try {
                callingThreadBlocker.await();
//                doSomeWork();
                outputScraper.add("Counted down");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                completedThreadCounter.countDown();
            }
        }
    }

    @Test
    public void 스레드_객체_테스트(){
        SubstractRequestThread thread1 = new SubstractRequestThread();
        thread1.start(); // 스레드1 병렬 실행

        SubstractRequest substractRequest = new SubstractRequest();
        Thread thread2 = new Thread(substractRequest);
        thread2.start();; // 스레드2 병렬 실행


    }