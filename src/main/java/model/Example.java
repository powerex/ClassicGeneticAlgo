package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Example implements Item {

    public static final int SIZE = 12;
    private static int id = 1;
    private long number;
    private String name;
    private String stringSquence;
    private Boolean[] sequence = new Boolean[SIZE];

    public static void clearPopulation() {
        id = 1;
    }

    public Example(long number) {
        this.name = "ch" + id++;
        this.number = number;

        List<Boolean> res = new LinkedList<Boolean>();
        long n = number;
        int i = 0;
        while (n != 0) {
            res.add(n % 2 == 1);
            n /= 2;
            ++i;
        }
        while (i < SIZE) {
            res.add(false);
            ++i;
        }
        Collections.reverse(res);
        sequence = res.toArray(sequence);
        stringSquence = getSequenceString();
    }

    public Example(Boolean[] sequence) {
        this.name = "ch" + id++;
        this.sequence = sequence;
        number = 0;
        for (int i=sequence.length-1; i>=0; i--) {
            if (sequence[i])
                number += Math.pow(2, sequence.length-1-i);
        }
        stringSquence = getSequenceString();
    }

    public Boolean[] getSequence() {
        return sequence;
    }

    public long getNumber() {
        return number;
    }

    public double getFitness() {
        double f = 0;
        long n = number;
        while (n != 0) {
            if (n % 2 == 1)
                f++;
            n /= 2;
        }
        return f;
    }

    public String getName() {
        return name;
    }

    public String getSequenceString() {
        StringBuilder sb = new StringBuilder();
        for (Boolean b: sequence) {
            if (b)
                sb.append('1');
            else
                sb.append('0');
        }
        return sb.toString();
    }

    public String getStringSquence() {
        return stringSquence;
    }
}
