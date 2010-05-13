package idar.loup.SimpleAttempt;

import org.jgap.Configuration;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TestBuildingGene {

    private DefaultConfiguration configuration = new DefaultConfiguration();

    @Before
    public void setup(){
        Configuration.reset();
    }

    @Test
    public void testUnavailable_means_not_change() throws InvalidConfigurationException {
        BuildingGene testObj = new BuildingGene(configuration, BuildingCode.UNAVAILABLE);
        testObj.applyMutation(0,1.0);
        assertEquals("#",testObj.getInternalValue());
        testObj.setToRandomValue(configuration.getRandomGenerator());
        assertEquals("#",testObj.getInternalValue());
        testObj.setAllele("-");
        assertEquals("-",testObj.getInternalValue());
    }
}
