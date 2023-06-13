package me.notro.essentialcommands.systems;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

@Getter
@Setter
@NonNull
@AllArgsConstructor
public class ScoreboardManager {

    private Player player;
    private Scoreboard scoreboard;

    public Scoreboard createScoreboardManager(String name, String criteria, String displayName, DisplaySlot displaySlot) {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective(name, criteria, displayName);
        objective.setDisplaySlot(displaySlot);

        return scoreboard;
    }

    public void scoreboardScore(String objectiveName, String scoreName, int scoreNumber) {
        Objective objective = scoreboard.getObjective(objectiveName);
        Score score = objective.getScore(scoreName);

        score.setScore(scoreNumber);
    }
}