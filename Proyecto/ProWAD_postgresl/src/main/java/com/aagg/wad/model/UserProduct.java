package com.aagg.wad.model;

import javax.persistence.*;

@Entity(name = "UserProduct")
@Table(name = "user_product")
public class UserProduct {
    @EmbeddedId
    private UserProductId id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @MapsId("productId")
    private Product product;
}
