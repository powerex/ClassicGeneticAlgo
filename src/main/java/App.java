import model.Example;
import model.Flask;
import model.Util;

import java.util.Random;

public class App {

    public static void main(String[] args) {

//*
        Random rnd = new Random();
        Flask<model.Item> flask = new Flask<model.Item>();
        for (int i=0; i<8; i++) {
            flask.addItem(new Example(rnd.nextInt(4096)));
        }

        flask.print();

        flask.printPercents();
        System.out.println("======");
        flask.printDistribution();

        System.out.println("\n\n");
//        Util.printItems(flask.getParentPool(4));

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~``");


//        Util.printItems(flask.crossParentPool(flask.getParentPool(4)));
//*/
    }

}
