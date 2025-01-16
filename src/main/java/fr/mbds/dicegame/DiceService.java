package fr.mbds.dicegame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // Indique que cette classe contient la logique métier.
public class DiceService {
    @Autowired
    private Dice dice;

    @Autowired
    private DiceRollLogRepository repository;

    @Autowired
    private DiceRollLogMapper mapper;

    // Lancer plusieurs dés et enregistrer le résultat.
    public DiceRollLogDTO rollDices(int count) {
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            results.add(dice.roll());
        }

        DiceRollLog log = DiceRollLog.builder()
                .diceCount(count)
                .results(results)
                .timestamp(LocalDateTime.now())
                .build();

        DiceRollLog savedLog = repository.save(log); // Sauvegarde dans la base.
        return mapper.toDTO(savedLog); // Retourne le DTO correspondant.
    }

    // Enregistrer un historique à partir d'un DTO.
    public DiceRollLogDTO saveDiceRoll(DiceRollLogDTO dto) {
        DiceRollLog entity = mapper.toDiceRollLog(dto); // Conversion du DTO en entité.
        DiceRollLog savedEntity = repository.save(entity); // Sauvegarde dans la base.
        return mapper.toDTO(savedEntity); // Retourne le DTO sauvegardé.
    }

    // Récupérer tous les historiques de lancers.
    public List<DiceRollLogDTO> getAllLogs() {
        List<DiceRollLog> logs = repository.findAll();
        return mapper.toDTOList(logs);
    }

    // Récupérer un log par son ID.
    public Optional<DiceRollLog> getLogById(Long id) {
        return repository.findById(id); // Renvoie un Optional contenant le log s'il existe.
    }

    // Mettre à jour complètement un log existant.
    public DiceRollLog updateLog(Long id, DiceRollLog updatedLog) {
        updatedLog.setId(id); // S'assurer que l'ID est conservé.
        return repository.save(updatedLog); // Met à jour et sauvegarde dans la base.
    }

    // Mise à jour partielle d'un log.
    public DiceRollLog patchLog(Long id, DiceRollLog partialLog) {
        Optional<DiceRollLog> existingLog = repository.findById(id);
        if (existingLog.isPresent()) {
            DiceRollLog log = existingLog.get();

            // Appliquer uniquement les champs non nulls.
            if (partialLog.getDiceCount() > 0) log.setDiceCount(partialLog.getDiceCount());
            if (partialLog.getResults() != null) log.setResults(partialLog.getResults());
            if (partialLog.getTimestamp() != null) log.setTimestamp(partialLog.getTimestamp());

            return repository.save(log); // Sauvegarde les modifications.
        }
        throw new RuntimeException("Log non trouvé"); // Exception si le log n'existe pas.
    }

    // Supprimer un log par son ID.
    public boolean deleteLog(Long id) {
        Optional<DiceRollLog> log = repository.findById(id);
        if (log.isPresent()) {
            repository.deleteById(id); // Supprime le log.
            return true;
        }
        return false; // Retourne false si le log n'existe pas.
    }
}
