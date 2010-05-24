package org.louCityCreator;

import org.louCityCreator.ga.FitnessIsNice;
import org.louCityCreator.ga.GA;
import org.louCityCreator.ga.GaRunner;
import org.louCityCreator.game.ShareString;

import java.io.*;

import static java.lang.System.exit;

public class App {
    private static String map;
    private static int maxBuildings;

    public static void main(String... args) {
        System.out.println("Lord of Ultima City Layout Creator");
        if (args.length < 1) {
            System.out.println("please run with input. Remeber only square maps");
            System.out.println("java -jar loucc.jar --------- <maxbuildings>");
            System.out.println("java -jar loucc.jar -f <file containing sharestring> <maxbuildings>");
            exit(0);
        }

        parseArgs(args);

        GaRunner runner = new GaRunner(new FitnessIsNice(maxBuildings), map);
        runner.setPopulationSize(100);
        runner.setNumEvolutions(200);
        ShareString shareString = runner.run();
        String chromosome = shareString.getSharestring();
        System.out.println("Winning design:");
        print(chromosome);
        System.out.println();
        System.out.println("Copy Paste to CityPlanner:");
        System.out.println(shareString.getCompleteShareString());
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
            String filestring = "";
            String s;
            while((s = br.readLine()) != null) {
                filestring += s;
            }
            SharestringParser parser = new SharestringParser(filestring);
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
