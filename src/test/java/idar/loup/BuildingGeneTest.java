package idar.loup;

import org.jgap.Configuration;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class BuildingGeneTest {
    Configuration gaConf;

    @Before
    public void setup() {
        gaConf = new DefaultConfiguration();
        Configuration.reset();
    }

    @Test
    public void testRandomValue_should_not_change() throws InvalidConfigurationException {
        BuildingGene testObj = new BuildingGene(gaConf, BuildingCode.UNAVAILABLE.value());
        testObj.setAllele(BuildingCode.LAKE.value());
        assertEquals(BuildingCode.UNAVAILABLE, testObj.getSign());

        testObj = new BuildingGene(gaConf, BuildingCode.TOWNHALL.value());
        testObj.setAllele(BuildingCode.FARM.value());
        assertEquals(BuildingCode.TOWNHALL, testObj.getSign());
        for (int i = 0; i < 10; i++) {
            testObj.setToRandomValue(gaConf.getRandomGenerator());
            assertEquals(BuildingCode.TOWNHALL, testObj.getSign());
        }
        testObj = new BuildingGene(gaConf, BuildingCode.FARM.value());
        testObj.setAllele(BuildingCode.TOWNHALL.value());
        assertEquals(BuildingCode.FARM,testObj.getSign());

    }

    @Test
    public void testRandomValue_should_not_have_invalid_values() throws InvalidConfigurationException {
        BuildingGene testObj;
        for (int i = 0; i < 50; i++) {
            testObj = new BuildingGene(gaConf, BuildingCode.STONE.value());
            testObj.setToRandomValue(gaConf.getRandomGenerator());
            assertFalse("Stone should not be " + BuildingCode.FOREST, BuildingCode.FOREST.equals(testObj.getSign()));
            assertFalse("Stone should not be " + BuildingCode.IRON, BuildingCode.IRON.equals(testObj.getSign()));
            assertFalse("Stone should not be " + BuildingCode.WATER, BuildingCode.WATER.equals(testObj.getSign()));
            assertFalse("Stone should not be " + BuildingCode.LAKE, BuildingCode.LAKE.equals(testObj.getSign()));
            assertFalse("Stone should not be " + BuildingCode.TOWNHALL, BuildingCode.TOWNHALL.equals(testObj.getSign()));
            assertFalse("Stone should not be " + BuildingCode.UNAVAILABLE, BuildingCode.UNAVAILABLE.equals(testObj.getSign()));
            assertFalse("Stone should not be " + BuildingCode.LAKE, BuildingCode.LAKE.equals(testObj.getSign()));
        }
    }

    @Test
    public void testCompareToAndEquals() throws InvalidConfigurationException {
        BuildingGene testObj = new BuildingGene(gaConf, BuildingCode.GRASS.value());
        BuildingGene testObj2 = new BuildingGene(gaConf, BuildingCode.GRASS.value());
        assertEquals(testObj,testObj2);
        assertEquals(0,testObj.compareTo(testObj2));
    }

}
