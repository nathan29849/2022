# 제어자(modifier)
- 클래스와 클래스의 멤버(멤버 변수, 메서드)에 부가적인 의미 부여
- 접근 제어자
   - public, protected, default, private
- 그 외
   - static, final, abstract, native, transient, synchronized, volatile, strictfp

- 하나의 대상에 여러 제어자를 같이 사용 가능(접근 제어자는 하나만 사용 가능)
```java
public class ModifierTest{
   public static final int WIDTH = 200;
   public static void main(String[] args){
      System.out.println("WIDTH="+WIDTH);
   }
}
```

## static : 클래스의, 공통적인
|제어자|대상|의미|
|--|--|--|
|static|멤버변수| - 모든 인스턴스에 공통적으로 사용되는 클래스 변수가 된다.</br> - 클래스 변수는 인스턴스를 생성하지 않고도 사용 가능하다.</br> - 클래스가 메모리에 로드될 때 생성된다.
|static|메서드| - 인스턴스를 생성하지 않고도 호출이 가능한 static 메서드가 된다.</br> - static 메서드 내에서는 인스턴스 멤버들을 직접 사용할 수 없다.

```java
class StaticTest{
    static int width = 200; // 클래스 변수(static 변수)
    static int height = 120; // 클래스 변수(static 변수)
    
    static { // 클래스 초기화 블럭
        // static 변수의 복잡한 초기화 수행
    }
    
    static int max(int a, int b){ // 클래스 메서드(static 메서드)
        return a > b ? a : b;
    }
}

```

## final : 마지막의, 변경될 수 없는
|제어자|대상|의미|
|--|--|--|
|final|클래스| - 변경될 수 없는 클래스, 확장될 수 없는 클래스가 된다.</br> 그래서 final로 지정된 클래스는 다른 클래스의 조상이 될 수 없다.
|final|메서드| - 변경될 수 없는 메서드, final로 지정된 메서드는 오버라이딩을 통해 재정의 될 수 없다.
|final|멤버변수| - 변수 앞에 final이 붙으면, 값을 변경할 수 없는 상수가 된다.|
|final|지역변수| - 변수 앞에 final이 붙으면, 값을 변경할 수 없는 상수가 된다.|


## abstract : 추상의, 미완성의
- 추상 메서드를 갖고 있으면, 무조건 추상 클래스이다.
- 추상 클래스의 인스턴스는 생성이 불가능하다. (미완성 설계도이기 때문)
- 상속을 통해 완성해야 쓸 수 있음. (완성된 설계도 = 구상 클래스)

|제어자|대상|의미|
|--|--|--|
|abstract|클래스| - 클래스 내에 추상 메서드가 선언되어 있음을 의미한다.|
|abstract|메서드| - 선언부만 작성하고 구현부는 작성하지 않은 추상 메서드임을 알린다.|

```java
abstract class AbstractTest{ // 추상 클래스(추상 메서드를 포함한 클래스)
   abstract void move(); // 추상 메서드(구현부가 없는 메서드)
}

AbstractTest a = new AbstractTest(); // error : 추상 클래스의 인스턴스 생성 불가 
```

## 접근 제어자(access modifier)
- private : 같은 클래스 내에서만 접근 가능
- default : 같은 패키지 내에서만 접근 가능
- protected : 같은 패키지 내 + 다른 패키지의 자손 클래스에서 접근 가능
- public : 접근 제한이 전혀 없음

|제어자|같은 클래스|같은 패키지|자손 클래스</br>(다른 패키지)|전체|
|--|--|--|--|--|
|public|O|O|O|O|
|protected|O|O|O||
|default|O|O|||
|private|O||||

- public, default의 경우 클래스에만 붙을 수 있는 접근 제어자이다.
- public, protected, default, private의 경우 메서드에 붙을 수 있는 접근 제어자이다.
