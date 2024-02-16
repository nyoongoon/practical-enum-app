package goal.in.next.demo.oop_example._3_clazz;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeightService {
    public final Scale scale; //의존성주입

    class Apple {
        double mess = 0.5;
    }
    class Banana {
        double mess = 0.3;
    }

    public void getHeavierObjectByGravity(){
        Object apple = new Apple();
        Object banana = new Banana();
        Object heavierObject;
        // 사과와 바나나의 무게가 같지 않을 때
        // 절차지향의 세계
        // 입력
        double weight1 = Gravity.getWeight(apple);
        double weight2 = Gravity.getWeight(banana);
        double heavierWeight;
        // 처리
        if(weight1 > weight2){
            heavierWeight = weight1;
        }else{
            heavierWeight = weight2;
        }
        // 출력
        if(Gravity.getWeight(apple) == heavierWeight){
            heavierObject = apple;
        }else{
            heavierObject = banana;
        }
        System.out.println(heavierObject + "가 더 무거움");
    }

    public void getHeavierObjectByScale(){
        Object apple = new Apple();
        Object banana = new Banana();

        // 객체지향의 세계
        scale.putObjectsOnScale(apple, banana); // 저울에 물체를 올려둔다.
        Object heavierObject = scale.getObjectOnScaleDown(); // 더 무거워서 내려간 쪽의 물체 가져오기
        System.out.println(heavierObject + "가 더 무거움");
    }
}
