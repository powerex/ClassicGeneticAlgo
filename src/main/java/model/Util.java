package model;

import java.util.List;

public class Util {

    static void printSequence(Boolean[] seq) {
        for (Boolean b: seq) {
            if (b)
                System.out.print("1");
            else
                System.out.print("0");
        }
        System.out.println();
    }

    public static void printItems(List<Item> list) {
        for (Item i: list) {
            System.out.print("(" + ((Example)i).getNumber() + ")\t");
            System.out.print("(" + i.getFitness() + ")\t");
            printSequence(i.getSequence());
        }
    }

}
