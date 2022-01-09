# 인터페이스(interface)
- 추상 메서드의 집합
- 구현된 것이 전혀 없는 설계도. 껍데기(모든 멤버가 public : 항상 노출되어 있음.)

> 추상클래스와 비교
> - 일반 클래스가 추상 메서드를 갖고 있는 것이 추상 클래스(다른 것도 가질 수 있음)
> - ex. 생성자, iv(인스턴스 변수) 등을 가질 수 있다.
> BUT, 
> - 인터페이스는 **추상 메서드만**으로 이루어진 집합. (iv를 가질 수 없음)
> - 구현된 것이 아무것도 없다고 생각하면 됨.

```java
interface 인터페이스 이름{
   public static final 타입 상수이름 = 값; // 상수 (핵심X) ~ 변수는 안됨!!
   public abstract 메서드이름(매개변수목록); // 추상 메서드 (핵심O)
}
```

#### Example
```java
interface PlayingCard{
   // 항상 public, static, final이다. (생략이 가능함)
   public static final int SPADE = 4;
   final int DIAMOND = 3; // public static final int DIAMOND = 3;
   static int HEART = 2; // public static final int HEART = 2;
   int CLOVER = 1;  // public static final int CLOVER = 1;
   
   public abstract String getCardNumber();
   // public abstract : 생략 가능(항상 인터페이스는 public, abstract이므로)
   String getCardKind(); // public abstract String getCardKind();
}
```

## 인터페이스의 상속
- 인터페이스의 조상은 인터페이스만 가능(`Object`가 최고 조상이 아님.)
- 다중 상속(조상 여러 개)이 가능(추상 메서드는 충돌해도 문제 없음)
   - 원래는 다중상속이 안됐던 이유 : 선언부가 같은 메서드를 두 개의 부모가 갖고 있을 때, 내용이 다르면 충돌이 발생함.
   - 인터페이스는 어차피 내용부가 없으므로 충돌을 해도 문제가 전혀 없다.
```java
interface Fightable extends Movable, Attackable{}

interface Movable{
    /* 지정된 위치(x, y)로 이동하는 기능의 메서드 */
    void move(int x, int y);
}

interface Attackable{
    /* 지정된 대상(u)로 공격하는 기능의 메서드 */
    void attack(Unit u);
}
```

## 인터페이스의 구현
- 인터페이스에 정의된 추상 메서드를 완성하는 것(미완성 설계도를 완성하기)
> class 클래스이름 implements 인터페이스 이름{
> // 인터페이스에 정의된 추상 메서드를 모두 구현해야 한다.
> }

```java
 class Fighter implements Fightable {
    public void move(int x, int y) {/* 내용  생략 */}
    public void attack(Unit u) {/* 내용  생략 */}    
 }
```

- 일부만 구현하는 경우는, 클래스 앞에 `abstract`를 꼭 붙여야 한다.
```java
abstract class Fighter implements Fightable{
   public void move(int x, int y){/* 내용 생략 */}
}
```

---

# 인터페이스를 이용한 다형성
- 다형성? : **조상 참조 변수**로 자손 객체를 가리킬 수 있는 것.
- 인터페이스도 구현 클래스의 부모? Yes!

```java
class Fighter extends Unit implements Fightable{
   public void move(int x, int y);
   public void attack(Fightable f);
}

Unit u = new Fighter();
Fighteable f = new Fighter();
```

- 매개변수 타입이 인터페이스인 경우는 해당 인터페이스를 구현한 클래스의 인스턴스만 가능함!
```java
interface Fightable{
   void move(int x, int y);
   void attack(Fightable f); // Fightable 인터페이스를 구현한 클래스의 인스턴스만 가능
}
```

- 인터페이스를 메서드의 리턴타입으로 지정할 수 있다.
```java
Fightable method(){ // Fightable 인터페이스를 구현한 클래스의 인스턴스를 반환
   // ...
   Fighter f = new Fighter(); // 정확히는 Fighter 클래스의 인스턴스이지만, Fightable로 형변환이 되므로 Fightable로도 반환이 가능하다.(조상 - 자손 관계라 형변환 가능)
   return f;
}

Fightable f = method();
```

