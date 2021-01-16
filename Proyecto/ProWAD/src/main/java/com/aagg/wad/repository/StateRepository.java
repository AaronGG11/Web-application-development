package com.aagg.wad.repository;

import com.aagg.wad.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

    @Query(value = "SELECT t.* FROM town t, state s, state_town st " +
            "WHERE  s.state_id = st.state_state_id " +
            "AND st.town_town_id = t.town_id " +
            "AND s.state_id=:id", nativeQuery = true)
    List<Object[]> getTownsByStateId(@Param("id") Integer id);


}
