package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

//@Document(collection = "classrooms")
public class Classroom {
    @Id
    private ObjectId id;

    @NotNull
    private String name;

    @NotNull
    private int capacity;

    @NotNull
    private String creator;

    public Classroom() {

    }

    public Classroom(@JsonProperty("id") ObjectId id,
                     @JsonProperty("name") String name,
                     @JsonProperty("capacity") int capacity,
                     @JsonProperty("creator") String creator) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.creator = creator;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCreator(String creator) { this.creator = creator; }

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getCreator() { return creator; }

}
