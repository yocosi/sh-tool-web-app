package com.example.app.shinyquest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ShinyQuestService {

    private final ShinyQuestRepository shinyQuestRepository;

    @Autowired
    public ShinyQuestService(ShinyQuestRepository shinyQuestRepository) {
        this.shinyQuestRepository = shinyQuestRepository;
    }

    public List<ShinyQuest> getAllShinyQuests() {
        return shinyQuestRepository.findAll();
    }


    public ShinyQuest getShinyQuestById(Long shinyquestId) {
        boolean exists = shinyQuestRepository.existsById(shinyquestId);
        if(!exists){
            throw new IllegalStateException("error: Unable to get the shiny quest with id " + shinyquestId + ". Doesn't exists in the database.");
        }
        return shinyQuestRepository.findById(shinyquestId).get();
    }

    public void addNewShinyQuest(ShinyQuest shinyQuest) {
        Optional<ShinyQuest> shinyQuestOptional = shinyQuestRepository.findById(shinyQuest.getId());
        if (shinyQuestOptional.isPresent()){
            throw new IllegalStateException("error: This shiny quest already exists in the database");
        }
        shinyQuestRepository.save(shinyQuest);
    }

    public void deleteShinyQuest(Long shinyquestId) {
        boolean exists = shinyQuestRepository.existsById(shinyquestId);
        if(!exists){
            throw new IllegalStateException("error: Unable to delete. Shiny quest with id " + shinyquestId + " doesn't exists in the database.");
        }
        shinyQuestRepository.deleteById(shinyquestId);
    }

    // With transactional annotation this is not necessary to implement jpql query
    // Because with this annotation the entity goes into a managed state
    @Transactional
    public void updateShinyQuest(Long shinyquestId, String type, String name, LocalDate startDate, LocalDate endDate, String game) {
        ShinyQuest shinyQuest = shinyQuestRepository.findById(shinyquestId)
                .orElseThrow(() -> new IllegalStateException("error: Shiny quest with id " + shinyquestId + " doesn't exists"));

        if(type != null && type.length() > 0 && !Objects.equals(shinyQuest.getType(), type)){
            shinyQuest.setType(type);
        }

        if(name != null && name.length() > 0 && !Objects.equals(shinyQuest.getName(), name)){
            shinyQuest.setName(name);
        }

        if(game != null && game.length() > 0 && !Objects.equals(shinyQuest.getGame(), game)){
            shinyQuest.setGame(game);
        }

        if (startDate != null &&  startDate.compareTo(LocalDate.now().plusDays(1)) < 0 && !Objects.equals(shinyQuest.getStartDate(), startDate)){
            shinyQuest.setStartDate(startDate);
        }

        if (endDate != null && !(endDate.compareTo(shinyQuest.getStartDate()) < 0) && !Objects.equals(shinyQuest.getEndDate(), endDate)){
            shinyQuest.setEndDate(endDate);
        }
    }
}
