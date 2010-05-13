package idar.loup.game;

import org.jgap.Gene;

import java.util.ArrayList;
import java.util.List;


public class Map {
    BuildingCode[] buildings;
    private int dimension;

    public Map(Gene[] genes) {
        dimension = (int)java.lang.Math.sqrt(genes.length);
        buildings = new BuildingCode[genes.length];
        int i= 0;
        for (Gene gene : genes) {
            buildings[i++] = BuildingCode.fromValue((String) gene.getAllele());
        }
    }

    public int getWoodProduction(){
        int woodproduction = 0;
        for(int i=0;i<dimension;i++)
            for(int j=0;j<dimension;j++){
            if(BuildingCode.WOODCUTTER.equals(getBuilding(i,j))){
                woodproduction += getWoodProduction(getNeighbours(i,j));
            }

        }
        return woodproduction;
    }

    private int getWoodProduction(List<BuildingCode> neighbours) {
        double forestbonus = 0;
        double sawmillbonus = 0;
        double workforcebonus = 0;
        for (BuildingCode neighbour : neighbours) {
            if(neighbour.equals(BuildingCode.COTTAGE)){
                workforcebonus += 0.5;
            }
            if(neighbour.equals(BuildingCode.SAWMILL)){
                sawmillbonus=0.75;
            }
            if(neighbour.equals(BuildingCode.FOREST)){
                forestbonus += 0.25;
            }
        }
        return (int) (300 * (1+forestbonus+workforcebonus)*(1+sawmillbonus));

    }

    public List<BuildingCode> getNeighbours(int i, int j) {
        ArrayList<BuildingCode> neighbours = new ArrayList<BuildingCode>();
        try{neighbours.add(getBuilding(i+1,j));}catch (Exception e){}
        try{neighbours.add(getBuilding(i-1,j));}catch (Exception e){}
        try{neighbours.add(getBuilding(i+1,j+1));}catch (Exception e){}
        try{neighbours.add(getBuilding(i-1,j+1));}catch (Exception e){}
        try{neighbours.add(getBuilding(i,j+1));}catch (Exception e){}
        try{neighbours.add(getBuilding(i,j-1));}catch (Exception e){}
        try{neighbours.add(getBuilding(i+1,j-1));}catch (Exception e){}
        try{neighbours.add(getBuilding(i-1,j-1));}catch (Exception e){}
        return neighbours;
    }

    public BuildingCode getBuilding(int i, int j){
        if(i<0||j<0||i>dimension-1||j>dimension-1) throw new IllegalArgumentException("Index out of bounds i:" + i + " j:" + j);
        return buildings[(j*dimension)+i];        
    }
}
