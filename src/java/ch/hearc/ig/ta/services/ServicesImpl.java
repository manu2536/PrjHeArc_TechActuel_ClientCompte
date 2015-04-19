package ch.hearc.ig.ta.services;

import ch.hearc.ig.ta.business.Client;
import ch.hearc.ig.ta.business.Compte;
import ch.hearc.ig.ta.business.Virement;
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
import ch.hearc.ig.ta.utilities.authentification.User;
import ch.hearc.ig.ta.utilities.authentification.Users;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author jeremy.wermeill
 */
public class ServicesImpl {

  public ServicesImpl() {
  }

  /**
   * Cette méthode permet d'effectuer un transfert d'un compte à un autre.
   *
   * @param compteDebit
   * @param compteCredit
   * @param montant
   */
  public void transfert(Compte compteDebit, Compte compteCredit, float montant) throws MetierException{
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
  
  public Virement prepareVirementForList(Compte compteDebit, Compte compteCredit, float montant){
    
     Client clientDebit = ClientDao.searchClientByIdCompte(compteDebit.getIdentifiant());
     Client clientCredit = ClientDao.searchClientByIdCompte(compteCredit.getIdentifiant());
     
     Virement virement = new Virement();
     virement.setNomClientDebit(clientDebit.getNom());
     virement.setNomClientCredit(clientCredit.getNom());
     virement.setNoCptDebit(compteDebit.getNumero());
     virement.setNoCptCredit(compteCredit.getNumero());
     virement.setMontant(montant);
     Date date = new Date();
     //date du jour
     virement.setDateVirement(date);
     
     return virement;
  }
  /**
   * Permet d'ajouter un virement à la liste en première positiion
   * Permet aussi de limiter la liste des virements à 10 virements
   * @param virements
   * @param virement
   * @return 
   */
  public List<Virement> addVirementToList(List<Virement> virements, Virement virement){
   if(virements.size() >= 10){
     //on enlève le dixième élément
     virements.remove(9);
   }
   //dans tous les cas le nouvel élément ira en première position
     virements.add(0, virement);
     
     return virements;
      
   }

  public Virement transfert(int compteDebit, int compteCredit, float montant) throws MetierException{
    Compte debit;
    Compte credit;
    Virement virement = null;
    
    debit = CompteDao.researchByID(compteDebit);
    if(debit == null){
      throw new MetierException("Compte à débiter innexistant");  
    }
   
    credit = CompteDao.researchByID(compteCredit); 
    if(credit == null){
      throw new MetierException("Compte à créditer innexistant");  
    }
    
    
    if(debit.equals(credit)){
      throw new MetierException("Les comptes à débiter et à créditer doivent être différents");  
    }
    transfert(debit, credit, montant);
    //si tout s'est bien passé on retourne le virement
    virement = prepareVirementForList(debit, credit, montant);
    return virement;
    
  }

  /**
   * Cette méthode permet de verser un montant dans un compte TODO : remonter
   * l'exception correctement
   *
   * @param IDcompteCredit
   * @param montant
   * @throws ch.hearc.ig.ta.exceptions.MetierException
   */
  public void verser(int IDcompteCredit, float montant) throws MetierException {
    Connection connection = null;

    try {
      Compte compteCredit = CompteDao.researchByID(IDcompteCredit);
      if (compteCredit == null) {
        throw new IDCompteException("Le compte de crédit (ID " + IDcompteCredit + ") n'existe pas");
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
   * @param IDcompteDebit
   * @param montant
   * @throws ch.hearc.ig.ta.exceptions.MetierException
   */
  public void retirer(int IDcompteDebit, float montant) throws MetierException {
    Connection connection = null;
    try {
      Compte compteDebit = CompteDao.researchByID(IDcompteDebit);
      if (compteDebit == null) {
        throw new IDCompteException("Le compte Id " + IDcompteDebit + " n'existe pas");
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

  public int addClient(String nom, String prenom, String adresse, String ville) throws MetierException {

    Client newCli = new Client();
    newCli.setNom(nom);
    newCli.setPrenom(prenom);
    newCli.setAdresse(adresse);
    newCli.setVille(ville);

    int identifiant = (int) ClientDao.create(newCli);

    return identifiant;
  }

  public int addCompte(String nom, String solde, String taux, int idClient) throws MetierException {
    Compte c = new Compte();
    c.setNom(nom);
    c.setSolde(new Float(solde));
    c.setTaux(new Float(taux));

    int idCompte = (int) CompteDao.create(c, idClient);
    return idCompte;
  }
  
  
  public void updateClient(Client c) throws MetierException{
    
    
    
    if(c.getIdentifiant() == null){
      throw new MetierException("id Client non trouvé");  
    }else{
      ClientDao.update(c);
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

  public void commit(Connection connection) throws CommitException {
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

  public void loadAccounts(Client c) {
    ClientDao.loadAccounts(c);
  }
  
  /*Retourne l'objet user à partir de son username*/
  public User getUser(String username){
    return Users.getUser(username);
  }
}
