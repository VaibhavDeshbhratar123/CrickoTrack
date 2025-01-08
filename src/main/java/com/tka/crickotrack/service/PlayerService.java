package com.tka.crickotrack.service;

import java.util.List;
import com.tka.crickotrack.enties.Player;

public interface PlayerService {

    boolean savePlayer(Player player);

    List<Player> getAllPlayers();

    Player getPlayerById(Long id);

    boolean updatePlayer(Player player);

    boolean deletePlayer(Long id);

    List<Player> getPlayersWithMostRuns();

    List<Player> getPlayersByRole(String role);

    List<Player> getTopPerformersByWickets(int minWickets);

    List<Player> getPlayersByMatchesPlayed(int minMatchesPlayed);

    List<Player> getPlayersByNationality(String nationality);

    List<Player> getPlayersByBattingStyle(String battingStyle);

    List<Player> getPlayersAboveAge(int age);

    int countPlayersWithMinimumMatchesPlayed(int minMatches);

    List<Player> getTopNPlayersByRuns(int n);

    List<Player> getPlayersByTeam(Long teamId);
}
