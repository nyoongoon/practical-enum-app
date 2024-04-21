package goal.in.next.demo._parent;

import goal.in.next.demo.oop_example._1_interfaces.Eating;
import goal.in.next.demo.oop_example._2_abtracts.Mammal;
import goal.in.next.demo.oop_example._3_clazz.Dog;
import goal.in.next.demo.oop_example._3_clazz.GoldenFish;
import goal.in.next.demo.service.AbsUitilsChild;
import goal.in.next.demo.service.AbsUtils;
import goal.in.next.demo.service.StringUtilsChild;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ParentService {
    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;
    private final AbsUtils absUitilsChild;


    public Child findChild(Long id){
        return childRepository.findById(id).orElseThrow();
    }



    @Transactional
    public void persistenceExample() {
        Parent parent = new Parent("부모"); //비영속(new) 상태의 엔티티(new)
        parentRepository.save(parent); //save()호출하면 엔티티는 영속상태(persist)가 되어 영속성 컨텍스트의 관리를 받는다.
    }




    @Transactional
    public void persistenceExample2() {
        Parent parent = new Parent("부모"); //비영속 상태의 엔티티(new)
        parentRepository.save(parent);





        Child child = new Child("자식");

        child.setParent(parent);
    }


//    @Transactional
//    public ParentResponse insertExample() {
//        Parent parent = new Parent();
////        parent.setName("부모입니다.");
//        parentRepository.save(parent); // 부모 먼저 insert해도 상관 없음
//
//        Child child = new Child();
////        child.setName("자식입니다.");
//
//        child.setParent(parent);
////        childRepository.save(child);
//
////        parentRepository.save(parent); // 부모 나중에 insert해도 상관 없음
//        return new ParentResponse(parent.getId(), child.getId());
//    }
//
//    @Transactional
//    public void example2() {
////        Child child = new Child();
////        childRepository.save(child);
//        Parent parent = new Parent();
////        parent.setName("부모");
//
//        Child child = new Child();
//        child.setParent(parent);
//
//        System.out.println(child.getParent().getName());
//
//        Child found = childRepository.findById(child.getId()).orElseThrow();
//        System.out.println(found.getParent().getName());
//    }
//
//    @Transactional
//    public void example() {
//        GoldenFish myGoldenFish = new GoldenFish(false);
//        Dog myDog = new Dog("brown");
//        Dog yourDog = new Dog("white");
//
//
//        Eating actOfEatingByDog = new Dog("black"); //먹는 행위 중 개의 행위에 주목
//        actOfEatingByDog.bite();
//        actOfEatingByDog.digest();
////        actOfEatingByDog.bark(); 호출 불가능 -> 먹는 행위의 관점에서 짖는 것은 모른다..
////        actOfEatingByDog.color; 무슨 색인지 모름.. -> 상태를 갖을 수 없음
//        Mammal amongMamalsDog = new Dog("yellow"); //포유류 중 개를 주목
//        actOfEatingByDog.bite(); // 먹고
//        amongMamalsDog.respire(); // 숨쉬고
//        amongMamalsDog.bark(); //짖고
////        amongMamalsDog.color; // 그러나 무슨 색인지는 몰라.. -> 상태를 갖을 수 없음
//        Dog badook = new Dog("blue"); //우리 바둑이
//        badook.bite(); //먹고
//        badook.respire(); //숨쉬고
//        badook.bark(); //짖고
//        String colorOfBabook = badook.color; //바둑이는 색깔도 뭔지 알아..
//
//
//        String a = "abc";
////        StringUtils.leftPad();
//
//
//        String upper = AbsUitilsChild.toUpperCase("upper");
//        absUitilsChild.toCharacter("abc");
////        Integer.parseInt();
//
//
//        StringUtils stringUtils = new StringUtils();
////        stringUtils.
//
//
//        AbsUtils.toUpperCase("Abc");
//
//        AbsUitilsChild absUitilsChild = new AbsUitilsChild();
//        absUitilsChild.toLowerCase("abc");
//        absUitilsChild.toInteger("abc");
//        absUitilsChild.toCharacter("abc");
//        StringUtilsChild.toUpperCase("abc");
//        StringUtilsChild abd = new StringUtilsChild();
//
//
//        Parent parent = new Parent();
//        parentRepository.save(parent);
//
//        Child child = new Child();
//        child.setParent(parent); //설정된 외래키 칼럼 필드에 해당하는 참조 변수 설정
//        childRepository.save(child); // 해당하는 참조 변수의 키 값을 외래키 칼럼의 값으로 insert하는 sql문 자동으로 추가
//    }
}
