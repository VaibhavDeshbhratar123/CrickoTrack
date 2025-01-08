package com.tka.crickotrack.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.crickotrack.dao.PlayerDao;
import com.tka.crickotrack.enties.Player;

@Service
public class PlayerServiceImp implements PlayerService {

    @Autowired
    private PlayerDao playerDao;

    @Override
    public boolean savePlayer(Player player) {
        return playerDao.savePlayer(player);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerDao.getAllPlayers();
    }

    @Override
    public Player getPlayerById(Long id) {
        return playerDao.getPlayerById(id);
    }

    @Override
    public boolean updatePlayer(Player player) {
        return playerDao.updatePlayer(player);
    }

    @Override
    public boolean deletePlayer(Long id) {
        return playerDao.deletePlayer(id);
    }

    @Override
    public List<Player> getPlayersWithMostRuns() {
        List<Player> players = playerDao.getAllPlayers();
        return players.stream()
                .filter(player -> player.getTotalRuns() > 1000)  // Example condition: players with more than 1000 runs
                .collect(Collectors.toList());
    }

    @Override
    public List<Player> getPlayersByRole(String role) {
        List<Player> players = playerDao.getAllPlayers();
        return players.stream()
                .filter(player -> player.getRole().equalsIgnoreCase(role))
                .collect(Collectors.toList());
    }

    @Override
    public List<Player> getTopPerformersByWickets(int minWickets) {
        List<Player> players = playerDao.getAllPlayers();
        return players.stream()
                .filter(player -> player.getWicketsTaken() >= minWickets)
                .collect(Collectors.toList());
    }

    @Override
    public List<Player> getPlayersByMatchesPlayed(int minMatchesPlayed) {
        List<Player> players = playerDao.getAllPlayers();
        return players.stream()
                .filter(player -> player.getMatchesPlayed() >= minMatchesPlayed)
                .collect(Collectors.toList());
    }

    @Override
    public List<Player> getPlayersByNationality(String nationality) {
        List<Player> players = playerDao.getAllPlayers();
        return players.stream()
                .filter(player -> player.getNationality().equalsIgnoreCase(nationality))
                .collect(Collectors.toList());
    }

    @Override
    public List<Player> getPlayersByBattingStyle(String battingStyle) {
        List<Player> players = playerDao.getAllPlayers();
        return players.stream()
                .filter(player -> player.getBattingStyle().equalsIgnoreCase(battingStyle))
                .collect(Collectors.toList());
    }

    @Override
    public List<Player> getPlayersAboveAge(int age) {
        List<Player> players = playerDao.getAllPlayers();
        return players.stream()
                .filter(player -> player.getAge() > age)
                .collect(Collectors.toList());
    }

    @Override
    public int countPlayersWithMinimumMatchesPlayed(int minMatches) {
        List<Player> players = playerDao.getAllPlayers();
        return (int) players.stream()
                .filter(player -> player.getMatchesPlayed() >= minMatches)
                .count();
    }

    @Override
    public List<Player> getTopNPlayersByRuns(int n) {
        List<Player> players = playerDao.getAllPlayers();
        return players.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getTotalRuns(), p1.getTotalRuns())) // Sorting by runs in descending order
                .limit(n)
                .collect(Collectors.toList());
    }

    @Override
    public List<Player> getPlayersByTeam(Long teamId) {
        List<Player> players = playerDao.getAllPlayers();
        return players.stream()
                .filter(player -> player.getTeam().getId().equals(teamId))
                .collect(Collectors.toList());
    }
}
