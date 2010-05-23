package org.louCityCreator;

import org.louCityCreator.ga.FitnessIsNice;
import org.louCityCreator.ga.GArunner;
import org.jgap.Configuration;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class GaTest {

    @Before
    public void setup() {
        Configuration.reset();
    }

    @Test
    public void TestGA() {
        GArunner runner = new GArunner(new FitnessIsNice(9), "---------");
        runner.setPopulationSize(100);
        runner.setNumEvolutions(20);
        String chromosome = runner.run();
        assertTrue(4500< (int) runner.getFitness()); // 5250
        System.out.println(chromosome);
    }

    @Test
    public void testUnavailableBuilding() {
        GArunner runner = new GArunner(new FitnessIsNice(9), "----#----");
        runner.setPopulationSize(100);
        runner.setNumEvolutions(20);
        String chromosome = runner.run();
        assertEquals(3524, (int) runner.getFitness()); // 3525
        System.out.println(chromosome);
    }

    @Test
    public void testTownHall() {
        GArunner runner = new GArunner(new FitnessIsNice(9), "----T----");
        runner.setPopulationSize(100);
        runner.setNumEvolutions(20);
        String chromosome = runner.run();
        assertEquals(3824, (int) runner.getFitness()); // 3825
        System.out.println(chromosome);
    }

    @Test
    public void testForest() {
        GArunner runner = new GArunner(new FitnessIsNice(5), "---.T----");
        runner.setPopulationSize(100);
        runner.setNumEvolutions(50);
        String chromosome = runner.run();
        assertEquals(2137, (int) runner.getFitness()); 
        System.out.println(chromosome);
    }
}
