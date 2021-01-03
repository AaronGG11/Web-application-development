package com.aagg.wad.service;

import com.aagg.wad.model.State;
import com.aagg.wad.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;

    public List<State> findAllStates(){
        return stateRepository.findAll();
    }

    /*
    @PersistenceContext
    private EntityManager manager;


    public List findAllTownsByStateId(Integer state_id){
        return stateRepository.findTownsByStateId(state_id);
    }

    public List<Town> findAllTownsByStateId(Integer id){
        return manager.createNamedQuery("getAllTownsByStateId", Town.class)
                .setParameter(1, id)
                .getResultList();
    }
    */

    public List<Object[]> getTownsByStateId(Integer id)
    {
        return stateRepository.getTownsByStateId(id);
    }
}
