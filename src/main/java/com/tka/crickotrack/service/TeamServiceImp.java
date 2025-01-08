package com.tka.crickotrack.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.crickotrack.dao.TeamDao;
import com.tka.crickotrack.enties.Team;

@Service
public class TeamServiceImp implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Override
    public boolean saveTeam(Team team) {
        return teamDao.saveTeam(team);
    }

    @Override
    public List<Team> getAllTeams() {
        return teamDao.getAllTeams();
    }

    @Override
    public Team getTeamById(Long id) {
        return teamDao.getTeamById(id);
    }

    @Override
    public boolean updateTeam(Team team) {
        return teamDao.updateTeam(team);
    }

    @Override
    public boolean deleteTeam(Long id) {
        return teamDao.deleteTeam(id);
    }

    @Override
    public List<Team> getTeamsWithMostWins() {
        List<Team> teams = teamDao.getAllTeams();
        return teams.stream()
                .filter(team -> team.getWins() > 50) // Example condition: teams with more than 50 wins
                .collect(Collectors.toList());
    }

    @Override
    public List<Team> getTeamsByMinBudget(double minBudget) {
        List<Team> teams = teamDao.getAllTeams();
        return teams.stream()
                .filter(team -> team.getBudget() >= minBudget)
                .collect(Collectors.toList());
    }

    @Override
    public List<Team> getTeamsWithMoreLossesThanWins() {
        List<Team> teams = teamDao.getAllTeams();
        return teams.stream()
                .filter(team -> team.getLosses() > team.getWins())
                .collect(Collectors.toList());
    }

    @Override
    public List<Team> getTeamsEstablishedAfterYear(int year) {
        List<Team> teams = teamDao.getAllTeams();
        return teams.stream()
                .filter(team -> team.getEstablishedYear() != null && team.getEstablishedYear() > year)
                .collect(Collectors.toList());
    }

    @Override
    public List<Team> getTeamsWithLowBudgetAndHighWins(double maxBudget, int minWins) {
        List<Team> teams = teamDao.getAllTeams();
        return teams.stream()
                .filter(team -> team.getBudget() <= maxBudget && team.getWins() >= minWins)
                .collect(Collectors.toList());
    }

    @Override
    public List<Team> getTeamsByOwner(String ownerName) {
        List<Team> teams = teamDao.getAllTeams();
        return teams.stream()
                .filter(team -> team.getOwnerName().equalsIgnoreCase(ownerName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Team> getTeamsByHomeGround(String homeGround) {
        List<Team> teams = teamDao.getAllTeams();
        return teams.stream()
                .filter(team -> team.getHomeGround().equalsIgnoreCase(homeGround))
                .collect(Collectors.toList());
    }

    @Override
    public int countTeamsWithWinsGreaterThan(int minWins) {
        List<Team> teams = teamDao.getAllTeams();
        return (int) teams.stream()
                .filter(team -> team.getWins() > minWins)
                .count();
    }

    @Override
    public List<Team> getTopNTeamsByBudget(int n) {
        List<Team> teams = teamDao.getAllTeams();
        return teams.stream()
                .sorted((t1, t2) -> Double.compare(t2.getBudget(), t1.getBudget())) // Sorting by budget in descending order
                .limit(n)
                .collect(Collectors.toList());
    }

    @Override
    public double calculateAverageBudgetOfTeams() {
        List<Team> teams = teamDao.getAllTeams();
        return teams.stream()
                .mapToDouble(Team::getBudget)
                .average()
                .orElse(0.0);
    }
}
