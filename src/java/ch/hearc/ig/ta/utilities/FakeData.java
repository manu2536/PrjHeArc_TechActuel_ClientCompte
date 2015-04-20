package ch.hearc.ig.ta.utilities;

import ch.hearc.ig.ta.business.Client;
import ch.hearc.ig.ta.business.Compte;
import ch.hearc.ig.ta.business.Virement;
import ch.hearc.ig.ta.dao.ClientDao;
import ch.hearc.ig.ta.dao.CompteDao;
import ch.hearc.ig.ta.log.ApplicationLogger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeremy.wermeill
 */
/**
 * Cette classe permet de charger des données en mémoire et est utile à
 * l'affichage.
 */
public class FakeData {

  private List<Virement> virementList;
  private List<Compte> comptes;
  private List<Client> clientListWithInscriptionDate;
  private List<Client> clientsList;
  private String fakeNumeroCpt = "";

  public FakeData() {

    //on initialise la liste des clients (on ne charge ainsi qu'une fois en DB)
    initClients();
    //on initialise la liste des virements
    initAllVirements();
    //on modifie la liste des clients et on ajoute à chacun des clients une date fictive d'inscription
    initDateInscriptionIntoClients();
    //les comptes ouverts
    initGetCpOuverts();
  }

  /**
   * Cette méthode prépare une liste d'objets virements (10 objets chargés) ->
   * on n'a pas de données concernant les virements dans la DB, on a donc cette
   * méthode qui doit être initié au lancement de l'application pour avoir un
   * set de données. Cette liste doit être réalimentée dès qu'un nouveau
   * virement est fait
   *
   */
  public void initAllVirements() {
    List<Float> amountsList = generateTenAmounts();
    List<Date> dateList = null;
    try {
      dateList = generateDate();
    } catch (ParseException ex) {
      ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
    }
    virementList = new ArrayList<>();
// on parcourt la liste des clients (on prend les 10 premiers)   
    for (int i = 0; i < 10; i++) {
      Virement virement = new Virement();
      virement.setNomClientDebit(clientsList.get(i).getNom());
      virement.setNoCptDebit(getFakeNumeroCpt());
      //pour le credit on prend toujours le client +10
      virement.setNomClientCredit(clientsList.get(i + 10).getNom());
      virement.setNoCptCredit(getFakeNumeroCpt());
      virement.setMontant(amountsList.get(i));
      virement.setDateVirement(dateList.get(i));
      //on ajoute à la liste le virement préparé
      virementList.add(virement);
    }
  }

  /**
   * Rempli la liste des clients
   */
  public void initClients() {
    clientsList = ClientDao.researchAll();
  }

  /**
   * prépare une liste de 20 clients avec la date d'inscription Cette méthode
   * remplie la liste des clients ayant une date d'inscription
   */
  public void initDateInscriptionIntoClients() {
    List<Date> dateList = null;
    clientListWithInscriptionDate = new ArrayList<>();
    try {
      //on récupère la liste de date
      dateList = generateDate();
    } catch (ParseException ex) {
      ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
    }
    //on parcourt tous les clients et on leur ajoute une date d'inscription
    for (int i = 0; i < 20; i++) {
      Client client = clientsList.get(i);
      client.setDateInscription(dateList.get(i));
      clientListWithInscriptionDate.add(client);
    }
  }

  /**
   * Permet de construire une liste de montants simulés
   *
   * @return une liste de type List contenant des montants simulés
   */
  private List<Float> generateTenAmounts() {

    List<Float> amountList = new ArrayList<>();

    amountList.add(100f);
    amountList.add(250f);
    amountList.add(254.5f);
    amountList.add(2645.55f);
    amountList.add(268.10f);
    amountList.add(106f);
    amountList.add(400f);
    amountList.add(860.5f);
    amountList.add(285.55f);
    amountList.add(750.10f);

    return amountList;
  }

  /**
   * Permet de construire un faux numéro de compte
   *
   * @return un numéro de compte simulé
   */
  private String getFakeNumeroCpt() {
   
    this.fakeNumeroCpt ="CHXX XXXX XXXX XXXX XXXX X";
    return this.fakeNumeroCpt;
  }

