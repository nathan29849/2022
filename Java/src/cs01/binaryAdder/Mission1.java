package e7d849a05f7ba1ad554a5e92e5e2e6aa;

public class Mission1 {
    static Boolean and(boolean a, boolean b){
        return a && b;
    }

    static Boolean or(boolean a, boolean b){
        return a || b;
    }

    static Boolean nand(boolean a, boolean b){
        return !(a && b);
    }

    static Boolean xor(boolean a, boolean b){
        return (a || b) && !(a && b);
    }
}
