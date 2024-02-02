package goal.in.next.demo.oop_example._1_interfaces;

public interface Running {
    //모든 인스턴스의 멤버는 불변 상수입니다. (static final) 생략가능
    // 달리는 속도는 음수가 될 수 없다.
    static final boolean IS_SPEED_MINUS = false;
    //광속을 초과할 순 없다
    int MAX_SPEED = 299792458;
    void bendKnees();
    void jump();
}
