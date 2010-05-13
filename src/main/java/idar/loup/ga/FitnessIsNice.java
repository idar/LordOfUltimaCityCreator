package idar.loup.ga;

import idar.loup.game.Map;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;

public class FitnessIsNice extends FitnessFunction{
    @Override
    protected double evaluate(IChromosome chromosome) {
        Gene[] genes = chromosome.getGenes();

        return new Map(genes).getWoodProduction();

    }
}
