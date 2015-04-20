/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.ta.prjergonomie.exceptions;

/**
 *
 * @author emmanuel.rondez
 */
public class MetierException extends Exception {

  public MetierException() {
  }

  public MetierException(String message) {
    super(message);
  }

  public MetierException(String message, Throwable cause) {
    super(message, cause);
  }

  public MetierException(Throwable cause) {
    super(cause);
  }

  public MetierException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
  
}
