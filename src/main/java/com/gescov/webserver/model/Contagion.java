package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;

public class Contagion {

    @Id
    private ObjectId id;

    private LocalDate startContagion; //Primary key

    private LocalDate endContagion;

    @DBRef
    User nameInfected;

    public Contagion() {}

    public Contagion(@JsonProperty("_id") ObjectId id,
                     @JsonProperty("nameInfected") final User nameInfected) {
        this.id = id;
        this.nameInfected = nameInfected;
        this.startContagion = LocalDate.now();
        this.endContagion = null;
    }

    public Contagion(ObjectId id, User nameInfected, LocalDate startContagion, LocalDate endContagion) {
        this.id = id;
        this.nameInfected = nameInfected;
        this.startContagion = startContagion;
        this.endContagion = endContagion;
    }

    public LocalDate getStartContagion() {
        return startContagion;
    }

    public LocalDate getEndContagion() {
        return endContagion;
    }

    public User getNameInfected() { return nameInfected; }

    public ObjectId getId() { return id; }

    public void setStartContagion(LocalDate startContagion) {
        this.startContagion = startContagion;
    }

    public void setEndContagion(@JsonProperty("endContagion") LocalDate endContagion) {
        this.endContagion = endContagion;
    }

    public void setNameInfected(User nameInfected) { this.nameInfected = nameInfected; }

    public void setId(ObjectId id) { this.id = id; }
}
