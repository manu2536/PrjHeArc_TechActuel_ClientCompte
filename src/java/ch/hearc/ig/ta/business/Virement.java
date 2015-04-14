/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.ta.business;

import java.util.Date;

/**
 *
 * @author jeremy.wermeill
 */
/**
 * Cette méthode permet de représenter un virement
 *
 * @author jeremy.wermeill
 */
public class Virement {

  private String noCptDebit;
  private String noCptCredit;
  private String nomClientDebit;
  private String nomClientCredit;
  private Float montant;
  private Date dateVirement;

  public String getNoCptDebit() {
    return noCptDebit;
  }

  public void setNoCptDebit(String noCptDebit) {
    this.noCptDebit = noCptDebit;
  }

  public String getNoCptCredit() {
    return noCptCredit;
  }

  public void setNoCptCredit(String noCptCredit) {
    this.noCptCredit = noCptCredit;
  }

  public String getNomClientDebit() {
    return nomClientDebit;
  }

  public void setNomClientDebit(String nomClientDebit) {
    this.nomClientDebit = nomClientDebit;
  }

  public String getNomClientCredit() {
    return nomClientCredit;
  }

  public void setNomClientCredit(String nomClientCredit) {
    this.nomClientCredit = nomClientCredit;
  }

  public Float getMontant() {
    return montant;
  }

  public void setMontant(Float montant) {
    this.montant = montant;
  }

  public Date getDateVirement() {
    return dateVirement;
  }

  public void setDateVirement(Date dateVirement) {
    this.dateVirement = dateVirement;
  }

}
