package cs01.binaryAdder;

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
