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
        int production = new Map(genes).getWoodProduction();
        if(buildingpenalty < 0 || production < 0) System.out.println("buildingpenalty " + buildingpenalty + " production " + production); 
        return production * buildingpenalty;

    }

    private double buildingPenalty(Gene[] genes) {
        int buildings = 0;
        for (Gene gene : genes) {
            if (BuildingCode.fromValue(String.valueOf(gene.getAllele())).isBuilding()) {
                buildings++;
            }
        }
        if (buildings > maxBuildings) {
            double penalty = 1 - ((buildings - maxBuildings) * 0.05);
            if(penalty < 0 ) return 0;
            return penalty;
        }
        return 1;
    }
}
