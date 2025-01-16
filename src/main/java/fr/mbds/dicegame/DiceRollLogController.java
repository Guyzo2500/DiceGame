package fr.mbds.dicegame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api") // Point de départ pour tous les endpoints de ce contrôleur.
public class DiceRollLogController {

    @Autowired
    private DiceService service;

    @Autowired
    private DiceRollLogMapper mapper;

    // GET /api/DiceRollLogs - Récupérer tous les logs
    @GetMapping("/DiceRollLogs")
    public ResponseEntity<List<DiceRollLogDTO>> getAllLogs() {
        List<DiceRollLogDTO> logs = service.getAllLogs();
        return ResponseEntity.ok(logs);
    }

    // POST /api/DiceRollLogs - Créer un nouveau log
    @PostMapping("/DiceRollLogs")
    public ResponseEntity<DiceRollLogDTO> createLog(@RequestBody DiceRollLogDTO dto) {
        DiceRollLogDTO savedLog = service.saveDiceRoll(dto);
        return new ResponseEntity<>(savedLog, HttpStatus.CREATED);
    }

    // GET /api/DiceRollLog/{id} - Récupérer un log par ID
    @GetMapping("/DiceRollLog/{id}")
    public ResponseEntity<DiceRollLogDTO> getLogById(@PathVariable Long id) {
        Optional<DiceRollLog> log = service.getLogById(id);
        return log.map(value -> ResponseEntity.ok(mapper.toDTO(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // PUT /api/DiceRollLog/{id} - Mettre à jour un log existant
    @PutMapping("/DiceRollLog/{id}")
    public ResponseEntity<DiceRollLogDTO> updateLog(@PathVariable Long id, @RequestBody DiceRollLogDTO dto) {
        Optional<DiceRollLog> existingLog = service.getLogById(id);
        if (existingLog.isPresent()) {
            DiceRollLog updatedLog = service.updateLog(id, mapper.toDiceRollLog(dto));
            return ResponseEntity.ok(mapper.toDTO(updatedLog));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // PATCH /api/DiceRollLog/{id} - Mise à jour partielle d’un log
    @PatchMapping("/DiceRollLog/{id}")
    public ResponseEntity<DiceRollLogDTO> patchLog(@PathVariable Long id, @RequestBody DiceRollLogDTO partialDto) {
        Optional<DiceRollLog> existingLog = service.getLogById(id);
        if (existingLog.isPresent()) {
            DiceRollLog patchedLog = service.patchLog(id, mapper.toDiceRollLog(partialDto));
            return ResponseEntity.ok(mapper.toDTO(patchedLog));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // DELETE /api/DiceRollLog/{id} - Supprimer un log
    @DeleteMapping("/DiceRollLog/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable Long id) {
        if (service.deleteLog(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
