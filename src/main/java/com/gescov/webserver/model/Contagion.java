package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Contagion {

    @Id
    private ObjectId id;

    private Date startContagion; //Primary key

    private Date endContagion;

    @NotNull
    String nameInfected; //Primary key

    public Contagion(){}

    public Contagion(@JsonProperty("_id") ObjectId id,
                     @JsonProperty("nameInfected") String nameInfected) {
        this.id = id;
        this.nameInfected = nameInfected;
        this.startContagion = new Date();
        this.endContagion = null;
    }

    public Contagion(ObjectId id, String nameInfected, Date startContagion, Date endContagion) {
        this.id = id;
        this.nameInfected = nameInfected;
        this.startContagion = startContagion;
        this.endContagion = endContagion;
    }

    public Date getStartContagion() {
        return startContagion;
    }

    public Date getEndContagion() {
        return endContagion;
    }

    public String getNameInfected() { return nameInfected; }

    public ObjectId getId() { return id; }

    public void setStartContagion(Date startContagion) {
        this.startContagion = startContagion;
    }

    public void setEndContagion(@JsonProperty("startContagion") Date endContagion) {
        this.endContagion = endContagion;
    }

    public void setNameInfected(String nameInfected) { this.nameInfected = nameInfected; }

    public void setId(ObjectId id) { this.id = id; }
}
