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

