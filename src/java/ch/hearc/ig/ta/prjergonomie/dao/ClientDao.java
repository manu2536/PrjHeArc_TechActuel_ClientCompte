/*
 * Projet de groupe
 */
package ch.hearc.ig.ta.prjergonomie.dao;

import ch.hearc.ig.ta.prjergonomie.dbfactory.OracleConnections;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ch.hearc.ig.ta.prjergonomie.business.Client;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author christop.francill
 */
public class ClientDao {

  public static long create(Client cli) {
    Connection c = null;
    OraclePreparedStatement pstmt = null;
    ResultSet rs = null;

    long rId = -1;

    try {
      c = OracleConnections.getConnection();

      StringBuilder sql = new StringBuilder("insert into client(nom,prenom,adresse,ville) values (?,?,?,?) returning numero into ?");
      pstmt = (OraclePreparedStatement) c.prepareStatement(sql.toString());

      pstmt.setString(1, cli.getNom());
      pstmt.setString(2, cli.getPrenom());
      pstmt.setString(3, cli.getAdresse());
      pstmt.setString(4, cli.getVille());
      pstmt.registerReturnParameter(5, OracleTypes.NUMBER);
      pstmt.executeUpdate();
      c.commit();

      rs = pstmt.getReturnResultSet();
      while (rs.next()) {
        rId = rs.getLong(1);
      }
    } catch (SQLException ex) {
      System.out.println("Error INSERT: " + ex.getMessage());
    } finally {
      try {
        pstmt.close();
        c.close();
      } catch (SQLException ex) {
        System.out.println("Error INSERT CLOSE: " + ex.getMessage());
      }
    }
    System.out.println(rId);
    return rId;
  }

