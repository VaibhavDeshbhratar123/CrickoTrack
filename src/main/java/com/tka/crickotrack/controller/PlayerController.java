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
import org.springframework.web.bind.annotation.RestController;

import com.tka.crickotrack.enties.Player;
import com.tka.crickotrack.service.PlayerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/players")
@Validated
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    // Create a new player
    @PostMapping
    public ResponseEntity<Player> savePlayer(@Valid @RequestBody Player player) {
        boolean isSaved = playerService.savePlayer(player);
        if (isSaved) {
            return new ResponseEntity<>(player, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all players
    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Get player by ID
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        Player player = playerService.getPlayerById(id);
        if (player != null) {
            return new ResponseEntity<>(player, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update player details
    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @Valid @RequestBody Player player) {
        player.setId(id);
        boolean isUpdated = playerService.updatePlayer(player);
        if (isUpdated) {
            return new ResponseEntity<>(player, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete player by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        boolean isDeleted = playerService.deletePlayer(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get players with most runs
    @GetMapping("/runs")
    public ResponseEntity<List<Player>> getPlayersWithMostRuns() {
        List<Player> players = playerService.getPlayersWithMostRuns();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Get players by role
    @GetMapping("/role/{role}")
    public ResponseEntity<List<Player>> getPlayersByRole(@PathVariable String role) {
        List<Player> players = playerService.getPlayersByRole(role);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Get top performers by wickets
    @GetMapping("/wickets/{minWickets}")
    public ResponseEntity<List<Player>> getTopPerformersByWickets(@PathVariable int minWickets) {
        List<Player> players = playerService.getTopPerformersByWickets(minWickets);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Get players by matches played
    @GetMapping("/matches/{minMatches}")
    public ResponseEntity<List<Player>> getPlayersByMatchesPlayed(@PathVariable int minMatches) {
        List<Player> players = playerService.getPlayersByMatchesPlayed(minMatches);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Get players by nationality
    @GetMapping("/nationality/{nationality}")
    public ResponseEntity<List<Player>> getPlayersByNationality(@PathVariable String nationality) {
        List<Player> players = playerService.getPlayersByNationality(nationality);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Get players by batting style
    @GetMapping("/batting-style/{battingStyle}")
    public ResponseEntity<List<Player>> getPlayersByBattingStyle(@PathVariable String battingStyle) {
        List<Player> players = playerService.getPlayersByBattingStyle(battingStyle);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Get players above a certain age
    @GetMapping("/age/{age}")
    public ResponseEntity<List<Player>> getPlayersAboveAge(@PathVariable int age) {
        List<Player> players = playerService.getPlayersAboveAge(age);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Get count of players with minimum matches played
    @GetMapping("/count/matches/{minMatches}")
    public ResponseEntity<Integer> countPlayersWithMinimumMatchesPlayed(@PathVariable int minMatches) {
        int count = playerService.countPlayersWithMinimumMatchesPlayed(minMatches);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    // Get players by team
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<Player>> getPlayersByTeam(@PathVariable Long teamId) {
        List<Player> players = playerService.getPlayersByTeam(teamId);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }
}
