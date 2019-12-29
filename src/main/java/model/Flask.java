package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Flask<T extends Item> {

    private List<T> items;
    private List<T> parents = null;
    private List<T> nextGeneration = null;
    private final double MUTATION = 0.2;

    private List<Double> percents;
    private List<Double> distribution;
    private int age;

    public Flask() {
        items = new LinkedList<T>();
        percents = new LinkedList<Double>();
        distribution = new LinkedList<Double>();
        age = 0;
        Example.clearPopulation();
    }

    public List<T> getNextGeneration() {
        return nextGeneration;
    }

    public List<T> getParents() {
        return parents;
    }

    void recalcPercents() {
        double sum = 0;
        for (Item i: items) {
            sum += i.getFitness();
        }
        percents.clear();
        distribution.clear();
        for (Item i: items) {
            percents.add(i.getFitness() / sum);
        }
        distribution.add(percents.get(0));
        for (int i=1; i<items.size(); ++i) {
            distribution.add(distribution.get(i-1) + percents.get(i));
        }
    }

    public double getAverageFitness() {
        double sum = 0.0;
        for (T t: items) {
            sum += t.getFitness();
        }
        return sum / items.size();
    }

    public void printPercents() {
        recalcPercents();
        for (Double d: percents) {
            System.out.println(d);
        }
    }

    public void printDistribution() {
        recalcPercents();
        for (Double d: distribution) {
            System.out.println(d);
        }
    }

    public void addItem(T it) {
        items.add(it);
        recalcPercents();
    }

    public T getItem(int index) {
        return items.get(index);
    }

    public void print() {
        for (Item i: items) {
            System.out.print("(" + i.getFitness() + ")\t");
            Util.printSequence(i.getSequence());
        }
    }

    private List<T> getParentPool(int size) {
        List<T> parents = new LinkedList<T>();
        Random rnd = new Random();
        for (int i=0; i<size*2; i++) {
            double d = rnd.nextDouble();
            int j = 0;
            while (d > distribution.get(j))
                ++j;
            parents.add(items.get(j));
        }
        return parents;
    }

    private List<T> crossParentPool() {
//        int k = model.Example.SIZE / 2;
        getParentPool(4);
        Random rnd = new Random();
        List<T> nextGeneration = new LinkedList<T>();

        for (int n=0; n<parents.size()/2; n++) {

            Boolean[] child1 = new Boolean[Example.SIZE];
            Boolean[] child2 = new Boolean[Example.SIZE];

            int k = rnd.nextInt(Example.SIZE-2) + 1;

            for (int i = 0; i < Example.SIZE; i++) {
                if (i < k) {
                    child1[i] = parents.get(2*n).getSequence()[i];
                    child2[i] = parents.get(2*n + 1).getSequence()[i];
                } else {
                    child2[i] = parents.get(2*n).getSequence()[i];
                    child1[i] = parents.get(2*n + 1).getSequence()[i];
                }
            }

            if (rnd.nextDouble() < MUTATION) {
                child1[rnd.nextInt(Example.SIZE)] ^= true;
            }

            nextGeneration.add((T) new Example(child1));
            nextGeneration.add((T) new Example(child2));
        }
        return nextGeneration;
    }

    public int getAge() {
        return age;
    }

    public void nextGeneration() {
        age++;
        if (nextGeneration != null)
            items = nextGeneration;
        parents = getParentPool(4);
        nextGeneration = crossParentPool();
        recalcPercents();
    }

    public List<T> getItems() {
        return items;
    }

    public double getPercents(int index) {
        return percents.get(index);
    }

}
