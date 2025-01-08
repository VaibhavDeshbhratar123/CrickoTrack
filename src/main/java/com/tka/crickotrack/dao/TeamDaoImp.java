package com.tka.crickotrack.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tka.crickotrack.enties.Team;
import com.tka.crickotrack.exception.DatabaseException;

@Repository
public class TeamDaoImp implements TeamDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean saveTeam(Team team) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(team);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseException("Failed to save team: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Team> getAllTeams() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Team";
            Query<Team> query = session.createQuery(hql, Team.class);
            return query.getResultList();
        } catch (HibernateException e) {
            throw new DatabaseException("Failed to fetch teams: " + e.getMessage(), e);
        }
    }

    @Override
    public Team getTeamById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Team.class, id);
        } catch (HibernateException e) {
            throw new DatabaseException("Failed to fetch team by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean updateTeam(Team team) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(team);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseException("Failed to update team: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteTeam(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Team team = session.get(Team.class, id);
            if (team != null) {
                session.delete(team);
                transaction.commit();
                return true;
            } else {
                throw new DatabaseException("Team not found with ID: " + id);
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseException("Failed to delete team: " + e.getMessage(), e);
        }
    }
}
