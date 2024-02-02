package goal.in.next.demo.oop_example._3_clazz;

import goal.in.next.demo.oop_example._2_abtracts.Fish;

public class GoldenFish extends Fish {
    //추상클래스가 상태를 갖으려면 상속을 통해 인스턴스 되어야함!
    public GoldenFish(boolean isSeeFish) {
        super(isSeeFish);
    }

    @Override
    public void respire() {}

    @Override
    public void openGills() {}

    @Override
    public void dive() {}

    @Override
    public void bite() {}

    @Override
    public void digest() {}
}
