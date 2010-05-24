package org.louCityCreator.ga;

import org.louCityCreator.game.MapCorner;

public class Result {

    private String chromosome;
    private double fitness;
    private MapCorner corner;

    public Result(String chromosome, double fitness, MapCorner corner) {
        this.chromosome = chromosome;
        this.fitness = fitness;
        this.corner = corner;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public MapCorner getCorner() {
        return corner;
    }

    public void setCorner(MapCorner corner) {
        this.corner = corner;
    }

    public boolean betterThan(Result best) {
        if(best == null) return true;
        if(best.getFitness() < this.getFitness()) return true;
        return false;
    }
}
