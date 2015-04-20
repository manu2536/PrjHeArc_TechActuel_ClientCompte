package ch.hearc.ig.ta.prjergonomie.utilities;

/**
 *
 * @author jeremy.wermeill
 */
public enum Level {
  Level1(1, "Novice", 0, 100, ""),
  Level2(2, "Account Manager", 101, 300, "Un café gratuit par jour"),
  Level3(3, "Top Account Manager", 301, 700, "Un repas gratuit par semaine"),
  Level4(4, "Super Account Manager", 701, 1500, "Une sortie annuelle avec repas offert"),
  Level5(5, "Rampage Account Manager", 1501, 10000, "Un weekend de sortie offert par année"),
  Level6(6, "Senior Banking Manager", 10001, Integer.MAX_VALUE, "Une semaine de vacances supplémentaire par année");
  
  final private int position;
  final private String name;
  final private int from;
  final private int to;
  final private String recompense;
  
  Level(int position, String name, int from, int to, String recompense){
    this.position = position;
    this.name = name;
    this.from = from;
    this.to = to;
    this.recompense = recompense;
  }

  public int getPosition() {
    return position;
  }
  
  public String getName() {
    return name;
  }

  public int getFrom() {
    return from;
  }

  public int getTo() {
    return to;
  }

  public String getRecompense() {
    return recompense;
  }
  
  public static Level getLevel(final int points){
    if(points > Level.Level1.getTo()){
      if(points > Level.Level2.getTo()){
        if(points > Level.Level3.getTo()){
          if(points > Level.Level4.getTo()){
            return Level5;
          }else{
            return Level4;
          }
        }else{
          return Level3;
        }
      }else{
        return Level2;
      }
    }else{
      return Level1;
    }
  }
}
