package idar.loup;

import org.jgap.RandomGenerator;

public enum BuildingCode {
    
    UNAVAILABLE("#"),
    TOWNHALL("T"),
    GRASS("-"),
    WATER("_"),
    BARRACKS("B"),
    //CASERN("G"),
    CITYGUARD_HOUSE("K"),
    COTTAGE("C"),
    FARM("F"),
    HARBOUR("R"),
    HIDEOUT("H"),
    WOODCUTTER("W"),
    FOUNDRY("D"),
    SAWMILL("L"),
    MAGE_TOWER("J"),
    MARKET_PLACE("P"),
    MILL("M"),
    IRONMINE("I"),
    SHIPYARD("V"),
    STABLES("E"),
    QUARRY("Q"),
    STONEMASON("A"),
    //STORAGE("S"),
    //STRONGHOLD("X"),
    //TEMPLE("Z"),
    TOWNHOUSE("U"),
    //WEAPON_FACTORY("Y"),
    FOREST("."),
    IRON(","),
    LAKE(";"),
    STONE(":");

    private final String value;

    BuildingCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }


    public static BuildingCode fromValue(String v) {
        for (BuildingCode c : BuildingCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }


    public static BuildingCode get(RandomGenerator randomGenerator) {

        BuildingCode[] values = BuildingCode.values();
        int n = randomGenerator.nextInt(values.length);
        return values[n];
    }

    public boolean isUntouchable(){
        if(BuildingCode.UNAVAILABLE.equals(this) || BuildingCode.TOWNHALL.equals(this) || BuildingCode.WATER.equals(this)) return true;
        return false;
    }

    public boolean isResource() {
        if(BuildingCode.STONE.equals(this) ||BuildingCode.FOREST.equals(this) || BuildingCode.IRON.equals(this) || BuildingCode.LAKE.equals(this) ) return true;
        return false;
    }
}
