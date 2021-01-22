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
public class PersonProductId implements Serializable {
    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "product_id")
    private Integer productId;
}
