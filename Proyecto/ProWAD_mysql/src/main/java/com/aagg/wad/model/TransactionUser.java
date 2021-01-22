package com.aagg.wad.model;

import javax.persistence.*;

@Entity(name = "TransactionUser")
@Table(name = "transaction_user")
public class TransactionUser {
    @EmbeddedId
    private TransactionUserId id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @MapsId("transactionId")
    private Transaction transaction;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @MapsId("sellerId")
    private User seller;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @MapsId("customerId")
    private User customer;
}
