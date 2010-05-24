package org.louCityCreator.game;

public class MapPart {

    private String map;
    private MapCorner corner;

    public MapPart(String map, MapCorner mapCorner) {
        this.map = map;
        corner = mapCorner;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public MapCorner getCorner() {
        return corner;
    }

    public void setCorner(MapCorner corner) {
        this.corner = corner;
    }

    @Override
    public String toString() {
        return map;
    }
}
