public class Mission1 {
    public static Boolean and(boolean a, boolean b) {
        return a && b;
    }

    public static Boolean or(boolean a, boolean b) {
        return a || b;
    }

    public static Boolean nand(boolean a, boolean b) {
        return !(and(a, b));
    }

    public static Boolean xor(boolean a, boolean b) {
        return and(or(a,b), nand(a, b));
    }
}
