/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hearc.ig.ta.testmain.temp;

import ch.hearc.ig.ta.business.Client;
import ch.hearc.ig.ta.utilities.FakeData;

/**
 *
 * @author jeremy.wermeill
 */
public class Main {

  
  public static void main(String[] args){
    
    FakeData fakeData = new FakeData();
    //parcourir la liste et afficher...
//    for(Virement virement : fakeData.getVirementList()){
//      System.out.println("\n");
//    
//       System.out.println("nom client débité : " + virement.getNomClientDebit());
//       System.out.println("numéro client débité : " + virement.getNoCptDebit());
//       System.out.println("nom client crédité : " + virement.getNomClientCredit());
//       System.out.println("numéro client crédité " + virement.getNoCptCredit());
//       System.out.println("montant transféré : " + virement.getMontant());
//       System.out.println("date du transfert : " + virement.getDateVirement());
//       System.out.println("date jour " + virement.getDay());
//       System.out.println("date mois " + virement.getMonth());
//       System.out.println("date année " + virement.getYear());
//    }
    
//   List<Virement> virements = fakeData.getVirementList();
//    Virement virement = new Virement();
//    virement.setMontant(1111111111111f);
//   if(virements.size() >= 10){
//     //on enlève le dixième élément
//     virements.remove(9);
//     if(virements.size() == 10){
//       System.out.println("oui..");
//     }
//     //on ajoute notre nouvel élément
//     virements.add(0, virement);
//     
//   }
//   
//   if(virements.size() == 10){
//     System.out.println("oui!! " + virements.get(0).getMontant());
//      
//   }
//    
  
//  //si un nouveau virement venait pointer le bout de son nez, il faudrait l'ajouter comme cela à la liste
//   Virement virement = new Virement(); 
//   fakeData.getVirementList().add(virement);
//   
//   
//   
//   //liste des clients avec leur date d'inscription
   for(Client client : fakeData.getClientsListWithInscriptionDate()){ 
     System.out.println("\n");
     System.out.println("nom du client : " + client.getNom());
     System.out.println("date d'inscription : " + client.getDateInscription());
   }
   
//    
//    GamificationService service = new GamificationService();
//    
//    service.getUsersWithScores();
//    
//    ArrayList<Vi
//    
//    for(Client client : fakeData.getClientsListWithInscriptionDate()){}
    
    
    
  }
  
}
