package org.louCityCreator.game;

public class ShareString {

    private String sharestring;

    public ShareString(String sharestring) {
        this.sharestring = sharestring;
    }

    public String getSharestring() {
        return sharestring;
    }

    public void setSharestring(String sharestring) {
        this.sharestring = sharestring;
    }

    public String getCompleteShareString(){
        return "[ShareString.1.2]:" + sharestring;
    }

    @Override
    public String toString(){
        return sharestring;
    }
}

