package com.aagg.wad.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "town")

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getAllTownsByStateId",
                query = "SELECT t.* FROM town t, state s, state_town st " +
                        "WHERE  s.state_id = st.state_state_id " +
                        "AND st.town_town_id = t.town_id " +
                        "AND s.state_id=?",
                resultClass=Town.class
        )
})

public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "town_id")
    private Integer id;

    @Column(name = "name")
    @NotEmpty
    private String name;
}
