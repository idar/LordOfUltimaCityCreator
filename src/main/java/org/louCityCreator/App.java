package org.louCityCreator;

import org.louCityCreator.ga.FitnessIsNice;
import org.louCityCreator.ga.GArunner;

public class App {

    public static void main(String... args) {
        System.out.println("Lord of Ultima City Layout Creator");
        if (args.length < 1) {
            System.out.println("please run with input. Remeber only square maps");
            System.out.println("java -jar louclc.jar --------- <maxbuildings>");
        }

        int maxBuildings = Integer.parseInt(args[1]);
        GArunner runner = new GArunner(new FitnessIsNice(maxBuildings), args[0]);
        runner.setPopulationSize(100);
        runner.setNumEvolutions(200);
        String chromosome = runner.run();
        System.out.println("Winning design:");
        print(chromosome);
        System.out.println();
    }

    private static void print(String chromosome) {
        int dim = (int) Math.sqrt(chromosome.length());
        int length = 0;
        while (length < chromosome.length()) {

            for (int i = 0; i < dim; i++) {
                System.out.print(chromosome.charAt(length++));
            }
            System.out.println("");
        }
    }
}
