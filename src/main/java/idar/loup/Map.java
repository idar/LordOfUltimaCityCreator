package idar.loup;

import idar.loup.buildings.Building;
import idar.loup.buildings.BuildingFactory;

public class Map {

    public Building[][] array = new Building[21][21];

    public Map(String sharestring){
        System.out.println(sharestring.length());
        if(sharestring.length() != 21*21) {
            throw new RuntimeException("Sharestring must be "+21*21+" characters long");
        }
        int pos = 0;
        for(int i = 0; i< 21; i++){
            for(int j = 0; j<21;j++){
                array[i][j] = BuildingFactory.create(""+sharestring.charAt(pos++));
            }
        }
    }

    public String getShareString(){
         String str = "";
        for(int i = 0; i<21;i++){
            for(int j = 0; j<21;j++){
                str += array[i][j];
            }
        }
        return str;
    }

    public String toString(){
        String str = "";
        for(int i = 0; i<21;i++){
            for(int j = 0; j<21;j++){
                str += array[i][j];
            }
            str+="\n";
        }
        return str;
    }
}
