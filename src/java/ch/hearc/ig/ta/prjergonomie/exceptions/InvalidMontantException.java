/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.ta.prjergonomie.exceptions;

/**
 *
 * @author mitch
 */
public class InvalidMontantException extends Exception {
    
    public InvalidMontantException() {
        super();
    }
    
    public InvalidMontantException(String msg) {
        super(msg);
    }
    
    public InvalidMontantException(Throwable t) {
        super(t);
    }
    
    public InvalidMontantException(String msg, Throwable t) {
        super(msg, t);
    }
    
}
