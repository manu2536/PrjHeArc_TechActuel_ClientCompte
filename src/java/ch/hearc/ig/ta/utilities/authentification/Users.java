package ch.hearc.ig.ta.utilities.authentification;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Fabien Maître
 */
public class Users {
  //Clé = username, value = User
  private static Map<String, User> users;

  static{
    users = new HashMap<>();
    addUser(1, "fabien", "maitre", 200);
    addUser(2, "colin", "schaffner", 475);
    addUser(3, "jeremy", "wermeille", 145);
    addUser(4, "emmanuel", "rondez", 50);
    addUser(5, "francesco", "termine", 1470);
    addUser(6, "christophe", "francillon", 890);
  }

  private static Map<String, User> getUsers(){
    return users;
  }
  
  public static void addUser(final int id, final String username, final String password, final int points){
    User newUser = new User(id, username, password, points);
    getUsers().put(newUser.getLogin(), newUser);
  }
  public static Boolean verifyUser(final String username, final String password){
    Boolean result = false;
    if(userExists(username)){
      if(getUsers().get(username).getPassword().equals(password)){
        result = true;
      }
    }
    return result;
  }
  
  public static Boolean userExists(final String username){
    Boolean result = false;
    if(getUsers().containsKey(username)){ 
      result = true;
    }
    return result;
  }
  
  public static User getUser(final String username){
    User user = null;
    if(userExists(username)){
      user = getUsers().get(username);
    }
    return user;
  }
}
