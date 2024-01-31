package goal.in.next.demo.oop_example.abtracts;

import goal.in.next.demo.oop_example.interfaces.Breathing;
import goal.in.next.demo.oop_example.interfaces.Eating;
import goal.in.next.demo.oop_example.interfaces.Running;

abstract public class Mammal implements Breathing, Eating, Running { //포유
    void breastfeed(){
        System.out.println("쪽쪽");
        // 젖을 먹이다.. (공통)
        // 인터페이스 보다는 추상화의 정도가 작지요..?
    }
    public abstract void bark(); // 짖는 방법은 다 다릅니다..

    @Override
    public void respire() {
        // 인터페이스 기능 추상클래스 구현
    }

    @Override
    public void bite() {
        // 인터페이스 기능 추상클래스 구현
    }

    @Override
    public void digest() {
        // 인터페이스 기능 추상클래스 구현
    }

    @Override
    public void bendKnees() {
        // 인터페이스 기능 추상클래스 구현
    }

    @Override
    public void jump() {
        // 인터페이스 기능 추상클래스 구현
    }
}
