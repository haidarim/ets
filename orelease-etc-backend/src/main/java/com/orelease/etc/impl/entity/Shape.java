package com.orelease.etc.impl.entity;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Data;

import java.io.IOException;
import java.io.Serializable;

@Data
@Entity
@Table(name="shapes", uniqueConstraints = {
        @UniqueConstraint(name = "uniqueShapeNameInProject", columnNames = {
                "shname", "projectName"
        })
})
public class Shape implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shId")
    private Long id;

    @Column(name = "shname", nullable = false)
    private String shname;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "point", nullable = false, columnDefinition = "JSONB")
    private String points;


    @ManyToOne
    @JoinColumn(name= "creator", nullable = false)
    private Client creator;

    @ManyToOne
    @JoinColumn(name = "projectName", nullable = false)
    private Project projectName;

    //@OneToMany(mappedBy = "lfrom") // bidirectional, many-to-many, from many shapes
    //private Set<Shape> outgoingLinks; // outgoing from this Shape

    //@OneToMany(mappedBy = "lto") // to many shapes
    //private Set<Shape> incomingLinks; // incoming to this Shape

    //............................... Helper to handle JsonData.................................
    @Transient // specifies that the field below should not be stored in the db
    private JsonNode jsonPoints;

    @PostLoad // before loading from db
    // NOTE: When using @PreLoad, we can not have @PrePersist and/or PreUpdate
    public void parseJson() {
        try{
            ObjectMapper mapper = new ObjectMapper();
            this.jsonPoints = mapper.readTree(this.points);
        }catch (IOException e){
            // TODO: LOG THE EXCEPTION
        }
    }

    @PrePersist
    @PreUpdate
    public void writeJson(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            this.points = mapper.writeValueAsString(this.jsonPoints);
        }catch (IOException e){
            // TODO: LOG THE EXCEPTION
        }
    }
}
