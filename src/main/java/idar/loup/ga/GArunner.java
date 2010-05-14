package idar.loup.ga;

import idar.loup.game.BuildingCode;
import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;

public class GArunner {

    private int populationSize = 200;
    private FitnessFunction fitnessFunction;
    private DefaultConfiguration gaConf;
    private String initialMap;
    private int numEvolutions = 500;
    private double fitness;

    public GArunner(FitnessFunction fitnessFunction, String initialMap) {
        this.initialMap = initialMap;
        this.fitnessFunction = fitnessFunction;
    }

    public String run() {

        gaConf = new DefaultConfiguration();
        gaConf.setPreservFittestIndividual(true);
        gaConf.setKeepPopulationSizeConstant(false);

        Genotype genotype = null;

        int chromeSize;

        chromeSize = initialMap.length();

        try {
            IChromosome sampleChromosome = new Chromosome(gaConf,
                    createGenes(gaConf, chromeSize));
            gaConf.setSampleChromosome(sampleChromosome);
            gaConf.setPopulationSize(populationSize);
            gaConf.setFitnessFunction(fitnessFunction);

            Population population = new Population(gaConf);
            for (int i = 0; i < populationSize; i++) {
                sampleChromosome = new Chromosome(gaConf,
                        createGenes(gaConf, chromeSize));
                population.addChromosome(sampleChromosome);
            }
            genotype = new Genotype(gaConf, population);
        }
        catch (InvalidConfigurationException e) {
            e.printStackTrace();
            System.exit(-2);
        }
        int progress = 0;
        int percentEvolution = numEvolutions / 100;
        for (int i = 0; i < numEvolutions; i++) {
            genotype.evolve();
            // Print progress.
            // ---------------


            System.out.println("Chromosome: " + createString(genotype.getFittestChromosome()) + genotype.getFittestChromosome().getFitnessValue());

            System.out.println("--------------------");

        }
        // Print summary.
        // --------------
        IChromosome fittest = genotype.getFittestChromosome();
        System.out.println("Fittest Chromosome has fitness " +
                fittest.getFitnessValue());
        fitness = fittest.getFitnessValue();
        return createString(fittest);
    }

    private Gene[] createGenes(DefaultConfiguration gaConf, int chromeSize) {
        Gene[] genes = new Gene[chromeSize];
        for (int i = 0; i < chromeSize; i++) {
            try {
                genes[i] = new BuildingGene(gaConf, BuildingCode.fromValue(String.valueOf(initialMap.charAt(i))));
            } catch (InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }
        }
        return genes;
    }

    private String createString(IChromosome chromosome) {
        Gene[] genes = chromosome.getGenes();
        String str = "";
        for (Gene gene : genes) {
            str += gene.getAllele();
        }
        return str;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public void setNumEvolutions(int value) {
        this.numEvolutions = value;
    }

    public double getFitness() {
        return fitness;
    }
}
