package idar.loup.game;

import org.jgap.RandomGenerator;

public enum BuildingCode {

    UNAVAILABLE("#"),

    GRASS("-"),

    COTTAGE("C"),
    TOWNHALL("T"),

    WOODCUTTER("W"),

    SAWMILL("L"),

    FOREST(".");


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
        while(BuildingCode.TOWNHALL.equals(values[n])){
            n = randomGenerator.nextInt(values.length);
        }
        return values[n];
    }

    public boolean isUntouchable(){
        if(BuildingCode.UNAVAILABLE.equals(this) || BuildingCode.TOWNHALL.equals(this) ) return true;
        return false;
    }

    public boolean isResource() {
        if(BuildingCode.FOREST.equals(this)) return true;
        return false;
    }
}