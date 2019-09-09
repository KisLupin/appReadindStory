package com.t3h.appstoryreading;

public class ListStory {

    private String nameStory;
    private int idName;

    public int getIdName() {
        return idName;
    }

    public void setIdName(int idName) {
        this.idName = idName;
    }

    public ListStory(int idName,String nameStory){
        this.nameStory = nameStory;
        this.idName = idName;
    }


    public String getNameStory() {
        return nameStory;
    }

    public void setNameStory(String nameStory) {
        this.nameStory = nameStory;
    }
}
