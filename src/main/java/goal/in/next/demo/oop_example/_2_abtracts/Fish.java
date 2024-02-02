package goal.in.next.demo.oop_example._2_abtracts;

import goal.in.next.demo.oop_example._1_interfaces.BreathingUnderwater;
import goal.in.next.demo.oop_example._1_interfaces.Eating;

abstract public class Fish implements BreathingUnderwater, Eating {
    boolean isSeeFish;
    // 멤버 변수를 갖을 수 있으나 생성자를 직접사용하지 못하고 상속을 통한 일반 클래스에서 초기화 가능
    // 상속을 통하여 상태를 갖을 수 있다.
    public Fish(boolean isSeeFish) {
        this.isSeeFish = isSeeFish;
    }
}
