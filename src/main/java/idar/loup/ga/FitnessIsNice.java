package idar.loup.ga;

import idar.loup.game.BuildingCode;
import idar.loup.game.Map;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;

public class FitnessIsNice extends FitnessFunction {
    private int maxBuildings;

    public FitnessIsNice(int maxBuildings) {
        this.maxBuildings = maxBuildings;
    }

    @Override
    protected double evaluate(IChromosome chromosome) {
        Gene[] genes = chromosome.getGenes();
        double buildingpenalty = buildingPenalty(genes);
        return new Map(genes).getWoodProduction() * buildingpenalty;

    }

    private double buildingPenalty(Gene[] genes) {
        int buildings = 0;
        for (Gene gene : genes) {
            if (BuildingCode.fromValue(String.valueOf(gene.getAllele())).isBuilding()) {
                buildings++;
            }
        }
        if (buildings > maxBuildings) {
            return 0.5;
        }
        return 1;
    }
}
