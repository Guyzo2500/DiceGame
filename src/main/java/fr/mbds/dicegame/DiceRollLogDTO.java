package fr.mbds.dicegame;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiceRollLogDTO {
    private Long id;
    private int diceCount;
    private List<Integer> results;
    private LocalDateTime timestamp;
}
