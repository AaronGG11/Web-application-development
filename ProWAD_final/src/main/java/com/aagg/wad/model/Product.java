package com.aagg.wad.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", columnDefinition = "serial")
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "*Please provide the product name")
    private String name;

    @Column(name = "description", length=2048)
    @NotEmpty(message = "*Please provide the product description")
    private String description;

    @Column(name = "availability")
    private Boolean availability;

    @Column(name = "cost")
    @NotNull(message = "*Please provide the product cost")
    @DecimalMax("0.0")
    private Float cost;

    @Column(name = "stock")
    @NotNull(message = "*Please provide the product stock")
    @Min(0)
    private Integer stock;

    // one product could belong to only one seller, and one product could belong to some coustumers
    //Producto like book
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Transaction> transactions;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_address", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = {@JoinColumn(name = "state_state_id"), @JoinColumn(name = "town_town_id")})
    private Set<StateTown> stateTowns;
}
