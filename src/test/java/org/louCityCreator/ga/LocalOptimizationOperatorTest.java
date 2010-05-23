package org.louCityCreator.ga;

import org.jgap.*;
import org.jgap.event.EventManager;
import org.jgap.impl.*;
import org.jgap.util.ICloneable;
import org.junit.Before;
import org.junit.Test;
import org.louCityCreator.game.BuildingCode;

import static junit.framework.Assert.assertEquals;

public class LocalOptimizationOperatorTest {

    private Configuration configuration;
    private int populationSize = 3;

    @Before
    public void setup() {
        Configuration.reset();
        configuration = new DefaultConfiguration();
    }

    @Test
    public void testBestPosition() throws InvalidConfigurationException {
        BuildingGene[] genes = new BuildingGene[9];
        genes[0] = new BuildingGene(configuration, BuildingCode.COTTAGE);
        genes[1] = new BuildingGene(configuration, BuildingCode.COTTAGE);
        genes[2] = new BuildingGene(configuration, BuildingCode.COTTAGE);
        genes[3] = new BuildingGene(configuration, BuildingCode.COTTAGE);
        genes[4] = new BuildingGene(configuration, BuildingCode.COTTAGE);
        genes[5] = new BuildingGene(configuration, BuildingCode.COTTAGE);
        genes[6] = new BuildingGene(configuration, BuildingCode.COTTAGE);
        genes[7] = new BuildingGene(configuration, BuildingCode.COTTAGE);
        genes[8] = new BuildingGene(configuration, BuildingCode.COTTAGE);

        configuration.setFitnessFunction(new FitnessIsNice(9));
        Chromosome chromosome = new Chromosome(configuration, genes);
        LocalOptimizationOperator testObj = new LocalOptimizationOperator(configuration, 100);
        int result = testObj.findBestPosition(genes, BuildingCode.WOODCUTTER, 0.0);
        assertEquals(4, result);
    }

    @Test
    public void testGAWithOperator() throws InvalidConfigurationException {
        Configuration.reset();
        configuration = new MyTestConfiguration();
        configuration.addGeneticOperator(new LocalOptimizationOperator(configuration, 1));

        BuildingGene[] genes = new BuildingGene[9];
        genes[0] = new BuildingGene(configuration, BuildingCode.WOODCUTTER);
        genes[1] = new BuildingGene(configuration, BuildingCode.COTTAGE);
        genes[2] = new BuildingGene(configuration, BuildingCode.UNAVAILABLE);
        genes[3] = new BuildingGene(configuration, BuildingCode.COTTAGE);
        genes[4] = new BuildingGene(configuration, BuildingCode.FOREST);
        genes[5] = new BuildingGene(configuration, BuildingCode.GRASS);
        genes[6] = new BuildingGene(configuration, BuildingCode.FOREST);
        genes[7] = new BuildingGene(configuration, BuildingCode.GRASS);
        genes[8] = new BuildingGene(configuration, BuildingCode.COTTAGE);
        configuration.setPreservFittestIndividual(true);
        configuration.setKeepPopulationSizeConstant(false);
        Chromosome sampleChromosome = new Chromosome(configuration, genes);
        configuration.setSampleChromosome(sampleChromosome);
        configuration.setPopulationSize(populationSize);
        configuration.setFitnessFunction(new FitnessIsNice(9));
        Population population = new Population(configuration);
        for (int i = 0; i < populationSize; i++) {
            sampleChromosome = new Chromosome(configuration,
                    genes);
            population.addChromosome(sampleChromosome);
        }
        Genotype genotype = new Genotype(configuration, population);

        for(int i=0; i<10;i++)
        genotype.evolve();

        IChromosome fittest = genotype.getFittestChromosome();
        System.out.println("Fittest Chromosome has fitness " +
                fittest.getFitnessValue());
        for (Gene gene : genotype.getFittestChromosome().getGenes()) {
            System.out.print(gene.getAllele());
        }
        assertEquals(825, (int)fittest.getFitnessValue() );
    }

}

class MyTestConfiguration extends Configuration implements ICloneable {

    /**
     * String containing the CVS revision. Read out via reflection!
     */
    private final static String CVS_REVISION = "$Revision: 1.26 $";

    public MyTestConfiguration() {
        this("", "");
    }

    /**
     * Constructs a new DefaultConfiguration instance with a number of
     * configuration settings set to default values. It is still necessary
     * to set the sample Chromosome, population size, and desired fitness
     * function. Other settings may optionally be altered as desired.
     *
     * @param a_id   unique id for the configuration within the current thread
     * @param a_name informative name of the configuration, may be null
     * @author Neil Rotstan
     * @author Klaus Meffert
     * @since 1.0
     */
    public MyTestConfiguration(String a_id, String a_name) {
        super(a_id, a_name);
        try {
            setBreeder(new GABreeder());
            setRandomGenerator(new StockRandomGenerator());
            setEventManager(new EventManager());
            BestChromosomesSelector bestChromsSelector = new BestChromosomesSelector(
                    this, 0.90d);
            bestChromsSelector.setDoubletteChromosomesAllowed(true);
            addNaturalSelector(bestChromsSelector, false);
            setMinimumPopSizePercent(0);
            //
            setSelectFromPrevGen(1.0d);
            setKeepPopulationSizeConstant(true);
            setFitnessEvaluator(new DefaultFitnessEvaluator());
            setChromosomePool(new ChromosomePool());
            //addGeneticOperator(new CrossoverOperator(this, 0.35d));
            //addGeneticOperator(new MutationOperator(this, 12));
        }
        catch (InvalidConfigurationException e) {
            throw new RuntimeException(
                    "Fatal error: DefaultConfiguration class could not use its "
                            + "own stock configuration values. This should never happen. "
                            + "Please report this as a bug to the JGAP team.");
        }
    }

    /**
     * @return deep clone of this instance
     * @author Klaus Meffert
     * @since 3.2
     */
    public Object clone() {
        return super.clone();
    }
}

