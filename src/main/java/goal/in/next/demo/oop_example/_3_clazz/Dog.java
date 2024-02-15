package goal.in.next.demo.oop_example._3_clazz;

import goal.in.next.demo.oop_example._2_abtracts.Mammal;

public class Dog extends Mammal {
    public String color;

    public Dog(String color) {
        this.color = color;
    }

    @Override
    public void bark() {
        System.out.println("멍멍");
    }
    // 나머지 인터페이스의 기능은 Mammal에서 정의해줄 수 있습니다..
}
