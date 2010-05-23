package org.louCityCreator.ga;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.louCityCreator.game.BuildingCode;

import static junit.framework.Assert.assertTrue;

public class FitnessIsNiceTester {

private DefaultConfiguration configuration;

    @Before
    public void setup() {
        Configuration.reset();
        configuration = new DefaultConfiguration();
    }


    @Test
    public void testBuildingPenalty() throws InvalidConfigurationException {

        FitnessIsNice testObj = new FitnessIsNice(5);

        Gene[] genes = createGenes("WWWCCCWWW");
        Chromosome chromosome = new Chromosome(configuration, genes);
        double value = testObj.evaluate(chromosome);
        assertTrue(value < 3000);
    }

    public Gene[] createGenes(String str) {
        Gene[] gene = new Gene[str.length()];
        for (int i = 0; i < gene.length; i++) {
            try {
                gene[i] = new BuildingGene(configuration, BuildingCode.fromValue(String.valueOf(str.charAt(i))));
            } catch (InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }
        }
        return gene;
    }
}
