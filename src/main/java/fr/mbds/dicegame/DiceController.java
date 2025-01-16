package fr.mbds.dicegame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indique que cette classe est un contrôleur REST.
@RequestMapping("/api") // Définition du chemin de base pour les endpoints.
public class DiceController {
    @Autowired
    private DiceService service;

    // Endpoint pour lancer un seul dé.
    @GetMapping("/rollDice")
    public DiceRollLogDTO rollSingleDice() {
        return service.rollDices(1);
    }

    // Endpoint pour lancer plusieurs dés.
    @GetMapping("/rollDices/{count}")
    public DiceRollLogDTO rollMultipleDices(@PathVariable int count) {
        return service.rollDices(count);
    }

    // Endpoint pour récupérer tous les historiques.
    @GetMapping("/diceLogs")
    public List<DiceRollLogDTO> getAllLogs() {
        return service.getAllLogs();
    }

    // Endpoint pour enregistrer un historique à partir d'un DTO.
    @PostMapping("/save")
    public DiceRollLogDTO saveDiceRoll(@RequestBody DiceRollLogDTO dto) {
        return service.saveDiceRoll(dto);
    }
}
