package org.louCityCreator;

import org.louCityCreator.ga.FitnessIsNice;
import org.louCityCreator.ga.GA;
import org.jgap.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.louCityCreator.ga.GaRunner;
import org.louCityCreator.game.ShareString;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class GaTest {

    @Before
    public void setup() {
        Configuration.reset();
    }

    @Test
    public void TestGA() {
        GA runner = new GA(new FitnessIsNice(9), "---------");
        runner.setPopulationSize(100);
        runner.setNumEvolutions(20);
        String chromosome = runner.run();
        assertTrue(4500< (int) runner.getFitness()); // 5250
        System.out.println(chromosome);
    }

    @Test
    public void testUnavailableBuilding() {
        GA runner = new GA(new FitnessIsNice(9), "----#----");
        runner.setPopulationSize(100);
        runner.setNumEvolutions(20);
        String chromosome = runner.run();
        assertEquals(3524, (int) runner.getFitness()); // 3525
        System.out.println(chromosome);
    }

    @Test
    public void testTownHall() {
        GA runner = new GA(new FitnessIsNice(9), "----T----");
        runner.setPopulationSize(100);
        runner.setNumEvolutions(20);
        String chromosome = runner.run();
        assertEquals(3824, (int) runner.getFitness()); // 3825
        System.out.println(chromosome);
    }

    @Test
    public void testForest() {
        GA runner = new GA(new FitnessIsNice(5), "---.T----");
        runner.setPopulationSize(100);
        runner.setNumEvolutions(50);
        String chromosome = runner.run();
        assertEquals(2137, (int) runner.getFitness()); 
        System.out.println(chromosome);
    }

    @Test
    public void testShareStringMap(){
        String sharestring = "########################------,#;-...--#####:-,,--,,#--------###-:--,---,#---------##---------#-------::##------#######------##---,-##----.##-----##--,,##--,,---##----##---,#--,,-,-:-#----##-.-,#-------::#,,--#######-:--T----#######-:--#---------#.---##---:#.-..----:#..--##-,-:##-.---::##.-..##-----##.-;--##---.-##::-.--#######-;----##-----,-::#------:--##--------:#------:::###--------#--,,--:-#####::-;-..#--,----########################";
        GaRunner runner = new GaRunner(new FitnessIsNice(0), sharestring);
        runner.setPopulationSize(1);
        runner.setNumEvolutions(1);

        ShareString result = runner.run();
        System.out.println(sharestring);
        System.out.println(result.getSharestring());
        assertEquals(sharestring, result.getSharestring());
    }
}
