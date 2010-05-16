package org.louCityCreator;

public class SharestringParser {
    private int dim;
    private String map;

    public SharestringParser(String sharestring){
        map = removeSharestringSyntax(sharestring);
        dim = (int) Math.sqrt(map.length());

        if(dim*dim != map.length()){
            throw new IllegalArgumentException("Sharestring has wrong length " + map.length() + " length should be " + dim*dim);
        }
    }

    private String removeSharestringSyntax(String sharestring) {
        return sharestring.replaceFirst("\\[ShareString.1.2\\]:","");
    }

    public int getDimension() {
        return dim;
    }

    public String getMap() {
        return map;
    }
}
