package ch.hearc.ig.ta.services;

import ch.hearc.ig.ta.business.Client;
import ch.hearc.ig.ta.business.Compte;
import ch.hearc.ig.ta.dao.ClientDao;
import ch.hearc.ig.ta.dao.CompteDao;
import ch.hearc.ig.ta.dbfactory.OracleConnections;
import ch.hearc.ig.ta.exceptions.AccountDaoException;
import ch.hearc.ig.ta.exceptions.CommitException;
import ch.hearc.ig.ta.exceptions.ConnectionProblemException;
import ch.hearc.ig.ta.exceptions.IDCompteException;
import ch.hearc.ig.ta.exceptions.InsufficientFundException;
import ch.hearc.ig.ta.exceptions.InvalidMontantException;
import ch.hearc.ig.ta.exceptions.MetierException;
import ch.hearc.ig.ta.exceptions.RollbackException;
import ch.hearc.ig.ta.log.ApplicationLogger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author jeremy.wermeill
 */
public class ServicesImpl {

  /**
   * Cette méthode permet d'effectuer un transfert d'un compte à un autre.
   *
   * @param compteDebit
   * @param compteCredit
   * @param montant
   */
  public void transfert(Compte compteDebit, Compte compteCredit, float montant) {
    Connection connection = null;
    try {
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
 
  public void verser(int IDcompteCredit, float montant) throws MetierException {
    Connection connection = null;
    
    try {
      Compte compteCredit = CompteDao.researchByID(IDcompteCredit);
      if(compteCredit==null){
        throw new IDCompteException("Le compte Id "+IDcompteCredit+" n'existe pas");
      }
      //on contrôle la validité du montant 
      checkAmountValidity(montant);
      //modifier l'objet 
      compteCredit.setSolde(compteCredit.getSolde() + montant);
      //récupérer une connexion et mettre à jour le compte
      connection = initConnection();
      CompteDao.update(compteCredit, connection);
      commit(connection);
    } catch (InvalidMontantException | IDCompteException ex) {
      ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
      throw new MetierException(ex);
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
  public void retirer(int IDcompteDebit, float montant) throws MetierException{
    Connection connection = null;
    try {
      Compte compteDebit = CompteDao.researchByID(IDcompteDebit);
      if(compteDebit==null){
        throw new IDCompteException("Le compte Id "+IDcompteDebit+" n'existe pas");
      }
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
    } catch (InvalidMontantException | InsufficientFundException | IDCompteException ex) {
      //ce sont des exceptions métiers. A voir comment on les remonte.
      ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
      throw new MetierException(ex);
      
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

  public List<Client> getClientsAll() {
    Connection connection = null;
    try {
      connection = initConnection();
      return ClientDao.researchAll();
    } catch (ConnectionProblemException ex) {
      ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
      return null;
    } finally {
      try {
        // dans tous les cas on ferme la connexion.
        closeConnection(connection);
      } catch (ConnectionProblemException ex) {
        ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
      }
    }
  }
  
  public List<Client> searchClientFullText(String recherche) {
    Connection connection = null;
    try {
      connection = initConnection();
      return ClientDao.researchFullText(recherche);
    } catch (ConnectionProblemException ex) {
      ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
      return null;
    } finally {
      try {
        // dans tous les cas on ferme la connexion.
        closeConnection(connection);
      } catch (ConnectionProblemException ex) {
        ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
      }
    }
  }

  public List<Client> searchClient(String recherche) {
    Connection connection = null;
    try {
      connection = initConnection();
      return ClientDao.researchFullText(recherche);
    } catch (ConnectionProblemException ex) {
      ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
      return null;
    } finally {
      try {
        // dans tous les cas on ferme la connexion.
        closeConnection(connection);
      } catch (ConnectionProblemException ex) {
        ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
      }
    }
  }

  public int addClient(String nom, String prenom, String adresse, String ville) {

    Client newCli = new Client();
    newCli.setNom(nom);
    newCli.setPrenom(prenom);
    newCli.setAdresse(adresse);
    newCli.setVille(ville);

    int identifiant = (int) ClientDao.create(newCli);

    return identifiant;
  }

  public void addCompte(String nom, String solde, String taux, int idClient) {
    Compte c = new Compte();
    c.setNom(nom);
    c.setSolde(new Float(solde));
    c.setTaux(new Float(taux));

    int idCompte = (int) CompteDao.create(c, idClient);
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
    } catch (SQLException | NullPointerException ex) {
      throw new ConnectionProblemException("An exception occured while trying to close connection : " + ex);
    }
  }

  private void checkAmountValidity(float montant) throws InvalidMontantException {
    if (montant < 0 || montant == 0) {
      throw new InvalidMontantException("le montant ne peut pas être inférieur ou égal à zéro");
    }
  }

  /**
   *
   * @param id
   * @return Client
   */
  public Client searchClientById(String id) {
    Connection connection = null;
    try {
      connection = initConnection();
      Client cl = new Client();
      cl.setIdentifiant(Integer.parseInt(id));
      return ClientDao.research(cl).get(0);
    } catch (ConnectionProblemException ex) {
      ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
      return null;
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
   * Cette méthode contrôle si le compte est solvable
   *
   * @param compte
   */
  private void checkSolvency(Compte compte, float montant) throws InsufficientFundException {

    if (compte.getSolde() - montant < 0) {
      throw new InsufficientFundException("le solde du compte est insuffisant");
    }

  }
  
  public void loadAccounts(Client c){
    ClientDao.loadAccounts(c);
  }

  public void forTransfert(int idCompteDebit, int idCompteCredi, float montantTransfert)throws MetierException {
    Compte debit = new Compte();
    Compte credit = new Compte();
    
    debit = CompteDao.researchByID(idCompteDebit);
    credit = CompteDao.researchByID(idCompteCredi);
    
    transfert(debit, credit, montantTransfert);
    
  }

}
