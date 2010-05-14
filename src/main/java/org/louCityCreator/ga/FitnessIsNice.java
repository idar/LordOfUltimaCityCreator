package org.louCityCreator.ga;

import org.louCityCreator.game.BuildingCode;
import org.louCityCreator.game.Map;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;

public class FitnessIsNice extends FitnessFunction {
    private int maxBuildings;

    public FitnessIsNice(int maxBuildings) {
        this.maxBuildings = maxBuildings;
    }

    public FitnessIsNice() {
        this.maxBuildings = Integer.MAX_VALUE;
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
            return 0.1;
        }
        return 1;
    }
}
