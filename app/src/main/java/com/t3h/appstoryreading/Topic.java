package com.t3h.appstoryreading;

public class Topic {
    private int id;
    private String name;
    private int idIM;

    public int getIdIM() {
        return idIM;
    }

    public void setIdIM(int idIM) {
        this.idIM = idIM;
    }

    public Topic(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
