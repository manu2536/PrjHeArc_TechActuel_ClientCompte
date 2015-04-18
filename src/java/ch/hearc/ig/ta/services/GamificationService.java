package ch.hearc.ig.ta.services;

import ch.hearc.ig.ta.utilities.authentification.User;

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
  public void incrementScore() {
    score++;
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
   * TOP 5 des clients ayant fait le virement le plus élevé
   *
   * @param clientList
   * @return
   */
//  public List<Client> topFive(List<Virement> virementList) {
//
//    float[] montants = new float[virementList.size()];
//    for (int i = 0; i <= virementList.size(); i++) {
//      montants[i] = virementList.get(i).get
//    }
//
//    Arrays.sort(clientList);
//
//    return null;
//  }

}
