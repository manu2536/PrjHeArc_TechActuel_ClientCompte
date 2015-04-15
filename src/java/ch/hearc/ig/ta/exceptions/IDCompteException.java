/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.ta.exceptions;

/**
 *
 * @author emmanuel.rondez
 */
public class IDCompteException extends Exception{

  public IDCompteException() {
  }
  
  public IDCompteException(String message) {
    super(message);
  }

  public IDCompteException(String message, Throwable cause) {
    super(message, cause);
  }

  public IDCompteException(Throwable cause) {
    super(cause);
  }
  
}
