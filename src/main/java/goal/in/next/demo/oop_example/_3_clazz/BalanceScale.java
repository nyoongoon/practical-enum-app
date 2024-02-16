package goal.in.next.demo.oop_example._3_clazz;

class BalanceScale implements Scale{ //추상화, 상속, 다형성 있음.
    Object left; // 캡슐화 -> 상태 있음
    Object right; // 특정 대상의 가변적인 순간적인 상태를 갖고 있을 수 있다..

    public void putObjectsOnScale(Object left, Object right){
        this.left = left;
        this.right = right;
    }

    public Object getObjectOnScaleDown(){
        //..
        return null;
    }
}
