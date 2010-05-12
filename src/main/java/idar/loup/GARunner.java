package idar.loup;

import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;

public class GARunner {
    private int populationSize = 50;
    private Map initialmap;
    private FitnessFunction fitnessFunction;
    private DefaultConfiguration gaConf;

    public GARunner(Map initialmap, FitnessFunction fitnessFunction) {
        this.initialmap = initialmap;
        this.fitnessFunction = fitnessFunction;
    }

    public void run(){
         int numEvolutions = 500;
        gaConf = new DefaultConfiguration();
        gaConf.setPreservFittestIndividual(true);
        gaConf.setKeepPopulationSizeConstant(false);
        Genotype genotype = null;
        int chromeSize;

        chromeSize = 21*21;

        try {
            IChromosome sampleChromosome = new Chromosome(gaConf,
                    createGenes(gaConf,chromeSize));
            gaConf.setSampleChromosome(sampleChromosome);
            gaConf.setPopulationSize(populationSize);
            gaConf.setFitnessFunction(fitnessFunction);
            Population population = new Population(gaConf);
            for(int i = 0; i< populationSize;i++){
                population.addChromosome(sampleChromosome);
            }
            genotype = new Genotype(gaConf,population);
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
            if (percentEvolution > 0 && i % percentEvolution == 0) {
                progress++;
                IChromosome fittest = genotype.getFittestChromosome();
                double fitness = fittest.getFitnessValue();
                System.out.println("Currently fittest Chromosome has fitness " +
                        fitness + fittest.toString());
            }
        }
        // Print summary.
        // --------------
        IChromosome fittest = genotype.getFittestChromosome();
        System.out.println("Fittest Chromosome has fitness " +
                fittest.getFitnessValue());
    }

    private Gene[] createGenes(Configuration gaConf, int chromeSize) {
        Gene[] array = new Gene[chromeSize];
        Map map = new Map(initialmap.getShareString());

        int z = 0;
        for(int i = 0; i< 21; i++){
            for(int j = 0; j<21;j++){
                try {
                    array[z++] = new BuildingGene(gaConf,map.array[i][j].toString());
                } catch (InvalidConfigurationException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return array;
    }


}
