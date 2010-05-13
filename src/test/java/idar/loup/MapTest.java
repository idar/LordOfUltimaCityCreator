package idar.loup;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class MapTest {

    private String sharestring;
    private Map testObj;

    @Before
    public void setup(){
        sharestring = "########################-...---#..-,-:-#####WCWCWCW-#-------,###CWLWCWLWC#---.-;---##-WCWCWCW-#-:-------##CICIC-#######-,,,--##-IDI-##.-:--##,-,-.##--C-##-.-:-,,##-,--##-,--#----:-,--#--,-##-,--#---------#.---#######-,,-T----#######---,#-,------;#--..##..-,#---..-:--#-..-##-.C-##-..--:-##-.-.##-IDI.##--.--##-----##CICIC-#######--:---##-QCQCQCQ,#::-;-----##CQAQCQAQC#-----,,--###QCQCQCQ-#----,,--#####---:-:-#-;---,-########################";
        testObj = new Map(sharestring);
    }
    @Test
    @Ignore
    public void testSharestring(){
        assertEquals(sharestring,testObj.getShareString());
        sharestring = "########################---.---#-------#####--------#--------###--..-----#----;----##-----:--.#---------##------#######------##-----##-----##---;-##--:-##-------##;---##----#---------#----##----#--;------#----#######----T-:--#######----#---------#----##----#---------#----##---:##-,---.-##----##-----##-----##--,--##------#######------##:-------:#---------##----:----#-----,---###------:-#---,----#####-------#-------########################";
        testObj = new Map(sharestring);
        assertEquals(sharestring,testObj.getShareString());
    }

    @Test
    @Ignore
    public void testChromosoneString(){
        sharestring = "########################---.---#-------#####--------#--------###--..-----#----;----##-----:--.#---------##------#######------##-----##-----##---;-##--:-##-------##;---##----#---------#----##----#--;------#----#######----T-:--#######----#---------#----##----#---------#----##---:##-,---.-##----##-----##-----##--,--##------#######------##:-------:#---------##----:----#-----,---###------:-#---,----#####-------#-------########################";
        testObj = new Map(sharestring);
        String shortstring = testObj.getShortShareString();
        shortstring = shortstring.replaceFirst("--","WW");
        testObj.setShortString(shortstring);
        sharestring = "########################WW-.---#-------#####--------#--------###--..-----#----;----##-----:--.#---------##------#######------##-----##-----##---;-##--:-##-------##;---##----#---------#----##----#--;------#----#######----T-:--#######----#---------#----##----#---------#----##---:##-,---.-##----##-----##-----##--,--##------#######------##:-------:#---------##----:----#-----,---###------:-#---,----#####-------#-------########################";
        assertEquals(sharestring,testObj.getShareString());


    }

    @Test
    @Ignore
    public void testWoodProduction(){
        System.out.println(testObj.getWoodProduction());
    }

    @Test
    @Ignore
    public void testStoneProduction(){
        System.out.println(testObj.getStoneProduction());
    }
}
