package com.aagg.wad.model;

import javax.persistence.*;

@Entity(name = "UserProduct")
@Table(name = "user_product")
public class UserProduct {
    @EmbeddedId
    private UserProductId id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @MapsId("userId")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @MapsId("productId")
    private Product product;
}
