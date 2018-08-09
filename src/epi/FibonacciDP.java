package epi;

import utils.UnitTester;

/**
 * EPI: Dynamic Programming, page 304
 * Fibonacci numbers O(n) time, O(1) space
 */
public class FibonacciDP {

    private static int getNum(int n) {
        if (n < 2) {
            return n;
        }
        int fPrevious2 = 0;
        int fPrevious1 = 1;
        for (int i = 2; i <= n; i++) {
            int sum = fPrevious1 + fPrevious2;
            fPrevious2 = fPrevious1;
            fPrevious1 = sum;
        }
        return fPrevious1;
    }

    public static void main(String[] args) {
        System.out.println(getNum(4));
        System.out.println(getNum(5));
        System.out.println(getNum(6));
        System.out.println(getNum(7));
        UnitTester.assertEquals(13, getNum(7));
    }
}
