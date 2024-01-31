package goal.in.next.demo.oop_example.clazz;

import goal.in.next.demo.oop_example.abtracts.Mammal;

public class Dog extends Mammal {
    @Override
    public void bark() {
        System.out.println("멍멍");
    }
    // 나머지 인터페이스의 기능은 Mammal에서 정의해줄 수 있습니다..
}
