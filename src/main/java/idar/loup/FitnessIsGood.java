package idar.loup;

import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;

public class FitnessIsGood extends FitnessFunction{
    private Map map;

    public FitnessIsGood(String sharestring) {
        map = new Map(sharestring);
    }

    @Override
    protected double evaluate(IChromosome iChromosome) {
        String chromosone = createString(iChromosome);
        map.setShortString(chromosone);
        //System.out.println(chromosone + " "  + map.getWoodProduction());
        return map.getWoodProduction();
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
