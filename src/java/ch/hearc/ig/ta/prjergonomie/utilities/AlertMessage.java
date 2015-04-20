package ch.hearc.ig.ta.prjergonomie.utilities;

/**
 *
 * @author Fabien Maître
 */
public class AlertMessage {
  private String type; /*succes/info/warning/danger*/
  private String code;
  private String titre;
  private String message;

  public AlertMessage(String type, String titre, String message) {
    this.code = null;
    this.type = type;
    this.titre = titre;
    this.message = message;
  }

  public AlertMessage(String type, String code, String titre, String message) {
    this(type, titre, message);
    this.code = code;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getTitre() {
    return titre;
  }

  public void setTitre(String titre) {
    this.titre = titre;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
