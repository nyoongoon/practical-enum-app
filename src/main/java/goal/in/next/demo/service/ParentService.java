package goal.in.next.demo.service;

import goal.in.next.demo.entity.Child;
import goal.in.next.demo.entity.Parent;
import goal.in.next.demo.oop_example._1_interfaces.Diving;
import goal.in.next.demo.oop_example._2_abtracts.Mammal;
import goal.in.next.demo.oop_example._3_clazz.Dog;
import goal.in.next.demo.oop_example._1_interfaces.Eating;
import goal.in.next.demo.oop_example._3_clazz.GoldenFish;
import goal.in.next.demo.repository.ChildRepository;
import goal.in.next.demo.repository.ParentRepository;
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

    @Transactional
    public void insertParent() {

        Parent parent = new Parent();
        parentRepository.save(parent);

        Child child = new Child();
//        child.setParent(parent);
        Child save = childRepository.save(child);
        //select문 나가지 않았는데, 새로운 instance로 parent 생성 --> 1차 캐시로 조회한듯?
//        Parent parent2 = save.getParent();

        //select 안나감
//        List<Child> children = parent.getChildren();
//        int size = children.size();
        //select 안나감 -> 1차캐시?
        Parent parent1 = parentRepository.findById(parent.getId()).orElseThrow();
//        List<Child> children1 = parent1.getChildren();
//
    }

    @Transactional
    public void example2(){
//        Child child = new Child();
//        childRepository.save(child);
        Parent parent = new Parent();
        parent.setName("부모");

        Child child = new Child();
        child.setParent(parent);

        System.out.println(child.getParent().getName());

        Child found = childRepository.findById(child.getId()).orElseThrow();
        System.out.println(found.getParent().getName());
    }

    @Transactional
    public void example(){
        GoldenFish myGoldenFish = new GoldenFish(false);
        Dog myDog = new Dog("brown");
        Dog yourDog = new Dog("white");




        Eating actOfEatingByDog = new Dog("black"); //먹는 행위 중 개의 행위에 주목
        actOfEatingByDog.bite();
        actOfEatingByDog.digest();
//        actOfEatingByDog.bark(); 호출 불가능 -> 먹는 행위의 관점에서 짖는 것은 모른다..
//        actOfEatingByDog.color; 무슨 색인지 모름.. -> 상태를 갖을 수 없음
        Mammal amongMamalsDog = new Dog("yellow"); //포유류 중 개를 주목
        actOfEatingByDog.bite(); // 먹고
        amongMamalsDog.respire(); // 숨쉬고
        amongMamalsDog.bark(); //짖고
//        amongMamalsDog.color; // 그러나 무슨 색인지는 몰라.. -> 상태를 갖을 수 없음
        Dog badook = new Dog("blue"); //우리 바둑이
        badook.bite(); //먹고
        badook.respire(); //숨쉬고
        badook.bark(); //짖고
        String colorOfBabook = badook.color; //바둑이는 색깔도 뭔지 알아..








        String a = "abc";
        StringUtils.leftPad();


        String upper = AbsUitilsChild.toUpperCase("upper");
        absUitilsChild.toCharacter("abc");
//        Integer.parseInt();







        StringUtils stringUtils = new StringUtils();
//        stringUtils.




        AbsUtils.toUpperCase("Abc");

        AbsUitilsChild absUitilsChild = new AbsUitilsChild();
        absUitilsChild.toLowerCase("abc");
        absUitilsChild.toInteger("abc");
        absUitilsChild.toCharacter("abc");
        StringUtilsChild.toUpperCase("abc");
        StringUtilsChild abd = new StringUtilsChild();



        Parent parent = new Parent();
        parentRepository.save(parent);

        Child child = new Child();
        child.setParent(parent); //설정된 외래키 칼럼 필드에 해당하는 참조 변수 설정
        childRepository.save(child); // 해당하는 참조 변수의 키 값을 외래키 칼럼의 값으로 insert하는 sql문 자동으로 추가
    }
}
