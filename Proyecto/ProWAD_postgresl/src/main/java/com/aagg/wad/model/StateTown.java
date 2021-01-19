package com.aagg.wad.model;

import javax.persistence.*;

@Entity(name = "StateTown")
@Table(name = "state_town")
public class StateTown {
    @EmbeddedId
    private StateTownId id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @MapsId("stateId")
    private State state;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @MapsId("townId")
    private Town town;
}
