package goal.in.next.demo.oop_example._2_abtracts;

import goal.in.next.demo.oop_example._1_interfaces.Breathing;
import goal.in.next.demo.oop_example._1_interfaces.Eating;
import goal.in.next.demo.oop_example._1_interfaces.Running;

abstract public class Mammal implements Breathing, Eating, Running { //포유
    String name;

    void breastfeed(){
        System.out.println("쪽쪽");
        // 젖을 먹이다.. (공통)
        // 인터페이스 보다는 추상화의 정도가 작지요..?
    }
    public abstract void bark(); // 짖는 방법은 다 다릅니다..

    @Override
    public void respire() {}

    @Override
    public void bite() {}

    @Override
    public void digest() {}

    @Override
    public void bendKnees() {}

    @Override
    public void jump() {}
}
