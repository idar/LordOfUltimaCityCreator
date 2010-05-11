package idar.loup;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CityTest {
    private City testObj;

    @Before
    public void setup(){
        testObj = new City();
    }

    @Test
    public void testMap(){
        Map map = new Map("########################-...---#..-,-:-#####WCWCWCW-#-------,###CWLWCWLWC#---.-;---##-WCWCWCW-#-:-------##CICIC-#######-,,,--##-IDI-##.-:--##,-,-.##--C-##-.-:-,,##-,--##-,--#----:-,--#--,-##-,--#---------#.---#######-,,-T----#######---,#-,------;#--..##..-,#---..-:--#-..-##-.C-##-..--:-##-.-.##-IDI.##--.--##-----##CICIC-#######--:---##-QCQCQCQ,#::-;-----##CQAQCQAQC#-----,,--###QCQCQCQ-#----,,--#####---:-:-#-;---,-########################");
        testObj.setMap(map);
        assertEquals(map,testObj.getMap());

    }
}
