package com.tka.crickotrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tka.crickotrack.enties.Team;
import com.tka.crickotrack.service.TeamService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/teams")
@Validated
public class TeamController {

    @Autowired
    private TeamService teamService;

    // Create a new team
    @PostMapping
    public ResponseEntity<Team> saveTeam(@Valid @RequestBody Team team) {
        boolean isSaved = teamService.saveTeam(team);
        if (isSaved) {
            return new ResponseEntity<>(team, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all teams
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    // Get team by ID
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        Team team = teamService.getTeamById(id);
        if (team != null) {
            return new ResponseEntity<>(team, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update team details
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @Valid @RequestBody Team team) {
        team.setId(id);
        boolean isUpdated = teamService.updateTeam(team);
        if (isUpdated) {
            return new ResponseEntity<>(team, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete team by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        boolean isDeleted = teamService.deleteTeam(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get teams with most wins
    @GetMapping("/wins")
    public ResponseEntity<List<Team>> getTeamsWithMostWins() {
        List<Team> teams = teamService.getTeamsWithMostWins();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    // Get teams by minimum budget
    @GetMapping("/budget/{minBudget}")
    public ResponseEntity<List<Team>> getTeamsByMinBudget(@PathVariable double minBudget) {
        List<Team> teams = teamService.getTeamsByMinBudget(minBudget);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    // Get teams with more losses than wins
    @GetMapping("/losses")
    public ResponseEntity<List<Team>> getTeamsWithMoreLossesThanWins() {
        List<Team> teams = teamService.getTeamsWithMoreLossesThanWins();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    // Get teams established after a specific year
    @GetMapping("/established/{year}")
    public ResponseEntity<List<Team>> getTeamsEstablishedAfterYear(@PathVariable int year) {
        List<Team> teams = teamService.getTeamsEstablishedAfterYear(year);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    // Get teams with low budget and high wins
    @GetMapping("/low-budget-high-wins")
    public ResponseEntity<List<Team>> getTeamsWithLowBudgetAndHighWins(
            @RequestParam double maxBudget, @RequestParam int minWins) {
        List<Team> teams = teamService.getTeamsWithLowBudgetAndHighWins(maxBudget, minWins);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    // Get teams by owner
    @GetMapping("/owner/{ownerName}")
    public ResponseEntity<List<Team>> getTeamsByOwner(@PathVariable String ownerName) {
        List<Team> teams = teamService.getTeamsByOwner(ownerName);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    // Get teams by home ground
    @GetMapping("/home-ground/{homeGround}")
    public ResponseEntity<List<Team>> getTeamsByHomeGround(@PathVariable String homeGround) {
        List<Team> teams = teamService.getTeamsByHomeGround(homeGround);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    // Count teams with wins greater than a specified number
    @GetMapping("/count/wins/{minWins}")
    public ResponseEntity<Integer> countTeamsWithWinsGreaterThan(@PathVariable int minWins) {
        int count = teamService.countTeamsWithWinsGreaterThan(minWins);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    // Get top N teams by budget
    @GetMapping("/top-budget/{n}")
    public ResponseEntity<List<Team>> getTopNTeamsByBudget(@PathVariable int n) {
        List<Team> teams = teamService.getTopNTeamsByBudget(n);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    // Get average budget of all teams
    @GetMapping("/average-budget")
    public ResponseEntity<Double> calculateAverageBudgetOfTeams() {
        double averageBudget = teamService.calculateAverageBudgetOfTeams();
        return new ResponseEntity<>(averageBudget, HttpStatus.OK);
    }
}
