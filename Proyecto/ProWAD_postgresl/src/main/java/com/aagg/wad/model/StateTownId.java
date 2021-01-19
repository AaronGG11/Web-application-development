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
public class StateTownId implements Serializable {
    @Column(name = "state_id")
    private Integer stateId;

    @Column(name = "town_id")
    private Integer townId;
}
