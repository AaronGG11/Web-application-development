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

    public List<Object[]> getTownsByStateId(Integer id)
    {
        return stateRepository.getTownsByStateId(id);
    }
}
