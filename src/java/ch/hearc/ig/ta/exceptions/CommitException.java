/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.ta.exceptions;

/**
 *
 * @author jeremy.wermeill
 */
public class CommitException extends Exception {
    
    public CommitException() {
        super();
    }
    
    public CommitException(String msg) {
        super(msg);
    }
    
    public CommitException(Throwable t) {
        super(t);
    }
    
    public CommitException(String msg, Throwable t) {
        super(msg, t);
    }
    
}
