package com.aagg.wad.service;

import com.aagg.wad.model.State;
import com.aagg.wad.model.Town;
import com.aagg.wad.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class StateService {
    private StateRepository stateRepository;

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    public StateService(StateRepository stateRepository){
        this.stateRepository = stateRepository;
    }

    public List<State> findAllStates(){
        return stateRepository.findAll();
    }

    /*
    public List findAllTownsByStateId(Integer state_id){
        return stateRepository.findTownsByStateId(state_id);
    }
    */

    public List<Town> findAllTownsByStateId(Integer id){
        return manager.createNamedQuery("getAllTownsByStateId", Town.class)
                .setParameter(1, id)
                .getResultList();
    }

    public List<Object[]> getTownsByState(Integer id){
        return stateRepository.getTownsByState(id);
    }
}
