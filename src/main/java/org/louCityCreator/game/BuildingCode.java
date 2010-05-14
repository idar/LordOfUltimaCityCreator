package org.louCityCreator.game;

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


    public static BuildingCode get(RandomGenerator randomGenerator, BuildingCode initialBuilding) {

        BuildingCode[] values = BuildingCode.values();
        int n = randomGenerator.nextInt(values.length);
        while (BuildingCode.TOWNHALL.equals(values[n]) || BuildingCode.UNAVAILABLE.equals(values[n]) || isResourceButNotInitalBuilding(values[n], initialBuilding)) {
            n = randomGenerator.nextInt(values.length);
        }
        return values[n];
    }

    private static boolean isResourceButNotInitalBuilding(BuildingCode value, BuildingCode initial) {
        if (value.isResource() && !value.equals(initial)) {
            return true;
        }
        return false;
    }

    public boolean isUntouchable() {
        if (BuildingCode.UNAVAILABLE.equals(this) || BuildingCode.TOWNHALL.equals(this)) return true;
        return false;
    }

    public boolean isResource() {
        if (BuildingCode.FOREST.equals(this)) return true;
        return false;
    }

    public boolean isBuilding() {
        if (BuildingCode.COTTAGE.equals(this) || BuildingCode.TOWNHALL.equals(this) || BuildingCode.SAWMILL.equals(this) || BuildingCode.WOODCUTTER.equals(this)) {
            return true;
        }
        return false;
    }
}