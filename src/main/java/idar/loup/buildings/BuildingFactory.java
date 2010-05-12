package idar.loup.buildings;

public class BuildingFactory {

    public static Building create(String str) {
        if ("#".equals(str)) {
            return new Unavailable();
        }
        if ("-".equals(str)) {
            return new Grass();
        }
        if (".".equals(str)) {
            return new Forest();
        }
        if (",".equals(str)) {
            return new Iron();
        }
        if (":".equals(str)) {
            return new Stone();
        }
        if ("W".equals(str)) {
            return new Woodcutter();
        }
        if ("C".equals(str)) {
            return new Cottage();
        }
        if ("L".equals(str)) {
            return new Sawmill();
        }
        if (";".equals(str)) {
            return new Lake();
        }
        if ("I".equals(str)) {
            return new IronMine();
        }
        if ("D".equals(str)) {
            return new Foundry();
        }
        if ("T".equals(str)) {
            return new TownHall();
        }
        if ("Q".equals(str)) {
            return new Quarry();
        }
        if ("A".equals(str)) {
            return new Stonemason();
        }
        if ("_".equals(str)) return new Water();
        if ("B".equals(str)) return new Barracks();
        if ("K".equals(str)) return new CityguardHouse();
        if ("F".equals(str)) return new Farm();
        if ("R".equals(str)) return new Harbour();
        if ("H".equals(str)) return new Hideout();
        if ("J".equals(str)) return new MageTower();
        if ("P".equals(str)) return new MarketPlace();
        if ("M".equals(str)) return new Mill();
        if ("V".equals(str)) return new Shipyard();
        if ("E".equals(str)) return new Stables();
        if ("U".equals(str)) return new TownHouse();
        return null;
    }
}
