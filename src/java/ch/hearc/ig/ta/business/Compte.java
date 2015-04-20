package ch.hearc.ig.ta.business;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/*Test*/
public class Compte implements Serializable {

  private Integer identifiant;
  private String numero;
  private String nom;
  private Float solde;
  // JWE : ajouté pour les statistiques
  private Date dateOuverture;
  // JWE : ajouté pour les statistiques également
  private String userName;
  private String defaultIBANNumber;
  private Float taux;

  public Compte() {
    this.identifiant = -1;
    this.numero = "-1";
    this.nom = "";
    this.solde = 0f;
    this.taux = 0f;
  }

  public Compte(Integer _identifiant, String _nom, Float _solde, Float _taux) {
    this();
    this.identifiant = _identifiant;
    this.nom = _nom;
    this.solde = _solde;
    this.taux = _taux;
  }

  @Override
  public String toString() {
    return String.valueOf(identifiant) + "," + nom + "," + String.valueOf(solde) + "," + String.valueOf(taux);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Compte)) {
      return false;
    }
    Compte objCompte = (Compte) obj;

    Boolean equals = false;
    if (objCompte.getIdentifiant().compareTo(this.getIdentifiant()) == 0) {
      if (objCompte.getNumero().equals(this.getNumero())) {
        if (objCompte.getNom().equals(this.getNom())) {
          if (objCompte.getSolde().compareTo(this.getSolde()) == 0) {
            if (objCompte.getTaux().compareTo(this.getTaux()) == 0) {
              equals = true;
            }
          }
        }
      }
    }

    return equals;
  }

  public void print() {
    System.out.println(this.toString());
  }

  public boolean isNull() {
    return nom == null && solde == null && taux == null && identifiant == null;
  }

  public Integer getIdentifiant() {
    return identifiant;
  }

  public void setIdentifiant(Integer identifiant) {
    this.identifiant = identifiant;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public Float getSolde() {
    return solde;
  }

  public void setSolde(Float solde) {
    this.solde = solde;
  }

  public Float getTaux() {
    return taux;
  }

  public void setTaux(Float taux) {
    this.taux = taux;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public Date getDateOuverture() {
    return dateOuverture;
  }

  public void setDateOuverture(Date dateOuverture) {
    this.dateOuverture = dateOuverture;
  }

  /**
   * A utiliser pour de l'affichage uniquement
   *
   * @return une date formattée de type jj.mm.aaaa
   */
  public String getFormatedDate() {
    SimpleDateFormat sdfy = new SimpleDateFormat("MM");
    String month = sdfy.format(dateOuverture);

    String formatedDate = getDay() + "." + month + "." + getYear();
    return formatedDate;
  }

  public String getYear() {
    SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
    return sdfy.format(dateOuverture);
  }

  public Integer getMonth() {
    SimpleDateFormat sdfy = new SimpleDateFormat("MM");
    String month = sdfy.format(dateOuverture);
    Integer monthInt = Integer.valueOf(month);
    return monthInt - 1;
  }

  public String getDay() {
    SimpleDateFormat sdfy = new SimpleDateFormat("dd");
    return sdfy.format(dateOuverture);
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getDefaultIBANNumber() {
    defaultIBANNumber = "CHXX XXXX XXXX XXXX XXXX X";
    return defaultIBANNumber;
  }

  
  
}
