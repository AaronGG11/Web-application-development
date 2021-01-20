package com.aagg.wad.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionPersonId implements Serializable {
    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "seller_id")
    private Integer sellerId;

    @Column(name = "customer_id")
    private Integer customerId;
}
