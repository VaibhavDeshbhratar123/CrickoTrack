package com.tka.crickotrack.dao;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tka.crickotrack.enties.Player;
import com.tka.crickotrack.exception.DatabaseException;
import com.tka.crickotrack.exception.InvalidIdException;
import com.tka.crickotrack.exception.PlayerNotFoundException;

@Repository
public class PlayerDaoImp implements PlayerDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean savePlayer(Player player) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.save(player);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new DatabaseException("Failed to save player: " + e.getMessage(), e);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new DatabaseException("Unexpected error occurred while saving player.", e);
		}
	}

	@Override
	public List<Player> getAllPlayers() {
		try (Session session = sessionFactory.openSession()) {
			String hql = "FROM Player";
			Query<Player> query = session.createQuery(hql, Player.class);
			List<Player> players = query.getResultList();
			if (players.isEmpty()) {
				throw new DatabaseException("No players found.");
			}
			return players;
		} catch (HibernateException e) {
			throw new DatabaseException("Hibernate exception occurred while fetching players: " + e.getMessage(), e);
		} catch (Exception e) {
			throw new DatabaseException("Unexpected error occurred while fetching players.", e);
		}
	}

	@Override
	public Player getPlayerById(Long id) {
		if (id == null || id <= 0) {
			throw new InvalidIdException("Invalid ID: ID cannot be null or non-positive.");
		}

		try (Session session = sessionFactory.openSession()) {
			Player player = session.get(Player.class, id);
			if (player == null) {
				throw new PlayerNotFoundException("Player not found with ID: " + id);
			}
			return player;
		} catch (HibernateException e) {
			throw new DatabaseException("Hibernate exception occurred while fetching player by ID: " + e.getMessage(),
					e);
		} catch (Exception e) {
			throw new DatabaseException("Unexpected error occurred while fetching player by ID.", e);
		}
	}

	@Override
	public boolean updatePlayer(Player player) {
		if (player == null || player.getId() == null || player.getId() <= 0) {
			throw new InvalidIdException("Player or Player ID cannot be null or non-positive.");
		}

		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.update(player);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new DatabaseException("Failed to update player: " + e.getMessage(), e);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new DatabaseException("Unexpected error occurred while updating player.", e);
		}
	}

	@Override
	public boolean deletePlayer(Long id) {
		if (id == null || id <= 0) {
			throw new InvalidIdException("Invalid ID: ID cannot be null or non-positive.");
		}

		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			Player player = session.get(Player.class, id);
			if (player != null) {
				session.delete(player);
				transaction.commit();
				return true;
			} else {
				throw new DatabaseException("Player not found with ID: " + id);
			}
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new DatabaseException("Failed to delete player: " + e.getMessage(), e);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new DatabaseException("Unexpected error occurred while deleting player.", e);
		}
	}
}