  /**
   * Permet de construire une liste de dates
   *
   * @return une liste de type List contenant des dates simulées
   */
  private List<Date> generateDate() throws ParseException {

    List<Date> dateList = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    dateList.add(sdf.parse("11.01.2015"));
    dateList.add(sdf.parse("12.02.2015"));
    dateList.add(sdf.parse("01.01.2015"));
    dateList.add(sdf.parse("03.03.2015"));
    dateList.add(sdf.parse("04.04.2015"));
    dateList.add(sdf.parse("06.02.2015"));
    dateList.add(sdf.parse("05.02.2015"));
    dateList.add(sdf.parse("13.04.2015"));
    dateList.add(sdf.parse("14.04.2015"));
    dateList.add(sdf.parse("15.01.2015"));
    dateList.add(sdf.parse("14.02.2015"));
    dateList.add(sdf.parse("09.01.2015"));
    dateList.add(sdf.parse("15.02.2015"));
    dateList.add(sdf.parse("20.02.2015"));
    dateList.add(sdf.parse("07.04.2015"));
    dateList.add(sdf.parse("15.03.2015"));
    dateList.add(sdf.parse("15.04.2015"));
    dateList.add(sdf.parse("15.02.2015"));
    dateList.add(sdf.parse("09.04.2015"));
    dateList.add(sdf.parse("09.03.2015"));
    dateList.add(sdf.parse("21.02.2015"));
    dateList.add(sdf.parse("05.04.2015"));
    dateList.add(sdf.parse("30.03.2015"));
    dateList.add(sdf.parse("15.03.2015"));
    dateList.add(sdf.parse("12.03.2015"));
    dateList.add(sdf.parse("15.02.2015"));
    dateList.add(sdf.parse("23.02.2015"));
    dateList.add(sdf.parse("28.02.2015"));
    dateList.add(sdf.parse("20.01.2015"));
    dateList.add(sdf.parse("17.04.2015"));
    dateList.add(sdf.parse("17.03.2015"));
    
    Collections.sort(dateList, Collections.reverseOrder());
   
    return dateList;
  }

  /**
   * Retourne la liste des virements avec une sourcouche de données fictives
   *
   * @return
   */
  public List<Virement> getVirementList() {
    return virementList;
  }

  /**
   * Retourne la liste des clients avec des dates d'inscriptions fictives
   *
   * @return
   */
  public List<Client> getClientsListWithInscriptionDate() {
    return clientListWithInscriptionDate;
  }
/**
 * utilisé uniquement pour des statistiques
 * @return une liste de comptes ouverts
 */
  public void initGetCpOuverts(){
  //cette liste récupère 20 comptes en DB et les complètes avec les informations utilisateurs
    List<Compte> tempComptes = CompteDao.researchAll();
    comptes = new ArrayList<>();
    List<String> usernames = usernames();
    List<Date> dates =null;
    try {
      dates = generateDate();
    } catch (ParseException ex) {
       ApplicationLogger.getInstance().log(Level.SEVERE, null, ex);
    }
     
    for(int i = 0; i < 30; i++){
      tempComptes.get(i).setUserName(usernames.get(i));
      tempComptes.get(i).setDateOuverture(dates.get(i));
      comptes.add(tempComptes.get(i));
    
    }
    
  }
  
  
  public List<String> usernames(){
  
    List<String> usernames = new ArrayList<>();
    
    usernames.add("francesco");
    usernames.add("emmanuel");
    usernames.add("colin");
    usernames.add("francesco");
    usernames.add("fabien");
    usernames.add("francesco");
    usernames.add("colin");
    usernames.add("francesco");
    usernames.add("francesco");
    usernames.add("fabien");
    usernames.add("francesco");
    usernames.add("fabien");
    usernames.add("emmanuel");
    usernames.add("fabien");
    usernames.add("fabien");
    usernames.add("emmanuel");
    usernames.add("fabien");
    usernames.add("jeremy");
    usernames.add("jeremy");
    usernames.add("emmanuel");
    usernames.add("jeremy");
    usernames.add("francesco");
    usernames.add("colin");
    usernames.add("colin");
    usernames.add("jeremy");
    usernames.add("colin");
    usernames.add("francesco");
    usernames.add("jeremy");
    usernames.add("emmanuel");
    usernames.add("fabien");
    
    return usernames;
  }

  public List<Compte> getComptes() {
    return comptes;
  }
  
  
  
}
