package ch.hearc.ig.ta.services;

import ch.hearc.ig.ta.business.Client;
import ch.hearc.ig.ta.business.Virement;
import java.util.Arrays;
import java.util.List;

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
   * @return le niveau du user
   */
  public String getLevel() {

    String level = "";

    if (score <= 50) {
      level = Level.Novice.name();
    }
    if (score > 50 && score <= 100) {
      level = Level.AccountManager.name();
    }

    if (score > 100 && score <= 150) {
      level = Level.TopAccountManager.name();
    }
    if (score > 150 && score <= 200) {
      level = Level.SuperAccountManager.name();
    }
    if (score > 200) {
      level = Level.RampageAccountManager.name();
    }

    return level;
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
