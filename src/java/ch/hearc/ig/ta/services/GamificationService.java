package ch.hearc.ig.ta.services;

import ch.hearc.ig.ta.business.Compte;
import ch.hearc.ig.ta.business.Virement;
import ch.hearc.ig.ta.utilities.authentification.User;
import ch.hearc.ig.ta.utilities.authentification.Users;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jeremy.wermeill
 */
/**
 * Cette classe permet d'effectuer tous les traitements métiers utiles à la
 * gamification.
 *
 * @author jeremy.wermeill
 */
public class GamificationService {

  private int score = 0;

  public GamificationService() {
  }

  /**
   * permet d'incrémenter le score du banquier
   */
  public void incrementScore(int nbPoint) {
    score += nbPoint;
  }

  /**
   * Permet de déterminer le niveau du gulu en fonction de son score
   *
   * @param user
   * @return le niveau du user
   */
  public Level getLevel(User user) {
    return Level.getLevel(user.getPoints());
  }

  /**
   * Retourne une liste d'objets user triés dans l'ordre décroissant en fonction
   * de leur points.
   *
   * @return une liste de users triés
   */
  public List<User> getUsersWithScores() {

    Map<String, User> users = Users.getUsers();
    ArrayList<User> userList = new ArrayList<>();
    for (Map.Entry<String, User> entry : users.entrySet()) {
      User user = entry.getValue();
      userList.add(user);
    }
    Collections.sort(userList);

    return userList;

  }

  public String getLimitedYear() {
    Date date = new Date();
    SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
    return sdfy.format(date);

  }

  public Map<Integer, Integer> getNbCompteOuvertsByMonth(List<Compte> comptes, String username) {
    Map<Integer, Integer> mapMoisCpOuverts = new HashMap();
    for (Compte compte : comptes) {
      //on limite à l'année acutelle et à l'utilisateur de session
      if (compte.getYear().equals(getLimitedYear()) && compte.getUserName().equals(username)) {

        if (mapMoisCpOuverts.get(compte.getMonth()) == null) {
          mapMoisCpOuverts.put(compte.getMonth(), 1);
        } else {
          int value = mapMoisCpOuverts.get(compte.getMonth());
          value++;
          mapMoisCpOuverts.remove(compte.getMonth());
          mapMoisCpOuverts.put(compte.getMonth(), value);
        }
      }

    }
    return mapMoisCpOuverts;
  }

}
