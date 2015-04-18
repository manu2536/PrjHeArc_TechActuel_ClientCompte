/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.ta.exceptions;

/**
 *
 * @author jeremy.wermeill
 */
public class AccountDaoException extends Exception {
    
    public AccountDaoException() {
        super();
    }
    
    public AccountDaoException(String msg) {
        super(msg);
    }
    
    public AccountDaoException(Throwable t) {
        super(t);
    }
    
    public AccountDaoException(String msg, Throwable t) {
        super(msg, t);
    }
    
}
