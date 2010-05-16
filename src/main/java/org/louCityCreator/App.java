package org.louCityCreator;

import org.louCityCreator.ga.FitnessIsNice;
import org.louCityCreator.ga.GArunner;

import java.io.*;
import java.util.Vector;

import static org.jgap.util.FileKit.readFile;

public class App {
    private static String map;
    private static int maxBuildings;

    public static void main(String... args) {
        System.out.println("Lord of Ultima City Layout Creator");
        if (args.length < 1) {
            System.out.println("please run with input. Remeber only square maps");
            System.out.println("java -jar louclc.jar --------- <maxbuildings>");
            System.out.println("java -jar -f <file containing sharestring> <maxbuildings>");
        }

        parseArgs(args);

        GArunner runner = new GArunner(new FitnessIsNice(maxBuildings), map);
        runner.setPopulationSize(100);
        runner.setNumEvolutions(200);
        String chromosome = runner.run();
        System.out.println("Winning design:");
        print(chromosome);
        System.out.println();
    }

    private static void parseArgs(String... args) {
        if("-f".equals(args[0])){
            readShareStringFile(args[1]);
            maxBuildings = Integer.parseInt(args[2]);
        }
        else {
            map = args[0];
            maxBuildings = Integer.parseInt(args[1]);
        }
    }

    public static void readShareStringFile(String filename) {
        File file = new File(filename);
        if(!file.canRead()) throw new RuntimeException("Can't read file " + filename);
        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            SharestringParser parser = new SharestringParser(br.readLine());
            map = parser.getMap();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
