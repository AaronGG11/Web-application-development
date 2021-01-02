package com.aagg.wad.repository;

import com.aagg.wad.model.State;
import com.aagg.wad.model.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.NamedNativeQuery;
import java.util.List;

public interface StateRepository extends JpaRepository<State, Integer> {

    @Query(value = "SELECT t.* FROM town t, state s, state_town st " +
            "WHERE  s.state_id = st.state_state_id " +
            "AND st.town_town_id = t.town_id " +
            "AND s.state_id=?1", nativeQuery = true)
    List findTownsByStateId(Integer state_id);

    @Query(value = "SELECT t.* FROM town t, state s, state_town st " +
            "WHERE  s.state_id = st.state_state_id " +
            "AND st.town_town_id = t.town_id " +
            "AND s.state_id=?1", nativeQuery = true)
    List<Object[]> getTownsByState(Integer id);
}
