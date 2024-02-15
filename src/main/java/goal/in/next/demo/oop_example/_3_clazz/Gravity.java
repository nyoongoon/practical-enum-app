package goal.in.next.demo.oop_example._3_clazz;

public final class Gravity {
    // 중력 상수
    static final double GRAVITY_CONSTANT = 9.8;
    // 대상의 무게 구하기
    static double getWeight(Object object){
        return object.mess * GRAVITY_CONSTANT;
    }

    class Apple {
        double mess = 0.5;
    }
    class Banana {
        double mess = 0.3;
    }
    class BalanceScale{
        Object left;
        Object right;

        Object putObjectsOnScale(Object left, Object right){
            if(left.mess > right.mess){
                return left;
            }else{
                return right;
            }
        }
    }

    public void main(String[] args) {

        Object apple = new Apple();
        Object banana = new Banana();
        Object heavierObject;

        // 사과와 바나나의 무게가 같지 않을 때
        // 절차지향의 세계
        double weight1 = Gravity.getWeight(apple);
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
        Object heavierObject = balanceScale.putObjectsOnScale(apple, banana);
        System.out.println(heavierObject + "가 더 무거움");
    }
}
