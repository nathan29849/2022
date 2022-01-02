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
> Tv t = new SmartTv(); 
- 자손 클래스의 멤버 변수를 사용할 수 없다.


## 참조변수의 형변환 (=리모컨 바꾸기)
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

- 핵심 :  참조변수가 가리키는 실제 인스턴스가 무엇인지가 중요함(해당 멤버의 개수를 넘어서면 안된다.)

---
## instanceof 연산자
- 참조변수의 형변환 가능 여부 확읺에 사용. 가능하면 true로 반환
- 형변환 전에 반드시 instanceof 로 확인해야 함.

```java
void doWork(Car c){
   if (c instanceof FireEngine){
      FireEngine fe = (FireEngine)c;
      fe.water();
   }
}

FireEngine fe = new FireEngine();
System.out.println(fe instanceof Object); // true (조상 타입에 대해서도 참이 나옴)
System.out.println(fe instanceof Car); // true (조상 타입에 대해서도 참이 나옴)
System.out.println(fe instanceof FireEngine); // true
```

# 다형성의 장점
- 1. 다형적 매개변수
- 2. 하나의 배열로 여러 종류의 객체 다루기

## 매개변수의 다형성   
- 참조형 매개변수는 메서드 호출시, 자신과 같은 타입 또는 자손타입의 인스턴스를 넘겨줄 수 있다.
```java
package codechobo;

import java.util.Arrays;

public class ProductExample {
    public static void main(String[] args) {

        Buyer b = new Buyer();
        TvModel tv = new TvModel();
        ComputerModel com = new ComputerModel();

        b.buy(tv);
        System.out.println(b.money);
        b.buy(com);
        System.out.println(b.money);
        System.out.println(Arrays.toString(b.cart));
        TvModel a = (TvModel)b.cart[0];

//        System.out.println(b.cart[0].power); Product 클래스의 참조변수이므로 형변환이 필요함.
        System.out.println(a.power);
    }
}


class Product{
    int price; // 제품 가격
    int bonusPoint; // 보너스 점수

    Product(int price){
        this.price = price;
        bonusPoint = (int)(price/10.0);
    }
}

class TvModel extends Product{
    boolean power = true;
    TvModel() {
        super(300);
    }
}
class ComputerModel extends Product{
    ComputerModel(){
        super(150);
    }
}

class Buyer{ // 물건 사는 사람
    int money = 1000;
    int bonusPoint = 0;

    Product[] cart = new Product[10];

//    void buy(TvModel t){
//        // 이렇게 매개변수를 설정하면 모든 클래스별로 오버로딩을 통해 메서드를 만들어야 한다.
//        money -= t.price;
//        bonusPoint += t.bonusPoint;
//    }
    int i = 0;
    void buy(Product p){
        if(money < p.price){
            System.out.println("구매할 수 없습니다.");
            return;
        }
        money -= p.price;
        bonusPoint += p.bonusPoint;

        cart[i++] = p;
    }
}

```