package idar.loup.SimpleAttempt;

import org.junit.Test;

public class GaTest {

        @Test
    public void TestGA() {
        GArunner runner = new GArunner(new FitnessIsNice());
        runner.run();
    }
}
