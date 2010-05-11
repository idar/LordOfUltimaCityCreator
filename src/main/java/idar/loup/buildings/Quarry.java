package idar.loup.buildings;

import java.util.List;

public class Quarry extends Building {

    public Quarry(){
        sign="Q";
    }

    public int getStoneProduction(List<Building> neighbours){
        double stonebonus = 0;
        double stonemasonbonus = 0;
        double workforcebonus = 0;
        for (Building neighbour : neighbours) {
            if(neighbour instanceof Stone){
                stonebonus += 0.25;
            }
            if(neighbour instanceof Stonemason){
                stonemasonbonus = 0.75;
            }
            if(neighbour instanceof Cottage){
                workforcebonus += 0.5;
            }
        }
        return (int) (300 * (1+stonebonus+workforcebonus)*(1+stonemasonbonus));
    }
}
