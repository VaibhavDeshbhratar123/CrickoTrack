package com.tka.crickotrack.enties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Player name is mandatory")
    private String name;

    @Min(value = 18, message = "Player must be at least 18 years old")
    @Max(value = 40, message = "Player age cannot exceed 40")
    private int age;

    @NotBlank(message = "Role is mandatory")
    private String role; // e.g., Batsman, Bowler, All-rounder

    @NotNull(message = "Jersey number is mandatory")
    @Min(value = 1, message = "Jersey number must be greater than 0")
    private int jerseyNumber;

    @NotBlank(message = "Nationality is mandatory")
    private String nationality;

    @NotBlank(message = "Batting style is mandatory")
    private String battingStyle;

    private String bowlingStyle;

    @Min(value = 0, message = "Matches played cannot be negative")
    private int matchesPlayed;

    @Min(value = 0, message = "Total runs cannot be negative")
    private int totalRuns;

    @Min(value = 0, message = "Wickets taken cannot be negative")
    private int wicketsTaken;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}
