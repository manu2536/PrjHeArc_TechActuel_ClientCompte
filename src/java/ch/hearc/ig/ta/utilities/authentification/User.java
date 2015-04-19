package ch.hearc.ig.ta.utilities.authentification;

/**
 *
 * @author Fabien Maître
 */
public class User implements Comparable<User>{
  private int id;
  private String login;
  private String password;
  private int points;

  public User() {
    this.points = 0;
  }

  public User(int id, String login, String password) {
    this();
    this.id = id;
    this.login = login;
    this.password = password;
  }
  
  
  public User(int id, String login, String password, int points) {
    this(id, login, password);
    this.points = points;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  @Override
  public int compareTo(User compareUser) {
  int comparePoints = ((User) compareUser).getPoints();
  
  return this.points - comparePoints;
  }
  
  
}
