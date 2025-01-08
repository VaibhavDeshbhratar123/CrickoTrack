package com.tka.crickotrack.service;

import java.util.List;
import com.tka.crickotrack.enties.Team;

public interface TeamService {

    boolean saveTeam(Team team);

    List<Team> getAllTeams();

    Team getTeamById(Long id);

    boolean updateTeam(Team team);

    boolean deleteTeam(Long id);

    List<Team> getTeamsWithMostWins();

    List<Team> getTeamsByMinBudget(double minBudget);

    List<Team> getTeamsWithMoreLossesThanWins();

    List<Team> getTeamsEstablishedAfterYear(int year);

    List<Team> getTeamsWithLowBudgetAndHighWins(double maxBudget, int minWins);

    List<Team> getTeamsByOwner(String ownerName);

    List<Team> getTeamsByHomeGround(String homeGround);

    int countTeamsWithWinsGreaterThan(int minWins);

    List<Team> getTopNTeamsByBudget(int n);

    double calculateAverageBudgetOfTeams();
}
