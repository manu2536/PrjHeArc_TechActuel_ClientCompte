/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hearc.ig.ta.services;

/**
 *
 * @author jeremy.wermeill
 */
public enum Level {
  Novice("Novice"),
  AccountManager("Account Manager"),
  TopAccountManager("Top Account Manager"),
  SuperAccountManager("Super Account Manager"),
  RampageAccountManager("Rampage Account Manager");
  
  private String name;
  
  Level(String name){
  this.name = name;
  }
  
  
}
