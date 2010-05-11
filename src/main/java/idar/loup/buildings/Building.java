package idar.loup.buildings;

import java.util.List;

public class Building {
    protected String sign;

    @Override
    public String toString() {
        return sign;
    }

    public int getWoodProduction(List<Building> neighbours){
        return 0;
    }

    public int getStoneProduction(List<Building> neighbours) {
        return 0;
    }
}
