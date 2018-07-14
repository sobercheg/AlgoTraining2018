package epi;

import java.util.Deque;
import java.util.LinkedList;

/**
 * EPI: Recursion, page 283
 * The Towers of Hanoi Problem
 */
public class HanoiTowers {

    static class Tower {
        String name;
        Deque<Integer> rings;

        @Override
        public String toString() {
            return name + "(" + (rings.isEmpty() ? "" : rings) + ")";
        }

        Tower(String name, int... r) {
            this.name = name;
            rings = new LinkedList<>();
            for (int ring : r) {
                rings.offer(ring);
            }

        }
    }

    private void move(Tower source, Tower dest, Tower temp, int toMove) {
        if (source.rings.isEmpty() || toMove == 0) {
            return;
        }
        move(source, temp, dest, toMove - 1);
        System.out.println("Move " + source + " -> " + source.rings.peek() + " -> " + dest);
        dest.rings.offer(source.rings.poll());
        move(temp, dest, source, toMove - 1);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) {
        HanoiTowers towers = new HanoiTowers();
        towers.move(new Tower("src", 1, 2, 3), new Tower("dest"), new Tower("temp"), 3);
    }
}
