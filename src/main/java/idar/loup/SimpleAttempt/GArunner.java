package idar.loup.SimpleAttempt;

import idar.loup.*;
import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;

import java.util.List;

public class GArunner {

    private int populationSize = 200;
    private FitnessFunction fitnessFunction;
    private DefaultConfiguration gaConf;

    public GArunner(FitnessFunction fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    public void run() {
        int numEvolutions = 500;
        gaConf = new DefaultConfiguration();
        gaConf.setPreservFittestIndividual(true);
        gaConf.setKeepPopulationSizeConstant(false);

        Genotype genotype = null;

        int chromeSize;

        chromeSize = 9;

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
    }

    private Gene[] createGenes(DefaultConfiguration gaConf, int chromeSize) {
        Gene[] genes = new Gene[chromeSize];
        for (int i = 0; i < chromeSize; i++) {
            try {
                genes[i] = new BuildingGene(gaConf, BuildingCode.get(gaConf.getRandomGenerator()));
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
}
