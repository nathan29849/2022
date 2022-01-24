# 익명 클래스(anonymous class)
- 이름이 없는 일회용 클래스로, 클래스의 정의와 생성을 동시에 한다.

```java
new 조상클래스이름() {
   // 멤버 선언
}

new 구현인터페이스이름() {
   // 멤버 선언
}
```


### Example 1
```java
public class AnonymousClass {
    // 익명 클래스
    Object iv = new Object() {
        void method() {
        }
    };

    // 익명 클래스
    static Object cv = new Object() {
        void method() {
        }
    };


    // 익명 클래스
    void myMethod() {
        Object lv = new Object() {
            void method() {
            }
        };
    }
}
```

### Example 2
```java
// AWT (Java의 윈도우 프로그래밍)
public class Ex7_18 {
    public static void main(String[] args) {
        Button b = new Button("start");
        b.addActionListener(new EventHandler());
    }
}

// 1회성으로 쓰이기 때문에 이렇게 안쓰고 익명클래스로 씀
class EventHandler implements ActionListener{
    public void actionPerformed(ActionEvent e){
        System.out.println("ActionEvent occurred!!!");
    }
}
```

- 위의 예제를 아래와 같이 바꿔서 많이 사용한다.(EventHandler 클래스가 일회성으로 쓰이기 때문)
```java
public class Ex7_18 {
    public static void main(String[] args) {
        Button b = new Button("start");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ActionEvent occurred!!!");
            }
        });
    }
}

```