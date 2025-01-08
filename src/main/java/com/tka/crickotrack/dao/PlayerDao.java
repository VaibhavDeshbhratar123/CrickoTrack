package com.tka.crickotrack.dao;

import java.util.List;

import com.tka.crickotrack.enties.Player;

public interface PlayerDao {
	boolean savePlayer(Player player);

	List<Player> getAllPlayers();

	Player getPlayerById(Long id);

	boolean updatePlayer(Player player);

	boolean deletePlayer(Long id);
}
