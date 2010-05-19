package org.louCityCreator;

import org.junit.After;
import org.louCityCreator.ga.BuildingGene;
import org.louCityCreator.game.BuildingCode;
import org.jgap.Configuration;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TestBuildingGene {

    private DefaultConfiguration configuration;

    @Before
    public void setup() {
        Configuration.reset();
        configuration = new DefaultConfiguration();
    }

    @After
    public void teardown(){
        Configuration.reset();
    }

    @Test
    public void testUnavailable_means_not_change() throws InvalidConfigurationException {
        BuildingGene testObj = new BuildingGene(configuration, BuildingCode.UNAVAILABLE);
        testObj.applyMutation(0, 1.0);
        assertEquals("#", testObj.getPersistentRepresentation());
        testObj.setToRandomValue(configuration.getRandomGenerator());
        assertEquals("#", testObj.getPersistentRepresentation());
        testObj.setAllele("-");
        assertEquals("-", testObj.getPersistentRepresentation());
    }

    @Test
    public void testSetValue() throws InvalidConfigurationException {
        BuildingGene testObj = new BuildingGene(configuration,BuildingCode.UNAVAILABLE);
        testObj.setToValue(BuildingCode.COTTAGE);
        assertEquals(BuildingCode.UNAVAILABLE,testObj.getBuilding());
        testObj = new BuildingGene(configuration,BuildingCode.FOREST);
        testObj.setToValue(BuildingCode.UNAVAILABLE);
        assertEquals(BuildingCode.FOREST,testObj.getBuilding());
        testObj = new BuildingGene(configuration, BuildingCode.GRASS);
        testObj.setToValue(BuildingCode.WOODCUTTER);
        assertEquals(BuildingCode.WOODCUTTER,testObj.getBuilding());     

    }
}
