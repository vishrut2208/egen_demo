package com.vishrut.demo.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Alert {


    @Id
    private String id;

    private String readId;

    private String type;

    private String description;

    public Alert(){
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReadId() {
        return readId;
    }

    public void setReadId(String readId) {
        this.readId = readId;
    }
}

