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
    List<String> accountNumber = generateNumeroCpt();
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
      virement.setNoCptDebit(accountNumber.get(i));
      //pour le credit on prend toujours le client +10
      virement.setNomClientCredit(clientsList.get(i + 10).getNom());
      virement.setNoCptCredit(accountNumber.get(i + 10));
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
   * Permet de construire une liste de numéro compte
   *
   * @return une liste de type List contenant des montants simulés
   */
  private List<String> generateNumeroCpt() {

    List<String> accountNumberList = new ArrayList<>();

    accountNumberList.add("CH72 0000 5564 5554 6442 7");
    accountNumberList.add("CH45 1200 5564 5824 6442 7");
    accountNumberList.add("CH58 1100 5564 1224 6442 7");
    accountNumberList.add("CH67 0000 5441 4642 6442 7");
    accountNumberList.add("CH79 1111 5564 5554 6442 7");
    accountNumberList.add("CH97 0000 5564 6424 6442 7");
    accountNumberList.add("CH46 9999 4545 5554 6442 7");
    accountNumberList.add("CH22 4444 5564 2121 6442 7");
    accountNumberList.add("CH11 3333 5521 5251 6442 7");
    accountNumberList.add("CH68 0000 5564 6421 6442 7");
    accountNumberList.add("CH72 2121 5564 5554 6442 7");
    accountNumberList.add("CH93 3333 6445 5554 6442 7");
    accountNumberList.add("CH45 5621 5564 2121 6442 3");
    accountNumberList.add("CH30 7411 5423 8641 6442 2");
    accountNumberList.add("CH32 5341 5564 5554 6442 1");
    accountNumberList.add("CH56 6952 5564 5554 6442 4");
    accountNumberList.add("CH54 0000 7521 5554 6442 5");
    accountNumberList.add("CH55 5521 5564 5554 6442 6");
    accountNumberList.add("CH75 4521 3641 5554 6442 8");
    accountNumberList.add("CH65 1576 7544 5554 6442 9");

    return accountNumberList;
  }

  /**
   * Permet de construire une liste de dates
   *
   * @return une liste de type List contenant des dates simulées
   */
  private List<Date> generateDate() throws ParseException {

    List<Date> dateList = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    dateList.add(sdf.parse("11.11.2010"));
    dateList.add(sdf.parse("12.12.2010"));
    dateList.add(sdf.parse("01.01.2011"));
    dateList.add(sdf.parse("03.03.2011"));
    dateList.add(sdf.parse("04.04.2011"));
    dateList.add(sdf.parse("06.06.2011"));
    dateList.add(sdf.parse("05.11.2011"));
    dateList.add(sdf.parse("13.05.2012"));
    dateList.add(sdf.parse("14.06.2012"));
    dateList.add(sdf.parse("15.06.2012"));
    dateList.add(sdf.parse("14.07.2012"));
    dateList.add(sdf.parse("09.08.2012"));
    dateList.add(sdf.parse("15.08.2012"));
    dateList.add(sdf.parse("20.12.2012"));
    dateList.add(sdf.parse("07.04.2013"));
    dateList.add(sdf.parse("15.03.2014"));
    dateList.add(sdf.parse("15.09.2014"));
    dateList.add(sdf.parse("15.09.2009"));
    dateList.add(sdf.parse("09.10.2014"));
    dateList.add(sdf.parse("09.11.2014"));
    dateList.add(sdf.parse("21.12.2014"));
    dateList.add(sdf.parse("05.04.2015"));
    dateList.add(sdf.parse("30.03.2015"));
    dateList.add(sdf.parse("15.03.2015"));
    dateList.add(sdf.parse("12.03.2015"));
    dateList.add(sdf.parse("15.02.2015"));
    dateList.add(sdf.parse("23.12.2014"));
    dateList.add(sdf.parse("23.12.2014"));
    dateList.add(sdf.parse("22.12.2014"));
    dateList.add(sdf.parse("22.12.2014"));
    dateList.add(sdf.parse("21.12.2014"));
    
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
     
    for(int i = 0; i < 20; i++){
      tempComptes.get(i).setUserName(usernames.get(i));
      tempComptes.get(i).setDateOuverture(dates.get(i));
      comptes.add(tempComptes.get(i));
    
    }
    
  }
  
  
  public List<String> usernames(){
  
    List<String> usernames = new ArrayList<>();
    
    usernames.add("francesco");
    usernames.add("francesco");
    usernames.add("francesco");
    usernames.add("francesco");
    usernames.add("francesco");
    usernames.add("francesco");
    usernames.add("francesco");
    usernames.add("francesco");
    usernames.add("fabien");
    usernames.add("fabien");
    usernames.add("fabien");
    usernames.add("fabien");
    usernames.add("jeremy");
    usernames.add("jeremy");
    usernames.add("jeremy");
    usernames.add("colin");
    usernames.add("colin");
    usernames.add("colin");
    usernames.add("emmanuel");
    usernames.add("emmanuel");
    
    return usernames;
  }

  public List<Compte> getComptes() {
    return comptes;
  }
  
  
  
}
