/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.ta.services;

import modele.Compte;

/**
 *
 * @author jeremy.wermeill
 */
public interface Services {
  
  
  public void transfert(Compte compteDebit, Compte compteCredit, float montant);
  
  public void verser(Compte compteCredit, float montant);
  
  public void retirer(Compte compteDebit, float montant);
  
}
