/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.ta.prjergonomie.dbfactory;

import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author christop.francill
 */
public class OracleConnections {
    private static OracleDataSource ods = null;
    
    private static final String USER = "prof_francillon";
    private static final String PASSWORD = "prof_francillon";
    private static final String HOST = "ne-ege-leto.ig.he-arc.ch";
    private static final String PORT = "1521";
    private static final String SID = "ens2";
    
    public static Connection getConnection() throws SQLException{
        if(ods == null){
            String url = "jdbc:oracle:thin:@" + HOST + ":" + PORT + ":" + SID;
            
            ods = new OracleDataSource();
            ods.setUser(USER);
            ods.setPassword(PASSWORD);
            ods.setURL(url);
        }
        
        Connection c = ods.getConnection();
        c.setAutoCommit(false);
        
        return c;
    }
}
