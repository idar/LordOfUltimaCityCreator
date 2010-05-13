package idar.loup.SimpleAttempt;

import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class MapTest {
    private DefaultConfiguration configuration = new DefaultConfiguration();

    @Before
    public void setup(){
        Configuration.reset();
    }
    @Test
    public void testWoodProduction(){
        Map testObj = new Map(createGenes("WWWWWWWWW"));
        assertEquals(300*9,testObj.getWoodProduction());

        testObj = new Map(createGenes("CW-------"));
        assertEquals((int)(300*1.5),testObj.getWoodProduction());

        testObj = new Map(createGenes("-C-WLW-C-"));
        assertEquals((int)(2100),testObj.getWoodProduction());

        testObj = new Map(createGenes("WCWWLWWCW"));
        assertEquals((int)(5248),testObj.getWoodProduction()); //5250

    }

    @Test
    public void tetGetNeighbours(){
        Map testObj = new Map(createGenes("WWCLCWWWC"));
        List<BuildingCode> list = testObj.getNeighbours(0, 0);
        assertEquals(3,list.size());
    }

    @Test
    public void testMapCoordinates(){
        Map testObj = new Map(createGenes("CW-------"));
        assertEquals(BuildingCode.COTTAGE,testObj.getBuilding(0,0));
        assertEquals(BuildingCode.WOODCUTTER,testObj.getBuilding(1,0));
        assertEquals(BuildingCode.GRASS,testObj.getBuilding(0,1));
        assertEquals(BuildingCode.GRASS,testObj.getBuilding(1,1));
        assertEquals(BuildingCode.GRASS,testObj.getBuilding(2,1));
        assertEquals(BuildingCode.GRASS,testObj.getBuilding(2,2));
    }

    public Gene[] createGenes(String str){
        Gene[] gene = new Gene[str.length()];
        for(int i=0;i<gene.length;i++){
            try {
                gene[i] = new BuildingGene(configuration,BuildingCode.fromValue(String.valueOf(str.charAt(i))));
            } catch (InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }
        }
        return gene;
    }
}
