/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.ta.services;

import ch.hearc.ig.ta.business.Compte;
import ch.hearc.ig.ta.dao.CompteDao;
import ch.hearc.ig.ta.dbfactory.OracleConnections;
import ch.hearc.ig.ta.exceptions.AccountDaoException;
import ch.hearc.ig.ta.exceptions.CommitException;
import ch.hearc.ig.ta.exceptions.ConnectionProblemException;
import ch.hearc.ig.ta.exceptions.InsufficientFundException;
import ch.hearc.ig.ta.exceptions.InvalidMontantException;
import ch.hearc.ig.ta.exceptions.RollbackException;
import ch.hearc.ig.ta.log.ApplicationLogger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 *
 * @author jeremy.wermeill
 */
public class ServicesImpl implements Services {

  /**
   * Cette méthode permet d'effectuer un transfert d'un compte à un autre.
   *
   * @param compteDebit
   * @param compteCredit
   * @param montant
   */
  public void transfert(Compte compteDebit, Compte compteCredit, float montant) {
      Connection connection = null;
      try{
     //vérifier la validité du montant
      checkAmountValidity(montant);
      //on vérifie si le compte à créditer est solvable
      checkSolvency(compteDebit, montant);
      //on débite le compte à débiter
      compteDebit.setSolde(compteDebit.getSolde() - montant);
      //on crédite le compte à créditer
      compteCredit.setSolde(compteCredit.getSolde() + montant);
     //on update les comptes en DB
      connection = initConnection();
      CompteDao.update(compteDebit, connection);
      CompteDao.update(compteCredit, connection);
      commit(connection);
       } catch (InvalidMontantException | InsufficientFundException ex) {
      //ce sont des exceptions métiers. A voir comment on les remonte.
      ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
    } catch (ConnectionProblemException | CommitException | AccountDaoException ex) {
      ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
      try {
        //si on a une erreur lié à la connection, on fait un rollback
        rollback(connection);
      } catch (RollbackException ex1) {
        ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
      }
    } finally {
      try {
        // dans tous les cas on ferme la connexion.
        closeConnection(connection);
      } catch (ConnectionProblemException ex) {
        ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
      }
    }
   
  }

  /**
   * Cette méthode permet de verser un montant dans un compte TODO : remonter
   * l'exception correctement
   *
   * @param compteCredit
   * @param montant
   */
  @Override
  public void verser(Compte compteCredit, float montant) {
    Connection connection = null;
    try {
      //on contrôle la validité du montant 
      checkAmountValidity(montant);
      //modifier l'objet 
      compteCredit.setSolde(compteCredit.getSolde() + montant);
      //récupérer une connexion et mettre à jour le compte
      connection = initConnection();
      CompteDao.update(compteCredit, connection);
      commit(connection);
    } catch (InvalidMontantException ex) {
        ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
    } catch (ConnectionProblemException | CommitException | AccountDaoException ex) {
      ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
      try {
        //si on a une erreur liée à la connection, on fait un rollback
        rollback(connection);
      } catch (RollbackException ex1) {
        ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
      }
    } finally {
      try {
        // dans tous les cas on ferme la connexion.
        closeConnection(connection);
      } catch (ConnectionProblemException ex) {
       ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
      }
    }
  }

  /**
   * Cette méthode permet de retirer un montant d'un compte TODO: voir ou
   * remonter l'exception
   *
   * @param compteDebit
   * @param montant
   */
  @Override
  public void retirer(Compte compteDebit, float montant) {
    Connection connection = null;
    try {
      //contrôle la validité du montant 
      checkAmountValidity(montant);
      //on contrôle si le solde est suffisant pour retirer le montant
      checkSolvency(compteDebit, montant);
      //débiter le compte
      compteDebit.setSolde(compteDebit.getSolde() - montant);
      //mettre à jour le compte avec le nouveau solde
      connection = initConnection();
      //on update le montant en db
      CompteDao.update(compteDebit, connection);
      commit(connection);
    } catch (InvalidMontantException | InsufficientFundException ex) {
      //ce sont des exceptions métiers. A voir comment on les remonte.
     ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
    } catch (ConnectionProblemException | CommitException | AccountDaoException ex) {
      ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
      try {
        //si on a une erreur lié à la connection, on fait un rollback
        rollback(connection);
      } catch (RollbackException ex1) {
        ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
      }
    } finally {
      try {
        // dans tous les cas on ferme la connexion.
        closeConnection(connection);
      } catch (ConnectionProblemException ex) {
        ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
      }
    }
  }

  /**
   * Méthodes privées pour gérer les connexions
   */
  /**
   * Initialisation d'une connection
   *
   * @return : une connexion oracle sortie du pool de connexion.
   */
  private Connection initConnection() throws ConnectionProblemException {
    Connection connection = null;
    try {
      connection = OracleConnections.getConnection();
    } catch (SQLException ex) {
      throw new ConnectionProblemException("An exception occured while trying to connect : " + ex);
    }
    return connection;
  }

  private void commit(Connection connection) throws CommitException {
    try {
      connection.commit();
    } catch (SQLException ex) {
      throw new CommitException("An exception occured while trying to commit : " + ex);
    }
  }

  private void rollback(Connection connection) throws RollbackException {
    try {
      connection.rollback();
    } catch (SQLException ex) {
      throw new RollbackException("An exception occured while trying to rollback " + ex);
    }
  }

  private void closeConnection(Connection connection) throws ConnectionProblemException {
    try {
      connection.close();
    } catch (SQLException ex) {
      throw new ConnectionProblemException("An exception occured while trying to close connection : " + ex);
    }
  }

  private void checkAmountValidity(float montant) throws InvalidMontantException {
    if (montant < 0 || montant == 0) {
      throw new InvalidMontantException("le montant ne peut pas être inférieur ou égal à zéro");
    }
  }

  /**
   * Cette méthode contrôle si le compte est solvable
   *
   * @param compte
   */
  private void checkSolvency(Compte compte, float montant) throws InsufficientFundException {

    if (compte.getSolde() - montant < 0) {
      throw new InsufficientFundException("le solde du compte est insuffisant");
    }

  }
}
