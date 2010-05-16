package org.louCityCreator;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SharestringTester {

    @Test
    public void testSharestringparser(){
        String sharestring = "[ShareString.1.2]:########################---.---#-------#####--------#--------###--..-----#----;----##-----:--.#---------##------#######------##-----##-----##---;-##--:-##-------##;---##----#---------#----##----#--;-WW---#----#######---.TL---#######----#---WWW---#----##----#---------#----##---:##-,---.-##----##-----##-----##--,--##------#######------##:-------:#---------##----:----#-----,---###------:-#---,----#####-------#-------########################";
        String mapstring = sharestring.substring(18);

        SharestringParser parser = new SharestringParser(sharestring);

        assertEquals(mapstring,parser.getMap());
        int h_dim = parser.getDimension();
        assertEquals(21,h_dim);
    }
}
