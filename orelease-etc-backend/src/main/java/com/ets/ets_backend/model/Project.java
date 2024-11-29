package com.ets.ets_backend.model;


import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="projects",
    uniqueConstraints = {
        @UniqueConstraint(name = "uniqueProjectNameAndCreator", columnNames = {
                "pname", "creator"
        })
    }
)
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="creator", nullable = false)
    private Client creator;

    @NotBlank
    @Column(name="pname", nullable = false)
    private String pname;

    @Column(name= "created_time", nullable = false)
    private LocalDateTime created_time;

    @Column(name="modified_time", nullable = false)
    private LocalDateTime modified_time;

    @PrePersist
    public void onCreate(){
        created_time = LocalDateTime.now();
        modified_time = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        if(modified_time.isBefore(created_time)){
            throw new IllegalArgumentException("modified time cannot be before created");
        }
        modified_time = LocalDateTime.now();
    }
}
