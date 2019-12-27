import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Example implements Item {

    public static final int SIZE = 12;
    private long number;
    private Boolean[] sequence = new Boolean[SIZE];

    public Example(long number) {
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
    }

    public Example(Boolean[] sequence) {
        this.sequence = sequence;
        number = 0;
        for (int i=sequence.length-1; i>=0; i--) {
            if (sequence[i])
                number += Math.pow(2, sequence.length-1-i);
        }
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
}
