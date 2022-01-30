# 쓰레드의 동기화(synchronization)
- 멀티 쓰레드 프로세스에서는 다른 쓰레드의 작업에 영향을 미칠 수 있다.
- 진행중인 작업이 다른 쓰레드에게 간섭받지 않게 하려면, '동기화'가 필요하다.

> 쓰레드의 동기화 : 한 쓰레드가 진행중인 작업을 다른 쓰레드가 간섭하지 못하게 막는 것

- 동기화하려면, 간섭받지 않아야 하는 문장들을 `임계 영역`으로 설정(여러 문장 하나로 묶기)
- 임계 영역은 락(lock)을 얻은 단 하나의 쓰레드만 출입가능 (객체 1개에 락 1개)


## synchronized를 이용한 동기화
- synchronized로 임계영역(lock이 걸리는 영역)을 설정하는 방법 2가지

- 1. 메서드 전체를 임계 영역으로 지정
> public synchronized void calcSum(){
>    // ...
>}

- 2. 특정한 영역을 임계 영역으로 지정
> synchronized(객체의 참조변수){
>   // ...
> }

- 임계 영역은 한 번에 한 쓰레드만 사용할 수 있기 때문에 영역을 최소화해야 함.
- 임계 영역이 넓을수록 성능이 떨어질 수 밖에 없다. (멀티 스레드의 장점이 동시에 여러 스레드의 작동이기 때문)

### 예제 코드
```java
public class ThreadEx22 {
    public static void main(String[] args) {
        Runnable r = new RunabbleEx22();
        new Thread(r).start();
        new Thread(r).start();
    }
}

class Account2 {
    private int balance = 1000; // private으로 해야 동기화가 의미가 있다.

    public synchronized int getBalance(){ // 사실은 읽을 때도 동기화 해줘야 함.
        return balance;
    }

    public synchronized void withdraw(int money){ // synchronized로 메서드를 동기화
        if (balance >= money) {
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){}
            balance -= money;
        }
    } // withdraw
}


class RunabbleEx22 implements Runnable{
    Account2 acc = new Account2();

    @Override
    public void run() {
        while(acc.getBalance() > 0){
            // 100, 200, 300 중의 한 값을 임의로 선택하여 출금(withdraw)
            int money = (int) (Math.random() * 3 + 1) * 100;
            acc.withdraw(money);
            System.out.println("balance : "+acc.getBalance());
        }
    }
}
```

---

## wait()과 notify()
- 동기화의 효율을 높이기 위해 wait(), notify()를 사용 (기다리기, 알려주기)
- Object 클래스에 정의되어 있으며, 동기화 블록 내에서만 사용할 수 있다.
- `wait()` : 객체의 lock을 풀고 쓰레드를 해당 객체의 waiting pool에 넣는다.
- `notify()` : waiting pool에서 대기중인 쓰레드 중의 하나를 깨운다.
- `notifyAll()` : waiting pool에서 대기중인 모든 쓰레드를 깨운다.(notify로만 하는 경우보다 조금 더 공평하게 모든 스레드를 깨우고 하는게 일반적으로 좋다고 함)


### wait()과 notify() 예제
- 요리사는 Table에 음식을 추가. 손님은 Table의 음식을 소비
- 요리사와 손님이 같은 객체(Table)을 공유하므로 동기화가 필요

- \[예외 1] 요리사가 Table에 요리를 추가하는 과정에 손님이 요리를 먹음
- \[예외 2] 하나 남은 요리를 손님2가 먹으려 하는데, 손님 1이 먹음

- 따라서 Table에 음식을 add, remove 하는 것에 대하여 동기화를 진행하였다.
- \[문제점] But.. 음식이 없을 때, 손님이 Table의 lock을 쥐고 안놓는다.
   - 요리사가 lock을 얻지 못해서 Table에 음식을 추가할 수 없다.

- \[해결책] 음식이 없을 때, wait()으로 손님이 lock을 풀고 기다리게 하자.
   - 요리사가 음식을 추가하면, notify()로 손님에게 알리자. (손님이 lock을 재획득)
   
   
### 예제 코드
```java
import java.util.ArrayList;

public class Ex13_14 {
    public static void main(String[] args) throws Exception {
        Table table = new Table();

        new Thread(new Cook(table), "Cook").start();
        new Thread(new Customer(table, "donut"), "CUST1").start();
        new Thread(new Customer(table, "burger"), "CUST2").start();

        Thread.sleep(2000);
        System.exit(0);

    }
}

class Customer implements Runnable {
    private Table table;
    private String food;

    Customer(Table table, String food) {
        this.table = table;
        this.food = food;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e){}
            String name = Thread.currentThread().getName();
            table.remove(food);
            System.out.println(name + " ate a "+food);
        }
    }
}

class Cook implements Runnable {
    private Table table;

    Cook(Table table) {
        this.table = table;
    }

    @Override
    public void run() {
        while (true) {
            int idx = (int) (Math.random() * table.dishNum());
            table.add(table.dishNames[idx]);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e){}
        }
    }
}

class Table {
    String[] dishNames = {"donut", "donut", "burger"};
    final int MAX_FOOD = 6;
    private ArrayList<String> dishes = new ArrayList<>();

    public int dishNum() {
        return dishNames.length;
    }

    public synchronized void add(String dish) {
        if(dishes.size() >= MAX_FOOD){
            String name = Thread.currentThread().getName();
            System.out.println(name + " is waiting");
            try{
                wait(); // COOK 쓰레드를 기다리게 한다.
                Thread.sleep(500);
            } catch (InterruptedException e){}
        }
        dishes.add(dish);
        notify(); // 기다리고 있는 CUST를 깨우기 위함.
        System.out.println("Dishes: "+dishes.toString());
    }

    public boolean remove(String dishName) {
        synchronized (this) {
            String name = Thread.currentThread().getName();
            while (dishes.size() == 0) {
                System.out.println(name + " is waiting");
                try {
                    wait(); // CUST 쓰레드를 기다리게 한다.
                    Thread.sleep(500);
                } catch (InterruptedException e){}
            }

            while (true) {
                for(int i =0; i<dishes.size(); i++){
                    if(dishName.equals(dishes.get(i))){
                        dishes.remove(i);
                        notify(); // COOK을 깨우기 위함.
                        return true;
                    }
                }

                try{
                    System.out.println(name + " is waiting");
                    wait(); // 원하는 음식이 없는 CUST 쓰레드를 기다리게 만든다.
                    Thread.sleep(500);
                } catch (InterruptedException e){}

            }
            /*return false;*/
        }
    }

}
```

### 예제 결과

![](https://images.velog.io/images/nathan29849/post/385b2da4-2a49-4eff-a5a1-deb3ed56a117/image.png)


- 예제 코드의 단점 : notify시에 요리사인지 손님인지 구분이 모호함. (notify & condition에 대해 학습 해보기)