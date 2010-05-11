package idar.loup;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class MapTest {

    @Test
    public void testSharestring(){
        String sharestring = "########################-...---#..-,-:-#####WCWCWCW-#-------,###CWLWCWLWC#---.-;---##-WCWCWCW-#-:-------##CICIC-#######-,,,--##-IDI-##.-:--##,-,-.##--C-##-.-:-,,##-,--##-,--#----:-,--#--,-##-,--#---------#.---#######-,,-T----#######---,#-,------;#--..##..-,#---..-:--#-..-##-.C-##-..--:-##-.-.##-IDI.##--.--##-----##CICIC-#######--:---##-QCQCQCQ,#::-;-----##CQAQCQAQC#-----,,--###QCQCQCQ-#----,,--#####---:-:-#-;---,-########################";
        Map map = new Map(sharestring);
        assertEquals(sharestring,map.getShareString());
    }
}