### Example
```java
package Example;

abstract class Unit {
    int x, y;

    abstract void move(int x, int y);

    void stop() {
        System.out.println("멈춥니다.");
    }
}

interface Fightable{
    void move(int x, int y); // public abstract 생략
    void attack(Fightable f); // public abstract 생략
}

class Fighter extends Unit implements Fightable{
    public void move(int x, int y){ // 메서드 오버라이딩시 조상보다 접근제어자가 좁으면 안된다.
        System.out.println("["+x+","+y+"]로 이동");
    }
    public void attack(Fightable f){
        System.out.println(f+"를 공격");
    }

    public String toString(){
        return "Another Fighter";
    }

    // 싸울 수 있는 상대를 불러온다.
    Fightable getFightable(){ // Fightable 인터페이스를 구현한 클래스의 인스턴스를 반환한다.
        Fighter f = new Fighter(); 
        return f;
    }

}

public class FighterTest {
    public static void main(String[] args) {
        Fighter f0 = new Fighter();
        Fightable f = new Fighter();
        Fightable f2 = f0.getFightable();
//        Fightable f3 = f.getFightable(); f가 Fightable 타입이므로 getFightable 메서드 사용 불가

        f2.move(777, 1000);

        f.move(100, 200);
        f.attack(new Fighter());

        Unit u = new Fighter();
        u.move(300, 200);
        u.stop();
    }
}

```

---

# 인터페이스의 장점

- 두 대상(객체) 간의 '연결, 대화, 소통'을 돕는 '중간 역할'을 한다.
- 선언(설계)와 구현을 분리시킬 수 있게 한다.
- 클래스 A가 클래스 B를 사용할 때(A가 B에 의존),
   - 인터페이스 덕분에 B가 변경되어도 A는 안바꿀 수 있게 된다.(느슨한 결합) <-> 인터페이스가 없으면 강한 결합이라고 봄
   - 껍데기는 그대로 유지한 채, 알맹이를 바꿔넣는다고 생각하면 됨
   
![](https://images.velog.io/images/nathan29849/post/f9189cd0-3a61-443e-aa5a-6e8fe70cde72/image.png)   
```java
package Example;

public class InterfaceExample {
    public static void main(String[] args) {
        A a = new A();
        a.methodA(new B());
        a.methodA(new C());
    }
}

class A {
    public void methodA(I i) {
        i.methodB();
    }
}

interface I {
    void methodB(); // public abstract 생략
}

class B implements I {
    public void methodB() {
        System.out.println("methodB()");
    }
}

class C implements I{
    public void methodB() {
        System.out.println("methodB() in C");
    }
}
```

## 인터페이스의 장점
- 개발 시간을 단축할 수 있다. 
- 변경에 유리한 유연한 설계가 가능하다.
- 표준화가 가능하다. (ex. JDBC - 자바 표준 데이터베이스 인터페이스)
- 서로 관계없는 클래스들을 관계를 맺어줄 수 있다.

![](https://images.velog.io/images/nathan29849/post/2d160286-bac2-4ab1-8b37-c4e89cb24a0b/image.png)
- 위의 메서드들을 하나의 메서드로만 구현이 가능하다.
```java
void repair(Repairable r){
   if (r instanceof Unit) {
      Unit u = (Unit)r;
      while(u.hitPoint != u.MAX_HP){
            u.hitPoint++;
         }
   }
}
```

# 디폴트 메서드와 static 메서드
- 인터페이스에 디폴트 메서드, static 메서드 추가가 가능해졌다.(JDK 1.8부터)
- 인터페이스에 새로운 메서드(추상 메서드)를 추가하기 어려움. (구현이 많이 이루어질수록 수정해야하는 부분이 많아지기 때문에.. 여러번 새롭게 구현을 해야한다는 의미)
- 해결책 : 디폴트 메서드(default method)
- 원래 디폴트 메서드는 인스턴스 메서드이다.(따라서 인터페이스 원칙에 위반됨 -> 근데 이제 예외사항으로 허용이 됨)

![](https://images.velog.io/images/nathan29849/post/89591f61-7c13-4616-b97c-a1804b51e433/image.png)

#### 디폴트 메서드가 기존의 메서드와 충돌할 때의 해결책
- 원래 디폴트 메서드가 허용되지 않았던 이유는 "충돌"때문
- 1. 여러 인터페이스의 디폴트 메서드 간의 충돌
   - 인터페이스를 구현한 클래스에서 디폴트 메서드를 오버라이딩 해야한다.
- 2. 디폴트 메서드와 조상 클래스의 메서드 간의 충돌
   - 조상 클래스의 메서드가 상속되고, 디폴트 메서드는 무시된다.(조상 클래스의 메서드가 우선권을 가짐)

> 결론 : 그냥 직접 오버라이딩 하면 우리가 원하는걸 쓸 수 있음.(너무 고민하지 말자.)