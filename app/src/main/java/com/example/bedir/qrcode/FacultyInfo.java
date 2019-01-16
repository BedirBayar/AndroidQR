package com.example.bedir.qrcode;

import java.io.Serializable;

public class FacultyInfo implements Serializable{
    private String Id;
    private String Name;
    private String Info;
    private String Since;

    public FacultyInfo(String ID, String name, String info, String since) {
        Id = ID;
        Name = name;
        Info = info;
        Since = since;
    }

    public FacultyInfo() {

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public String getSince() {
        return Since;
    }

    public void setSince(String since) {
        Since = since;
    }
}
