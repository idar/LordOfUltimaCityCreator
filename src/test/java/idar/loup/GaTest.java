package idar.loup;

import idar.loup.ga.FitnessIsNice;
import idar.loup.ga.GArunner;
import org.jgap.Configuration;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class GaTest {

    @Before
    public void setup(){
        Configuration.reset();
    }
    
    @Test
    public void TestGA() {
        GArunner runner = new GArunner(new FitnessIsNice(), "---------");
        runner.setPopulationSize(100);
        runner.setNumEvolutions(200);
        String chromosome = runner.run();
        assertEquals(5248,(int)runner.getFitness()); // 5250
        System.out.println(chromosome);
    }

    @Test
    public void testUnavailableBuilding(){
        GArunner runner = new GArunner(new FitnessIsNice(), "----#----");
        runner.setPopulationSize(100);
        runner.setNumEvolutions(200);
        String chromosome = runner.run();
        assertEquals(3524,(int)runner.getFitness()); // 3525
        System.out.println(chromosome);
    }

    @Test
    public void testTownHall(){
        GArunner runner = new GArunner(new FitnessIsNice(), "----T----");
        runner.setPopulationSize(100);
        runner.setNumEvolutions(200);
        String chromosome = runner.run();
        assertEquals(3824,(int)runner.getFitness()); // 3825
        System.out.println(chromosome);
    }
}
