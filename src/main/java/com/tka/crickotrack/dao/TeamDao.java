package com.tka.crickotrack.dao;

import java.util.List;
import com.tka.crickotrack.enties.Team;

public interface TeamDao {

    boolean saveTeam(Team team);

    List<Team> getAllTeams();

    Team getTeamById(Long id);

    boolean updateTeam(Team team);

    boolean deleteTeam(Long id);
}
