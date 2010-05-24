package org.louCityCreator.ga;

import org.junit.Test;
import org.louCityCreator.game.MapCorner;

import static junit.framework.Assert.assertTrue;

public class ResultTest {

     @Test
    public void testBetterThan(){
         Result worse = new Result("1", 1.0, MapCorner.CENTER);
         Result better = new Result("2", 2.0, MapCorner.CENTER);
         assertTrue(better.betterThan(worse));
     }
}
