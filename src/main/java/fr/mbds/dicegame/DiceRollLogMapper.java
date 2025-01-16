package fr.mbds.dicegame;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component // Indique que cette classe est un composant Spring.
public class DiceRollLogMapper {

    // Convertit une entité DiceRollLog en DTO.
    public DiceRollLogDTO toDTO(DiceRollLog log) {
        return new DiceRollLogDTO(log.getId(), log.getDiceCount(), log.getResults(), log.getTimestamp());
    }

    // Convertit un DTO en entité DiceRollLog.
    public DiceRollLog toDiceRollLog(DiceRollLogDTO dto) {
        if (dto != null) {
            return new DiceRollLog(dto.getId(), dto.getDiceCount(), dto.getResults(), dto.getTimestamp());
        }
        return null;
    }

    // Convertit une liste d'entités DiceRollLog en une liste de DTOs.
    public List<DiceRollLogDTO> toDTOList(List<DiceRollLog> logs) {
        return logs.stream()
                .map(this::toDTO) // Utilise la méthode toDTO pour chaque élément.
                .collect(Collectors.toList()); // Collecte les résultats dans une liste.
    }
}
