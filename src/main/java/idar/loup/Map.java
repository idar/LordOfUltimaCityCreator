package idar.loup;

import idar.loup.buildings.Building;
import idar.loup.buildings.BuildingFactory;
import org.jgap.IChromosome;

import java.util.ArrayList;
import java.util.List;

public class Map {

    public Building[][] array = new Building[21][21];

    public Map(String sharestring){
        System.out.println(sharestring.length());
        if(sharestring.length() != 21*21) {
            throw new RuntimeException("Sharestring must be "+21*21+" characters long");
        }
        int pos = 0;
        for(int i = 0; i< 21; i++){
            for(int j = 0; j<21;j++){
                array[i][j] = BuildingFactory.create(""+sharestring.charAt(pos++));
            }
        }
    }

    public String getShareString(){
         String str = "";
        for(int i = 0; i<21;i++){
            for(int j = 0; j<21;j++){
                str += array[i][j];
            }
        }
        return str;
    }

    public String toString(){
        String str = "";
        for(int i = 0; i<21;i++){
            for(int j = 0; j<21;j++){
                str += array[i][j];
            }
            str+="\n";
        }
        return str;
    }

    private List<Building> getNeighbours(int i,int j){
        ArrayList<Building> result = new ArrayList<Building>();
        result.add(array[i-1][j]);
        result.add(array[i+1][j]);
        result.add(array[i][j-1]);
        result.add(array[i+1][j-1]);
        result.add(array[i-1][j-1]);
        result.add(array[i][j+1]);
        result.add(array[i+1][j+1]);
        result.add(array[i-1][j+1]);
        return result;
    }

    public int getWoodProduction(){
        int woodproduction = 0;
        for(int i = 1; i<20;i++){
            for(int j = 1; j<20;j++){
                woodproduction += array[i][j].getWoodProduction(getNeighbours(i,j));
            }
        }
        return woodproduction;
    }

    public int getStoneProduction() {
        int stoneproduction = 0;
        for(int i = 1; i<20;i++){
            for(int j = 1; j<20;j++){
                stoneproduction += array[i][j].getStoneProduction(getNeighbours(i,j));
            }
        }
        return stoneproduction;
    }
}
