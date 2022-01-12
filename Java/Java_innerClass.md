# 내부 클래스(inner class)
- 클래스 안의 클래스
```java
class A { // 외부 클래스
   ...
   class B { // 내부 클래스
      ...
   }
   ...
}
```

## 내부 클래스의 장점
- 내부 클래스에서 외부 클래스의 멤버들을 쉽게 접근할 수 있다.
- 원래는 객체를 생성해야 쓸 수 있지만, 내부 클래스에서는 객체 생성 없이 멤버에 접근이 가능하다.
- 코드의 복잡성을 줄일 수 있다.(일종의 캡슐화)


## 내부 클래스 예시
```java
class AAA {
    int i = 100;

    class BBB {
        void method(){
            System.out.println(i); // 객체 생성없이 외부 클래스의 멤버 접근 가능
        }
    }
}
```

## 내부 클래스의 종류와 특징
- 내부 클래스의 종류와 유효범위(scope)는 변수와 동일

![](https://images.velog.io/images/nathan29849/post/f52c9675-2151-421f-9be2-2153778d0e3b/image.png)

|내부 클래스|특 징|
|--|--|
|인스턴스 내부 클래스|외부 클래스의 멤버변수 선언 위치에 선언하며, 외부 클래스의 인스턴스 멤버처럼 다루어진다. </br>주로 외부 클래스의 인스턴스 멤버들과 관련된 작업에 사용될 목적으로 선언된다.|
|스태틱 내부 클래스|외부 클래스의 멤버변수 선언 위치에 선언하며, 외부 클래스의 static 멤버처럼 다루어진다. </br>주로 외부 클래스의 static 멤버, 특히 static 메서드에서 사용될 목적으로 선언된다.|
|지역 내부 클래스|외부 클래스의 메서드나 초기화 블럭 안에 선언하며, 선언된 영역 내부에서만 사용될 수 있다.|
|익명 클래스|클래스의 선언과 객체의 생성을 동시에 하는 이름없는 클래스(일회용) </br>AWT나 SWING 같은 GUI에서 이벤트를 처리할 때 주로 쓰임|

## 내부 클래스의 제어자와 접근성
- 내부 클래스의 제어자는 변수에 사용 가능한 제어자와 동일(private, protected, public, default)
- 원래 클래스에는 default 또는 public만 가능함

```java
class Outer {
    private class InstanceInner {
    }

    protected static class StaticInner {
    }

    void myMethod() {
        class LocalInner {
        }
    }
}
```
---

### Example Code

```java

class Outer {
    public static void main(String[] args) {
        System.out.println(InstanceInner.CONST);
        System.out.println(StaticInner.cv);
        System.out.println(LocalInner.iv); // error : 지역 내부클래스의 static 상수는 메서드 내에서만 사용이 가능함.
    }
    private class InstanceInner {
        int iv = 100;
        static int cv = 100; // error : static 변수를 선언할 수 없다.
        final static int CONST = 100; // final static은 상수이므로 허용
    }

    protected static class StaticInner {
        int iv = 200;
        static int cv = 200;
    }

    void myMethod() {
        class LocalInner {
            int iv = 300;
            static int cv = 300; // error : static 변수를 선언할 수 없다.
            final static int CONST = 300; // final static은 상수이므로 허용
        }
    }
}
```

- 참고 : static 내부 클래스에서는 외부 클래스의 인스턴스 멤버에 접근할 수 없다.

---

### Example Code2
```

class Outer2 {
    private int outerIv = 0;
    static int outerCv = 0;

    class InstanceInner {
        int iiv = outerIv; // 외부 클래스의 private 멤버도 접근 가능하다.
        int iiv2 = outerCv;
    }

    static class StaticInner {
        int siv = outerIv; // static 클래스는 외부 클래스의 인스턴스 멤버에 접근할 수 없다.
        static int scv = outerCv;
    }

    void myMethod(){
        int lv = 0;
        final int LV = 0; // JDK 1.8부터 final 생략 가능

        class LocalInner {
            int liv = outerIv;
            int liv2 = outerCv;
            // 외부 클래스의 지역변수는 final이 붙은 변수(상수)만 접근 가능하다. (내부 클래스의 객체가 지역변수 - lv보다 더 오래 존재할 가능성이 있기 때문)
            int liv3 = lv; // but JDK 1.8부터 에러 아님(final이 생략된 것으로 봄 - 상수로 간주한다는 의미임)
            int liv4 = LV; // OK
        }
    }
    
}
```

- 1. 외부 클래스의 private 멤버도 접근 가능
- 2. 지역 내부 클래스를 감싸고 있는 메서드의 상수만 사용가능(지역 내부 클래스의 객체가 지역변수 lv보다 더 오래 존재할 가능성이 있기 때문)

---

### Example Code3
```java
public class EX_Outer {
    public static void main(String[] args) {
        Outer2 oc = new Outer2(); // 외부 클래스의 인스턴스를 먼저 생성해야 인스턴스 클래스의 인스턴스를 생성 가능
        Outer2.InstanceInner ii = oc.new InstanceInner();

        System.out.println(ii.iiv);
        System.out.println(Outer2.StaticInner.scv);
        
        // static 내부 클래스의 인스턴스는 외부 클래스를 먼저 생성하지 않아도 된다. (단, 생성할 때 외부 클래스 이름을 앞에 붙여야 함)
        Outer2.StaticInner si = new Outer2.StaticInner();
        System.out.println(si.scv);        
    }
}    
```
- 외부 클래스의 인스턴스를 먼저 생성해야 인스턴스 클래스의 인스턴스(내부 클래스의 인스턴스)를 생성가능하다.

- static 내부 클래스의 인스턴스는 외부 클래스를 먼저 생성하지 않아도 된다. (단, 생성할 때 외부 클래스 이름을 앞에 붙여야 함)


---

### Example Code4 (변수 이름이 전부 같을 때)

```java
public class EX_Outer2 {
    public static void main(String[] args) {
        Outer3 outer = new Outer3();
        Outer3.Inner inner = outer.new Inner();
        inner.method1();
    }
}

class Outer3{
    int value = 10;  // Outer3.this.value
    class Inner{
        int value = 20; // this.value

        void method1(){
            int value = 30; // lv
            System.out.println(value);
            System.out.println(this.value);
            System.out.println(Outer3.this.value);
        } // Inner class의 끝
    } // Outer3 class의 끝
}
```