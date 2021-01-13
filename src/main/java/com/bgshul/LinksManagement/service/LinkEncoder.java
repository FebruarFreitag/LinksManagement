package com.bgshul.LinksManagement.service;

import org.hashids.Hashids;

public class LinkEncoder {

    public String encodeLink(){
        Hashids hashids = new Hashids("LinksManagement", 8);
        LinkDB linkDB = new LinkDB();
        int ID = linkDB.getCurrentID();
        if (ID!=-1) {
            if (ID==0){
                ID=1;
            }
            return hashids.encode(ID);
        }
        else {
            return "Error";
        }
    }
}
