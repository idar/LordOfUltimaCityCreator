package idar.loup.SimpleAttempt;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class GaTest {

    @Test
    public void TestGA() {
        GArunner runner = new GArunner(new FitnessIsNice(), "---------");
        runner.setPopulationSize(50);
        runner.setNumEvolutions(75);
        String chromosome = runner.run();
        assertEquals(5248,(int)runner.getFitness()); // 5250
        System.out.println(chromosome);
    }

    @Test
    public void testUnavailableBuilding(){
        GArunner runner = new GArunner(new FitnessIsNice(), "----#----");
        runner.setPopulationSize(50);
        runner.setNumEvolutions(75);
        String chromosome = runner.run();
        assertEquals(3524,(int)runner.getFitness()); // 3525
        System.out.println(chromosome);
    }
}
