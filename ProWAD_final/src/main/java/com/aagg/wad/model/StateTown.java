package com.aagg.wad.model;

import javax.persistence.*;

@Entity(name = "StateTown")
@Table(name = "state_town")
public class StateTown {
    @EmbeddedId
    private StateTownId id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("stateId")
    private State state;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("townId")
    private Town town;
}
