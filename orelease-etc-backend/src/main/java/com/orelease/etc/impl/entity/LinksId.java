package com.orelease.etc.impl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * This class is a component that will be used by the {@code Link} class
 * This class is used to define composite keys (primary keys) for the {@code Link} class by using
 * {@code @EmbeddedId} in the {@code Link} entity, then the columns/vars of this class will be as an ID for {@code Link} class.
 *
 * @author Mehdi Haidari
 * */

@Embeddable // To indicate that this class can be embedded into another entity, in our case embedded into Link Entity
@Data // using lombok, to reduce the boilerplate code e.g. getter, setter, constructor
@NoArgsConstructor // to say to lombok that I want a constructor without any args
@AllArgsConstructor // to indicate that, generate a constructor with args, so two constructors
public class LinksId implements Serializable {

    @Column(name = "lfrom", nullable = false) //
    private Long from; // note here we use Long type for having as id to be used by the Link class

    @Column(name = "lto", nullable = false)
    private Long to;

}
