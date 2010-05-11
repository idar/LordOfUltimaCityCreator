package idar.loup.buildings;

import java.util.List;

public class TownHall extends Building {

    public TownHall(){
        sign="T";
    }

    @Override
    public int getWoodProduction(List<Building> neighbours) {
        return 300;
    }
}
