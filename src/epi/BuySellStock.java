package epi;

import utils.UnitTester;

/**
 * EPI: Introduction, page 1
 * Design an algorithm that determines the maximum profit that could
 * have been made by buying and then selling a single share over a given day range,
 * subject to the constraint that the buy and the sell have to take place at the start of the
 * day.
 */
public class BuySellStock {

    class Purchase {
        Purchase(int startDay, int endDay, long diff) {
            this.startDay = startDay;
            this.endDay = endDay;
            this.diff = diff;
        }

        int startDay;
        int endDay;
        long diff;
    }

    Purchase findBestPurchase(int[] prices) {
        Purchase currentBest = new Purchase(0, 1, prices[1] - prices[0]);

        int minIndex = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < prices[minIndex]) {
                // Found the next minimum, store into a temp var
                minIndex = i;
            } else if (prices[i] - prices[minIndex] > currentBest.diff) {
                // The diff between the current and minIndex days is the best, update
                currentBest = new Purchase(minIndex, i, prices[i] - prices[minIndex]);
            } else if (prices[i] - prices[currentBest.startDay] > currentBest.diff) {
                currentBest.endDay = i;
                currentBest.diff = prices[i] - prices[currentBest.startDay];
            }

        }
        return currentBest;
    }

    public static void main(String[] args) {
        testTrailingMinimum();
        testAscending();
    }

    private static void testTrailingMinimum() {
        // Run
        BuySellStock stock = new BuySellStock();
        Purchase p = stock.findBestPurchase(new int[]{10, 9, 10, 11, 20, 10, 2});
        //                                                ^          ^

        // Assert
        UnitTester.assertEquals(1, p.startDay);
        UnitTester.assertEquals(4, p.endDay);
        UnitTester.assertEquals(11L, p.diff);
    }

    private static void testAscending() {
        // Run
        BuySellStock stock = new BuySellStock();
        Purchase p = stock.findBestPurchase(new int[]{1, 2, 3, 4, 5, 6});
        //                                            ^              ^

        // Assert
        UnitTester.assertEquals(0, p.startDay);
        UnitTester.assertEquals(5, p.endDay);
        UnitTester.assertEquals(5L, p.diff);
    }
}
