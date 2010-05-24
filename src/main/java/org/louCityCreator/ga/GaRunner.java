package org.louCityCreator.ga;

import org.jgap.impl.DefaultConfiguration;
import org.louCityCreator.game.Map;
import org.louCityCreator.game.MapCorner;
import org.louCityCreator.game.MapPart;
import org.louCityCreator.game.ShareString;

import java.util.concurrent.*;

public class GaRunner {
    private int populationSize;
    private int numEvolutions;
    private FitnessIsNice fitnessFunction;
    private String initialMap;
    private Map map;

    public GaRunner(FitnessIsNice fitnessFunction, String initialMap) {
        this.fitnessFunction = fitnessFunction;
        this.initialMap = initialMap;        
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public void setNumEvolutions(int numEvolutions) {
        this.numEvolutions = numEvolutions;
    }

    public ShareString run() {
        map = new Map(initialMap);
        if(map.isComplete()){
            runCorners();
            return map.getShareString();
        }else {
        GA ga = new GA(fitnessFunction, initialMap);
        ga.setNumEvolutions(numEvolutions);
        ga.setPopulationSize(populationSize);
        return new ShareString(ga.run());
        }
    }

    private void runCorners() {
        int threads = Runtime.getRuntime().availableProcessors();

        ThreadPoolExecutor executor =  new ThreadPoolExecutor(threads, threads, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        CompletionService<Result> service = new ExecutorCompletionService<Result>(executor);

        service.submit(createGa("1", "Center", MapCorner.CENTER));
        service.submit(createGa("2", "TopLeft", MapCorner.TOPLEFT));
        service.submit(createGa("3", "TopRight", MapCorner.TOPRIGHT));
        service.submit(createGa("4", "BottomLeft", MapCorner.BOTTOMLEFT));
        service.submit(createGa("5", "BottomRight", MapCorner.BOTTOMRIGHT));

        Result best = null;
        try {
            for(int i = 0; i <5 ; i++){
                Result result = service.take().get();
                if(result.betterThan(best)) best = result;
            }
            executor.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        map.setPart(new MapPart(best.getChromosome(),best.getCorner()));

    }

    private GA createGa(String id, String name, MapCorner corner) {
        DefaultConfiguration gaConf = new DefaultConfiguration(id, name);
        GA gaCenter = new GA(fitnessFunction, map.getPart(corner).toString(),numEvolutions, populationSize, gaConf);
        gaCenter.setCorner(corner);
        return gaCenter;
    }
}
