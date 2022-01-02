# 다형성(Polymorphism)
- 여러 가지 형태를 가질 수 있는 능력
- 조상 타입 참조 변수로 자손 타입 객체를 다루는 것
   - 타입이 불일치해도 사용이 가능해짐!
```java
package codechobo;

public class TvExample {
    public static void main(String[] args) {
        Tv t = new SmartTv(); // 타입 불일치해도 괜찮음(다형성) : 반대의 경우는 허용이 안됨
        t.text = "Hello World!"; // 사용 불가
        t.caption(); // 사용 불가                
    }
}

class Tv{
    boolean power;
    int channel;
    
    void power(){ power = !power; }
    void channelUp(){ ++channel; }
    void channelDown(){ --channel; }
    
}

class SmartTv extends Tv{
    String text;
    void caption(){
        System.out.println(text);
    }
}
```

## 특징
- 객체와 참조변수의 타입이 일치할 때와 일치하지 않을 때의 차이?
> SmartTv s = new SmartTv();
> Tv t = new SmartTv(); // 반대의 경우는 불가능하다.
- 자손 클래스의 멤버 변수를 사용할 수 없다.


## 참조변수의 형변환
- 사용할 수 있는 멤버의 개수를 조절하는 것
- 조상, 자손 관계의 참조변수는 서로 형변환 가능
```java
package codechobo;

public class CarExample {
    FireEngine f = new FireEngine();
    Car c = (Car)f; // 조상인 Car타입으로 형변환(생략 가능)
    FireEngine f2 = (FireEngine)c; // 자손인 FireEngine타입으로 형변환(생략 불가)
    Ambulance a = (Ambulance) f; // error : 상속 관계가 아닌 클래스 간 형변환 불가
    
}

class Car{
    String color;
    int door;
    
    void drive(){
        System.out.println("drive, Brrr~~");
    }
    
    void stop(){
        System.out.println("Stop!!!!");
    }
}

class FireEngine extends Car{
    void water(){
        System.out.println("Waterrr!");
    }
    
}

class Ambulance extends Car{
    void siren(){
        System.out.println("beep~~~");
    }
}
```