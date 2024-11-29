package com.ets.ets_backend.model;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="links")
public class Link implements Serializable {
    @EmbeddedId
    private LinksId id;

    @ManyToOne
    @MapsId("from")
    @JoinColumn(name = "lfrom") // refers to LinksId.lfrom
    private Shape from;


    @ManyToOne
    @MapsId("to")
    @JoinColumn(name = "lto") // refers to LinksId.lto
    private Shape to;
}
