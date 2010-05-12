package idar.loup;

import org.jgap.*;
import org.jgap.impl.BooleanGene;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.job.MaxFunction;
import org.junit.Test;

public class GaTest {
    private String sharestring = "########################---.---#-------#####--------#--------###--..-----#----;----##-----:--.#---------##------#######------##-----##-----##---;-##--:-##-------##;---##----#---------#----##----#--;------#----#######----T-:--#######----#---------#----##----#---------#----##---:##-,---.-##----##-----##-----##--,--##------#######------##:-------:#---------##----:----#-----,---###------:-#---,----#####-------#-------########################";

    @Test
    public void TestGA() {
        Map map = new Map(sharestring);
        GARunner runner = new GARunner(map, new FitnessIsGood());
        runner.run();
    }
}
