package idar.loup;

import org.junit.Ignore;
import org.junit.Test;

public class GaTest {
    private String sharestring = "#######----##----##----##----#######";

    @Test
    @Ignore
    public void TestGA() {
        Map map = new Map(sharestring);
        GARunner runner = new GARunner(map, new FitnessIsGood(sharestring));
        runner.run();
    }
}
