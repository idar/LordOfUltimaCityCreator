package org.louCityCreator;

import org.louCityCreator.ga.BuildingGene;
import org.louCityCreator.game.BuildingCode;
import org.louCityCreator.game.Map;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.louCityCreator.game.MapCorner;
import org.louCityCreator.game.MapPart;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class MapTest {
    private DefaultConfiguration configuration;
    private String center;
    private String sharestring;
    private String topleftCorner;
    private String topRightCorner;
    private String bottomLeftCorner;
    private String bottomRightCorner;
    private Map testObj;

    @Before
    public void setup() {
        Configuration.reset();
        configuration = new DefaultConfiguration();

        sharestring = "[ShareString.1.2]:" +
                "#####################" +
                "###---.---#-------###" +
                "##--------#--------##" +
                "#--..-----#----;----#" +
                "#-----:--.#---------#" +
                "#------#######------#" +
                "#-----##-----##---;-#" +
                "#--:-##-------##;---#" +
                "#----#---------#----#" +
                "#----#--;-WW---#----#" +
                "######---.TL---######" +
                "#----#---WWW---#----#" +
                "#----#---------#----#" +
                "#---:##-,---.-##----#" +
                "#-----##-----##--,--#" +
                "#------#######------#" +
                "#:-------:#---------#" +
                "#----:----#-----,---#" +
                "##------:-#---,----##" +
                "###-------#-------###" +
                "#####################";

        center =
                "###########" +
                        "###-----###" +
                        "##-------##" +
                        "#---------#" +
                        "#--;-WW---#" +
                        "#---.TL---#" +
                        "#---WWW---#" +
                        "#---------#" +
                        "##-,---.-##" +
                        "###-----###" +
                        "###########";

        topleftCorner =
                "###########" +
                        "###---.---#" +
                        "##--------#" +
                        "#--..-----#" +
                        "#-----:--.#" +
                        "#------####" +
                        "#-----#####" +
                        "#--:-######" +
                        "#----######" +
                        "#----######" +
                        "##########T";

        topRightCorner =
                "###########" +
                        "#-------###" +
                        "#--------##" +
                        "#----;----#" +
                        "#---------#" +
                        "####------#" +
                        "#####---;-#" +
                        "######;---#" +
                        "######----#" +
                        "######----#" +
                        "T##########";

        bottomLeftCorner =
                "##########T" +
                        "#----######" +
                        "#----######" +
                        "#---:######" +
                        "#-----#####" +
                        "#------####" +
                        "#:-------:#" +
                        "#----:----#" +
                        "##------:-#" +
                        "###-------#" +
                        "###########";

        bottomRightCorner =
                "T##########" +
                        "######----#" +
                        "######----#" +
                        "######----#" +
                        "#####--,--#" +
                        "####------#" +
                        "#---------#" +
                        "#-----,---#" +
                        "#---,----##" +
                        "#-------###" +
                        "###########";

        SharestringParser parser = new SharestringParser(sharestring);
        testObj = new Map(createGenes(parser.getMap()));
    }

    @Test
    public void testWoodProduction() {
        Map testObj = new Map(createGenes("WWWWWWWWW"));
        assertEquals(300 * 9, testObj.getWoodProduction());

        testObj = new Map(createGenes("CW-------"));
        assertEquals((int) (300 * 1.5), testObj.getWoodProduction());

        testObj = new Map(createGenes("-C-WLW-C-"));
        assertEquals((int) (2100), testObj.getWoodProduction());

        testObj = new Map(createGenes("WCWWLWWCW"));
        assertEquals((int) (5248), testObj.getWoodProduction()); //5250

    }

    @Test
    public void tetGetNeighbours() {
        Map testObj = new Map(createGenes("WWCLCWWWC"));
        List<BuildingCode> list = testObj.getNeighbours(0, 0);
        assertEquals(3, list.size());
    }

    @Test
    public void testMapCoordinates() {
        Map testObj = new Map(createGenes("CW-------"));
        assertEquals(BuildingCode.COTTAGE, testObj.getBuilding(0, 0));
        assertEquals(BuildingCode.WOODCUTTER, testObj.getBuilding(1, 0));
        assertEquals(BuildingCode.GRASS, testObj.getBuilding(0, 1));
        assertEquals(BuildingCode.GRASS, testObj.getBuilding(1, 1));
        assertEquals(BuildingCode.GRASS, testObj.getBuilding(2, 1));
        assertEquals(BuildingCode.GRASS, testObj.getBuilding(2, 2));
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

    @Test
    public void testMapSplitting() {
        assertTrue(testObj.isComplete());

        String mapTopLeftCorner = testObj.getPart(MapCorner.TOPLEFT).toString();
        assertEquals(topleftCorner, mapTopLeftCorner);

        String mapTopRightCorner = testObj.getPart(MapCorner.TOPRIGHT).toString();
        assertEquals(topRightCorner, mapTopRightCorner);

        String mapBottomLeftCorner = testObj.getPart(MapCorner.BOTTOMLEFT).toString();
        assertEquals(bottomLeftCorner, mapBottomLeftCorner);

        String mapBottomRightCorner = testObj.getPart(MapCorner.BOTTOMRIGHT).toString();
        assertEquals(bottomRightCorner, mapBottomRightCorner);

        String mapCenter = testObj.getPart(MapCorner.CENTER).toString();
        assertEquals(center, mapCenter);
    }

    @Test
    public void testGetShareString() {
        assertEquals(sharestring, testObj.getShareString().getCompleteShareString());
    }

    @Test
    public void testSetMapCorners() {
        testObj.setPart(new MapPart(center, MapCorner.CENTER));
        assertEquals(sharestring, testObj.getShareString().getCompleteShareString());

        testObj.setPart(new MapPart(bottomLeftCorner,MapCorner.BOTTOMLEFT));
        assertEquals(sharestring, testObj.getShareString().getCompleteShareString());

        testObj.setPart(new MapPart(bottomRightCorner,MapCorner.BOTTOMRIGHT));
        assertEquals(sharestring, testObj.getShareString().getCompleteShareString());

        testObj.setPart(new MapPart(topleftCorner,MapCorner.TOPLEFT));
        assertEquals(sharestring, testObj.getShareString().getCompleteShareString());

        testObj.setPart(new MapPart(topRightCorner,MapCorner.TOPRIGHT));
        assertEquals(sharestring, testObj.getShareString().getCompleteShareString());
    }
}
