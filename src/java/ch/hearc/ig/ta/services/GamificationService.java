package ch.hearc.ig.ta.services;

import ch.hearc.ig.ta.utilities.Level;
import ch.hearc.ig.ta.utilities.authentification.User;
import ch.hearc.ig.ta.utilities.authentification.Users;
import java.util.ArrayList;
import java.util.Collections;
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
   * @param nbPoint
   */
  public void incrementScore(int nbPoint, User user) {
    user.addPoints(nbPoint);
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
   * Retourne une liste d'objets user triés dans l'ordre décroissant en
   * fonction de leur points.
   * @return  une liste de users triés
   */
  public List<User> getUsersWithScores(){
  
  Map<String,User> users = Users.getUsers();
  ArrayList<User> userList= new ArrayList<>();
    for (Map.Entry<String, User> entry : users.entrySet()) {
      User user = entry.getValue();
      userList.add(user);
    }
     Collections.sort(userList);
     
     return userList;
  
  }
  
}
