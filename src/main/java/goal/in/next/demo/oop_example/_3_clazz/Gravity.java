package goal.in.next.demo.oop_example._3_clazz;

public final class Gravity {
    // 중력 상수
    static final double GRAVITY_CONSTANT = 9.8;
    // 지구에서의 무게 구하기
    static double getWeightOnEarth(double mess){
        return mess * GRAVITY_CONSTANT;
    }
}
