package idar.loup.buildings;

import java.util.List;

public class Woodcutter extends Building {

    public Woodcutter(){
        sign = "W";
    }

    public int getWoodProduction(List<Building> neighbours){
        double forestbonus = 0;
        double sawmillbonus = 0;
        double workforcebonus = 0;
        for (Building neighbour : neighbours) {
            if(neighbour instanceof Forest){
                forestbonus += 0.25;
            }
            if(neighbour instanceof Sawmill){
                sawmillbonus = 0.75;
            }
            if(neighbour instanceof Cottage){
                workforcebonus += 0.5;
            }
        }
        return (int) (300 * (1+forestbonus+workforcebonus)*(1+sawmillbonus));
    }
}
