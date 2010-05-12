package idar.loup;

import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;

public class FitnessIsGood extends FitnessFunction{
    @Override
    protected double evaluate(IChromosome iChromosome) {
        Gene[] genes = iChromosome.getGenes();
        int fitness = 0;
        for (Gene gene : genes) {
            if(".".equals(gene.getAllele())){
                fitness++;
            }
        }
        return fitness;
    }
}