  public static ArrayList<Client> researchAll() {
    ArrayList<Client> listCli = new ArrayList<Client>();

    Connection cnx = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      cnx = OracleConnections.getConnection();

      //String sql = "select numero, nom, solde, taux from compte where numero_client=" + String.valueOf(client_numero);
      StringBuilder sql = new StringBuilder("select numero, nom, prenom, adresse, ville from client");
      stmt = cnx.createStatement();

      rs = stmt.executeQuery(sql.toString());

      while (rs.next()) {
        Client c = new Client();
        c.setIdentifiant(rs.getInt("numero"));
        c.setNom(rs.getString("nom"));
        c.setPrenom(rs.getString("prenom"));
        c.setAdresse(rs.getString("adresse"));
        c.setVille(rs.getString("ville"));
        listCli.add(c);
      }
      return listCli;
    } catch (SQLException ex) {
      System.out.println("Error SELECT CONNECTION: " + ex.getMessage());
      return null;
    } finally {
      try {
        rs.close();
        stmt.close();
        cnx.close();
      } catch (SQLException ex) {
        System.out.println("Error SELECT SQL: " + ex.getMessage());
      }

    }
  }

  public static ArrayList<Client> researchFullText(String text) {
    ArrayList<Client> listCli = new ArrayList<Client>();
    boolean and = false;
    Connection cnx = null;
    Statement stmt = null;
    ResultSet rs = null;
    String[] motsCle = text.split(" ");
    StringBuilder sql = new StringBuilder("select numero, nom, prenom, adresse, ville from client");

    sql.append(" WHERE")
            .append(whereClause("nom", motsCle))
            .append(" OR ")
            .append(whereClause("prenom", motsCle))
            .append(" OR ")
            .append(whereClause("adresse", motsCle))
            .append(" OR ")
            .append(whereClause("ville", motsCle));

    try {
      cnx = OracleConnections.getConnection();
      stmt = cnx.createStatement();

      rs = stmt.executeQuery(sql.toString());

      while (rs.next()) {
        Client c = new Client();
        c.setIdentifiant(rs.getInt("numero"));
        c.setNom(rs.getString("nom"));
        c.setPrenom(rs.getString("prenom"));
        c.setAdresse(rs.getString("adresse"));
        c.setVille(rs.getString("ville"));
        listCli.add(c);
      }
      return listCli;
    } catch (SQLException ex) {
      System.out.println("Error SELECT CONNECTION: " + ex.getMessage());
      return null;
    } finally {
      try {
        rs.close();
        stmt.close();
        cnx.close();
      } catch (SQLException ex) {
        System.out.println("Error SELECT SQL: " + ex.getMessage());
      }

    }

  }

  private static StringBuilder whereClause(String col, String[] motsCle) {
    StringBuilder where = new StringBuilder();
    for (String mot : motsCle) {
      if (where.length() != 0) {
        where.append(" OR");
      }
      where.append(" UPPER(")
              .append(col)
              .append(") LIKE '%' || UPPER('")
              .append(mot)
              .append("')|| '%'");
    }
    return where;
  }

  public static ArrayList<Client> research(Client cli) {

    ArrayList<Client> listCli = new ArrayList<Client>();

    boolean and = false;

    Connection cnx = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      cnx = OracleConnections.getConnection();

      //String sql = "select numero, nom, solde, taux from compte where numero_client=" + String.valueOf(client_numero);
      StringBuilder sql = new StringBuilder("select numero, nom, prenom, adresse, ville from client");

      if (!cli.isNull()) {
        sql.append(" where ");
        if (cli.getIdentifiant() != -1) {
          sql.append("numero = '");
          sql.append(cli.getIdentifiant());
          sql.append("'");
          and = true;
        }
        if (cli.getNom() != null) {
          if (and) {
            sql.append(" and ");
          }
          sql.append("nom = '");
          sql.append(cli.getNom());
          sql.append("'");
          and = true;
        }
        if (cli.getPrenom() != null) {
          if (and) {
            sql.append(" and ");
          }
          sql.append("prenom = '");
          sql.append(cli.getPrenom());
          sql.append("'");
          and = true;
        }
        if (cli.getAdresse() != null) {
          if (and) {
            sql.append(" and ");
          }
          sql.append("adresse = '");
          sql.append(cli.getAdresse());
          sql.append("'");
          and = true;
        }
        if (cli.getVille() != null) {
          if (and) {
            sql.append(" and ");
          }
          sql.append("ville = '");
          sql.append(cli.getVille());
          sql.append("'");
        }
      }

      stmt = cnx.createStatement();

      rs = stmt.executeQuery(sql.toString());

      while (rs.next()) {
        Client c = new Client();
        c.setIdentifiant(rs.getInt("numero"));
        c.setNom(rs.getString("nom"));
        c.setPrenom(rs.getString("prenom"));
        c.setAdresse(rs.getString("adresse"));
        c.setVille(rs.getString("ville"));
        listCli.add(c);
      }
      return listCli;
    } catch (SQLException ex) {
      System.out.println("Error SELECT CONNECTION: " + ex.getMessage());
      return null;
    } finally {
      try {
        rs.close();
        stmt.close();
        cnx.close();
      } catch (SQLException ex) {
        System.out.println("Error SELECT SQL: " + ex.getMessage());
      }

    }
  }

  public static void update(Client cli) {
    Connection cnx = null;
    PreparedStatement pstmt = null;

    try {
      cnx = OracleConnections.getConnection();

      StringBuilder sql = new StringBuilder("UPDATE client SET NOM = ?, PRENOM = ?, ADRESSE = ?, VILLE = ? WHERE numero = ?");
      pstmt = (OraclePreparedStatement) cnx.prepareStatement(sql.toString());

      pstmt.setString(1, cli.getNom());
      pstmt.setString(2, cli.getPrenom());
      pstmt.setString(3, cli.getAdresse());
      pstmt.setString(4, cli.getVille());
      pstmt.setLong(5, cli.getIdentifiant());
      pstmt.executeUpdate();
      cnx.commit();
    } catch (SQLException ex) {
      System.out.println("Error UPDATE SET: " + ex.getMessage());
    } finally {
      try {
        pstmt.close();
        cnx.close();
      } catch (SQLException ex) {
        System.out.println("Error UPDATE CLOSE: " + ex.getMessage());
      }
    }
  }

  public static void delete(Client cli) {
    Connection cnx = null;
    PreparedStatement pstmt = null;

    try {
      
      CompteDao.deleteAllComptes(cli.getIdentifiant());
      cnx = OracleConnections.getConnection();

      StringBuilder sql = new StringBuilder("DELETE FROM client WHERE numero = ?");
      pstmt = (OraclePreparedStatement) cnx.prepareStatement(sql.toString());

      pstmt.setLong(1, cli.getIdentifiant());
      pstmt.executeUpdate();
      cnx.commit();
    } catch (SQLException ex) {
      System.out.println("Error DELETE SQL: " + ex.getMessage());
    } finally {
      try {
        pstmt.close();
        cnx.close();
      } catch (SQLException ex) {
        System.out.println("Error DELETE CLOSE: " + ex.getMessage());
      }
    }
  }

  public static void loadAccounts(Client c){
    c.setListeCompte(CompteDao.research(c.getIdentifiant()));
  }
  
  public static Client searchClientByIdCompte(int idCompte){

     Client client = null;
    Connection cnx = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      cnx = OracleConnections.getConnection();

      //String sql = "select numero, nom, solde, taux from compte where numero_client=" + String.valueOf(client_numero);
      StringBuilder sql = new StringBuilder();
      sql.append("select cli.numero as numCli, cli.nom as nomCli, cli.prenom as prenomCli, cli.adresse as adresseCli, cli.ville as villeCli from compte com")
              .append(" inner join client cli on cli.numero = com.numero_client ")
              .append("where com.numero = ")
              .append(idCompte);
      stmt = cnx.createStatement();
      rs = stmt.executeQuery(sql.toString());

      while (rs.next()) {
        client = new Client();
        client.setIdentifiant(rs.getInt("numCli"));
        client.setNom(rs.getString("nomCli"));
        client.setPrenom(rs.getString("prenomCli"));
        client.setAdresse(rs.getString("adresseCli"));
        client.setVille(rs.getString("villeCli"));
      }
      return client;
    } catch (SQLException ex) {
      System.out.println("Error SELECT CONNECTION: " + ex.getMessage());
      return null;
    } finally {
      try {
        rs.close();
        stmt.close();
        cnx.close();
      } catch (SQLException ex) {
        System.out.println("Error SELECT SQL: " + ex.getMessage());
      }

    }
    
    
  }
}
