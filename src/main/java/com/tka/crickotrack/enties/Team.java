package com.tka.crickotrack.enties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Team name is mandatory")
    @Size(min = 3, max = 50, message = "Team name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Team code is mandatory")
    @Size(min = 2, max = 10, message = "Team code must be between 2 and 10 characters")
    private String teamCode;

    @NotBlank(message = "Owner name is mandatory")
    private String ownerName;

    @NotBlank(message = "Home ground is mandatory")
    private String homeGround;

    @DecimalMin(value = "0.0", inclusive = false, message = "Budget must be greater than 0")
    private Double budget;

    @PastOrPresent(message = "Established year must be in the past or present")
    private Integer establishedYear;

    @Min(value = 0, message = "Matches played cannot be negative")
    private int matchesPlayed;

    @Min(value = 0, message = "Wins cannot be negative")
    private int wins;

    @Min(value = 0, message = "Losses cannot be negative")
    private int losses;
}
