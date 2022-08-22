// This class is a Service.
// Services are used to write business logic in a different layer, separated from Controller class file.
// All the methods for the endpoints are implemented here and are called by the Controller class.
package com.example.app.livingdex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service // For the dependency injection of this class
public class LivingDexService {

    private final LivingDexRepository livingDexRepository;

    @Autowired
    public LivingDexService(LivingDexRepository livingDexRepository) {
        this.livingDexRepository = livingDexRepository;
    }

    public List<LivingDex> getAllLivingDex() {
        return livingDexRepository.findAll();
    }

    public LivingDex getLivingDexById(Long livingdexId){
        boolean exists = livingDexRepository.existsById(livingdexId);
        if (!exists){
            throw  new IllegalStateException("error: This living dex doesn't exist");
        }
        return livingDexRepository.findById(livingdexId).get();
    }

    public void createNewLivingDex(LivingDex livingDex) {
        Optional<LivingDex> livingDexOptional = livingDexRepository.findById(livingDex.getId());
        if(livingDexOptional.isPresent()){
            throw new IllegalStateException("error: A living dex with this id " + livingDex.getId() + " already exists on the database");
        }
        livingDexRepository.save(livingDex);
    }

    public void deleteLivingDex(Long livingdexId) {
        boolean exists = livingDexRepository.existsById(livingdexId);
        if(!exists){
            throw new IllegalStateException("error: Unable to delete the living dex. Doesn't exists");
        }
        livingDexRepository.deleteById(livingdexId);
    }

    // With transactional annotation this is not necessary to implement jpql query
    // Because with this annotation the entity goes into a managed state
    @Transactional
    public void updateLivingDexTotalShinies(Long livingdexId, Integer nbShinies) {
        LivingDex livingDex = livingDexRepository.findById(livingdexId)
                .orElseThrow(() -> new IllegalStateException("error: Living dex with id " + livingdexId + " does not exists"));

        if(nbShinies != null && nbShinies > 0 && !Objects.equals(livingDex.getNbShinies(), nbShinies)){
            livingDex.setNbShinies(nbShinies);
        }
    }
}
