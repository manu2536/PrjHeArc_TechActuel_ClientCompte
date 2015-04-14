/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hearc.ig.ta.testmain.temp;

import ch.hearc.ig.ta.business.Virement;
import ch.hearc.ig.ta.utilities.FakeData;

/**
 *
 * @author jeremy.wermeill
 */
public class Main {

  
  public static void main(String[] args){
    
    FakeData fakeData = new FakeData();
    
    for(Virement virement : fakeData.getVirementList()){
      System.out.println("\n");
    
       System.out.println("nom client débité : " + virement.getNomClientDebit());
       System.out.println("numéro client débité : " + virement.getNoCptDebit());
       System.out.println("nom client crédité : " + virement.getNomClientCredit());
       System.out.println("numéro client crédité " + virement.getNoCptCredit());
       System.out.println("montant transféré : " + virement.getMontant());
       System.out.println("date du transfert : " + virement.getDateVirement());
    }
  
  }
  
}
