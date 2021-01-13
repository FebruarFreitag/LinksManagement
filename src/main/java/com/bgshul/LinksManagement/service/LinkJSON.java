package com.bgshul.LinksManagement.service;

public class LinkJSON {

    public String link;
    public String original;
    public int rank;
    public int count;

    public LinkJSON(String link, String original, int rank, int count) {
        this.link = link;
        this.original = original;
        this.rank = rank;
        this.count = count;
    }

    public LinkJSON() {
    }

    @Override
    public String toString(){
        return "{\n" +
                "   \"link\": \"" + link + "\",\n" +
                "   \"original\": \"" + original + "\",\n" +
                "   \"rank\": " + rank + ",\n" +
                "   \"count\": " + count +
                "}\n";
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getOriginal() {
        return original;
    }

    public String getLink() {
        return link;
    }

    public int getRank() {
        return rank;
    }

    public int getCount() {
        return count;
    }
}
