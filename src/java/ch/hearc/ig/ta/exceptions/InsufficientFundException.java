/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.ta.exceptions;

/**
 *
 * @author jeremy.wermeill
 */
public class InsufficientFundException extends Exception {
    
    public InsufficientFundException() {
        super();
    }
    
    public InsufficientFundException(String msg) {
        super(msg);
    }
    
    public InsufficientFundException(Throwable t) {
        super(t);
    }
    
    public InsufficientFundException(String msg, Throwable t) {
        super(msg, t);
    }
    
}
