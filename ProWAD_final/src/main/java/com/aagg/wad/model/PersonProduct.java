package com.aagg.wad.model;

import javax.persistence.*;

@Entity(name = "PersonProduct")
@Table(name = "person_product")
public class PersonProduct {
    @EmbeddedId
    private PersonProductId id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("personId")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("productId")
    private Product product;
}
