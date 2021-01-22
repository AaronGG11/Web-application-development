package com.aagg.wad.model;

import javax.persistence.*;

@Entity(name = "TransactionPerson")
@Table(name = "transaction_person")
public class TransactionPerson {
    @EmbeddedId
    private TransactionPersonId id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("transactionId")
    private Transaction transaction;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("sellerId")
    private Person seller;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("customerId")
    private Person customer;
}
