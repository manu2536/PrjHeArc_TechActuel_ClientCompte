package ch.hearc.ig.ta.services;

/**
 *
 * @author jeremy.wermeill
 */
public enum Level {
  Level1("Novice", 0, 100),
  Level2("Account Manager", 101, 300),
  Level3("Top Account Manager", 301, 700),
  Level4("Super Account Manager", 701, 1500),
  Level5("Rampage Account Manager", 1501, Integer.MAX_VALUE);
  
  final private String name;
  final private int from;
  final private int to;
  
  Level(String name, int from, int to){
    this.name = name;
    this.from = from;
    this.to = to;
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
