package goal.in.next.demo.oop_example._3_clazz;

public final class Gravity { //중력을 무엇으로 상속할까... 추상화, 상속, 다형성 불가.. !
    // 중력 상수 - 중력의 성질을 나타내지만, 어떤 대상의 순간적인 상태 나타내는 것이 아님!
    static final double GRAVITY_CONSTANT = 9.8; // 상수만 있을 뿐, 상태 없음!
    // 대상의 무게 구하기
    static double getWeight(Object object){
//        return object.mess * GRAVITY_CONSTANT;
        return GRAVITY_CONSTANT;
    }

    class Apple {
        double mess = 0.5;
    }
    class Banana {
        double mess = 0.3;
    }


    public void main(String[] args) {

        Object apple = new Apple();
        Object banana = new Banana();
        Object heavierObject;

        // 사과와 바나나의 무게가 같지 않을 때
        // 절차지향의 세계
        double weight1 = Gravity.getWeight(apple); //소스코드 자체에 대한 수정이 필요
        double weight2 = Gravity.getWeight(banana);
        double heavierWeight;

        if(weight1 > weight2){
            heavierWeight = weight1;
        }else{
            heavierWeight = weight2;
        }

        if(Gravity.getWeight(apple) == heavierWeight){
            heavierObject = apple;
        }else{
            heavierObject = banana;
        }
        System.out.println(heavierObject + "가 더 무거움");


        // 객체지향의 세계
        BalanceScale balanceScale = new BalanceScale();
        balanceScale.putObjectsOnScale(apple, banana);
        heavierObject = balanceScale.getObjectOnScaleDown();
        System.out.println(heavierObject + "가 더 무거움");
    }
}
