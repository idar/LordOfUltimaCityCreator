package org.louCityCreator.game;

import org.jgap.Gene;

import java.util.ArrayList;
import java.util.List;


public class Map {
    BuildingCode[] buildings;
    private int dimension;

    public Map(Gene[] genes) {
        dimension = (int) java.lang.Math.sqrt(genes.length);
        buildings = new BuildingCode[genes.length];
        int i = 0;
        for (Gene gene : genes) {
            buildings[i++] = BuildingCode.fromValue((String) gene.getAllele());
        }
    }

    public Map(String initialMap) {
        dimension = (int) java.lang.Math.sqrt(initialMap.length());
        buildings = new BuildingCode[initialMap.length()];
        for (int i = 0; i < initialMap.length(); i++)
            buildings[i] = BuildingCode.fromValue(String.valueOf(initialMap.charAt(i)));

    }

    public int getWoodProduction() {
        int woodproduction = 0;
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++) {
                if (BuildingCode.WOODCUTTER.equals(getBuilding(i, j))) {
                    woodproduction += getWoodProduction(getNeighbours(i, j));
                }
                if (BuildingCode.TOWNHALL.equals(getBuilding(i, j))) {
                    woodproduction += 300;
                }

            }
        return woodproduction;
    }

    private int getWoodProduction(List<BuildingCode> neighbours) {
        double forestbonus = 0;
        double sawmillbonus = 0;
        double workforcebonus = 0;
        for (BuildingCode neighbour : neighbours) {
            if (neighbour.equals(BuildingCode.COTTAGE)) {
                workforcebonus += 0.5;
            }
            if (neighbour.equals(BuildingCode.SAWMILL)) {
                sawmillbonus = 0.75;
            }
            if (neighbour.equals(BuildingCode.FOREST)) {
                forestbonus += 0.25;
            }
        }
        return (int) (300 * (1 + forestbonus + workforcebonus) * (1 + sawmillbonus));

    }

    public List<BuildingCode> getNeighbours(int i, int j) {
        ArrayList<BuildingCode> neighbours = new ArrayList<BuildingCode>();
        try {
            neighbours.add(getBuilding(i + 1, j));
        } catch (Exception e) {
        }
        try {
            neighbours.add(getBuilding(i - 1, j));
        } catch (Exception e) {
        }
        try {
            neighbours.add(getBuilding(i + 1, j + 1));
        } catch (Exception e) {
        }
        try {
            neighbours.add(getBuilding(i - 1, j + 1));
        } catch (Exception e) {
        }
        try {
            neighbours.add(getBuilding(i, j + 1));
        } catch (Exception e) {
        }
        try {
            neighbours.add(getBuilding(i, j - 1));
        } catch (Exception e) {
        }
        try {
            neighbours.add(getBuilding(i + 1, j - 1));
        } catch (Exception e) {
        }
        try {
            neighbours.add(getBuilding(i - 1, j - 1));
        } catch (Exception e) {
        }
        return neighbours;
    }

    public BuildingCode getBuilding(int i, int j) {
        if (i < 0 || j < 0 || i > dimension - 1 || j > dimension - 1)
            throw new IllegalArgumentException("Index out of bounds i:" + i + " j:" + j);
        return buildings[(j * dimension) + i];
    }

    public void setBuilding(int i, int j, BuildingCode value) {
        if (i < 0 || j < 0 || i > dimension - 1 || j > dimension - 1)
            throw new IllegalArgumentException("Index out of bounds i:" + i + " j:" + j);
        buildings[(j * dimension) + i] = value;
    }

    private MapPart getTopLeftCorner() {
        return new MapPart(getCorner(0, 0), MapCorner.TOPLEFT);
    }

    private MapPart getTopRightCorner() {

        return new MapPart(getCorner(10, 0), MapCorner.TOPRIGHT);
    }

    private MapPart getBottomLeftCorner() {
        return new MapPart(getCorner(0, 10), MapCorner.BOTTOMLEFT);
    }

    private MapPart getBottomRightCorner() {
        return new MapPart(getCorner(10, 10), MapCorner.BOTTOMRIGHT);
    }


    private String getCorner(int istart, int jstart) {
        String result = "";
        for (int j = jstart; j < jstart + 11; j++)
            for (int i = istart; i < istart + 11; i++) {
                if (j == 10 && i == 10) result += "T";
                else if (isCenter(i, j)) result += "#";
                else result += getBuilding(i, j).value();
            }
        return result;
    }

    private boolean isCenter(int i, int j) {
        if (j < 7 && j > 15) return false;
        if (i < 6 && i > 16) return false;
        //inside
        if ((j == 6 || j == 14) && i > 7 && i < 13) return true;
        if ((j == 7 || j == 13) && i > 6 && i < 14) return true;
        if ((j > 7 && j < 13) && i > 5 && i < 15) return true;
        // end inside
        return false;
    }


    private MapPart getCenter() {
        String result = "";
        for (int j = 5; j < 5 + 11; j++)
            for (int i = 5; i < 5 + 11; i++) {
                if (!isCenter(i, j)) result += "#";
                else result += getBuilding(i, j).value();
            }
        return new MapPart(result, MapCorner.CENTER);
    }

    public boolean isComplete() {
        return dimension == 21 ? true : false;
    }

    private void setCenterMap(String centerMap) {
        for (int z = 0; z < centerMap.length(); z++) {
            int j = z / 11;
            int i = z - (j * 11);
            if (isCenter(i + 5, j + 5)) {
                setBuilding(i + 5, j + 5, BuildingCode.fromValue(String.valueOf(centerMap.charAt(z))));
            }
        }
    }

    public ShareString getShareString() {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < buildings.length; i++) {
            BuildingCode building = buildings[i];
            str.append(building.value());
        }
        return new ShareString(str.toString());
    }

    private void setCorner(int istart, int jstart, String values) {
        for (int z = 0; z < values.length(); z++) {
            int j = z / 11;
            int i = z - (j * 11);
            if (!isCenter(i + istart, j + jstart))
                setBuilding(i + istart, j + jstart, BuildingCode.fromValue(String.valueOf(values.charAt(z))));
        }
    }

    private void setBottomLeftCorner(String values) {
        setCorner(0, 10, values);
    }

    private void setBottomRightCorner(String values) {
        setCorner(10, 10, values);
    }

    private void setTopLeftCorner(String values) {
        setCorner(0, 0, values);
    }

    private void setTopRightCorner(String values) {
        setCorner(10, 0, values);
    }

    public void setPart(MapPart part) {
        if (dimension != 21) throw new IllegalStateException("Must be a dim 21 map");
        switch (part.getCorner()) {
            case TOPLEFT:
                setTopLeftCorner(part.getMap());
                break;
            case TOPRIGHT:
                setTopRightCorner(part.getMap());
                break;
            case BOTTOMLEFT:
                setBottomLeftCorner(part.getMap());
                break;
            case BOTTOMRIGHT:
                setBottomRightCorner(part.getMap());
                break;
            case CENTER:
                setCenterMap(part.getMap());
                break;
        }
    }

    public MapPart getPart(MapCorner corner) {
        if (dimension != 21) throw new IllegalStateException("Must be a dim 21 map");
        switch (corner) {
            case TOPLEFT:
                return getTopLeftCorner();
            case TOPRIGHT:
                return getTopRightCorner();
            case BOTTOMLEFT:
                return getBottomLeftCorner();
            case BOTTOMRIGHT:
                return getBottomRightCorner();
            case CENTER:
                return getCenter();
            default:
                return null;
        }
    }
}
