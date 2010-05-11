package idar.loup.buildings;

public class BuildingFactory {

    public static Building create(String str){
        if("#".equals(str)){
            return new InvalidBuilding();
        }
        if("-".equals(str)){
            return new Grass();
        }
        if(".".equals(str)){
            return new Forest();
        }
        if(",".equals(str)){
            return new Iron();
        }if(":".equals(str)){
            return new Stone();
        }if("W".equals(str)){
            return new Woodcutter();
        }if("C".equals(str)){
            return new Cottage();
        }if("L".equals(str)){
            return new Sawmill();
        }if(";".equals(str)){
            return new Lake();
        }if("I".equals(str)){
            return new IronMine();
        }if("D".equals(str)){
            return new Foundry();
        }if("T".equals(str)){
            return new TownHall();
        }if("Q".equals(str)){
            return new Quarry();
        }if("A".equals(str)){
            return new Stonemason();
        }
        return null;
    }
}
