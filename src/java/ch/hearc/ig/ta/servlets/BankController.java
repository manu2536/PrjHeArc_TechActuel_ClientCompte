package ch.hearc.ig.ta.servlets;

import ch.hearc.ig.ta.business.Client;
import ch.hearc.ig.ta.exceptions.MetierException;
import ch.hearc.ig.ta.services.ServicesImpl;
import ch.hearc.ig.ta.utilities.AlertMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author emmanuel.rondez
 */
public class BankController extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    // Définition de l'URL de destination (Dans certain cas modifer en BankController
    String URLRedirection = "index.jsp";
    String action = "dashboard";
    //Liste contenant des messages d'erreur
    List<AlertMessage> alertMessages = new ArrayList<>();

    if (request.getAttribute("RedirectionAction") != null) {
      // Permet de rediriger la servlet sur elle même
      //(request.setParameter() --> n'existe pas
      action = (String) request.getAttribute("RedirectionAction");
      alertMessages.addAll((List<AlertMessage>) request.getSession().getAttribute("alertMessages"));
    } else {
      if (request.getParameter("action") != null) {
        action = request.getParameter("action");
      }
    }

    switch (action) {
      case "demo":
        //Demo de toutes les types de messages d'erreur
        alertMessages.add(new AlertMessage("success", "Succès", "L'opération s'est déroulée correctement"));
        alertMessages.add(new AlertMessage("info", "Information", "Juste pour info"));
        alertMessages.add(new AlertMessage("warning", "Attention", "Il pourrait y avoir un problème"));
        alertMessages.add(new AlertMessage("danger", "Erreur", "L'opération a échouée"));

        //Page cible
        request.getSession().setAttribute("currentPage", "demo"); //Utile uniquement si le lien est également dans le menu afin de le mettre en sélection
        request.setAttribute("targetPage", "demo.jsp");
        request.setAttribute("targetPageTitle", "Demo");

        break;

      case "listClient":
        List<Client> listeClients;
        ServicesImpl service = new ServicesImpl();
        //Si client recherché
        if (request.getParameter("recherche") != null) {
          String recherche = request.getParameter("recherche");
          request.getSession().setAttribute("searchedValue", recherche);
          listeClients = service.searchClientFullText(recherche);

          //Si aucune recherche
        } else {
          listeClients = service.getClientsAll();
        }

        request.setAttribute("ListCustomers", listeClients);

        //Page cible
        request.getSession().setAttribute("currentPage", "clients");
        request.setAttribute("targetPage", "listeClient.jsp");
        request.setAttribute("targetPageTitle", "Clients");
        break;

      case "virement":
        Client clVirement = getClientbyRequestIDorSession(request);
        if (clVirement != null) {
          request.setAttribute("Client", clVirement);
          //Page cible
          request.getSession().setAttribute("currentPage", "virement");
          request.setAttribute("targetPage", "virement.jsp");
          request.setAttribute("targetPageTitle", "Virement");
        } else {
          // Erreur Redirection page clients avec message d'erreur
          alertMessages.add(new AlertMessage("warning", "Attention", "Aucun client sélectionné"));

          request.getSession().setAttribute("currentPage", "clients");
          request.setAttribute("targetPage", "listeClient.jsp");
          request.setAttribute("targetPageTitle", "Clients");
        }

        break;

      case "addClient":
        
        try {
          int id1 = new ServicesImpl().addClient(request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("adresse"), request.getParameter("ville"));
          Client cli1 = new ServicesImpl().searchClientById(String.valueOf(id1));
          request.setAttribute("Client", cli1);

          alertMessages.add(new AlertMessage("success", "Succès", "Client ajouté"));
          
          request.getSession().setAttribute("currentPage", "clients");
          request.setAttribute("targetPage", "detailClient.jsp");
          request.setAttribute("targetPageTitle", "Details client");
        } catch (MetierException ex) {
          alertMessages.add(new AlertMessage("warning", "Attention", "Erreur ajout de client : " + ex));
          request.setAttribute("RedirectionAction", "afficherClient");
          URLRedirection = "BankController";
        } finally {

        }

        
        break;

      case "addCompte":

        
        Client cli = getClientbyRequestIDorSession(request);
        int idClie = cli.getIdentifiant();
        
        try {
          new ServicesImpl().addCompte(request.getParameter("nom"), request.getParameter("solde"), request.getParameter("taux"), idClie);
          
          alertMessages.add(new AlertMessage("success", "Succès", "Compte ajouté"));
          request.setAttribute("RedirectionAction", "afficherClient");
          URLRedirection = "BankController";
        } catch (MetierException ex) {
          alertMessages.add(new AlertMessage("warning", "Attention", "Erreur ajout de compte : " + ex));
          request.setAttribute("RedirectionAction", "afficherClient");
          URLRedirection = "BankController";
        } finally {

        }

       
        request.setAttribute("Client", cli);

        //Page cible
        request.getSession().setAttribute("currentPage", "clients");
        request.setAttribute("targetPage", "detailClient.jsp");
        request.setAttribute("targetPageTitle", "Details client");
        break;

      case "depot":
        Client clDepot = getClientbyRequestIDorSession(request);
        if (clDepot != null) {
          request.setAttribute("Client", clDepot);
          //Page cible
          request.getSession().setAttribute("currentPage", "depot");
          request.setAttribute("targetPage", "depot.jsp");
          request.setAttribute("targetPageTitle", "Dépôt");
        } else {
          // Erreur Redirection page clients avec message d'erreur
          alertMessages.add(new AlertMessage("warning", "Attention", "Aucun client sélectionné"));

          request.getSession().setAttribute("currentPage", "clients");
          request.setAttribute("targetPage", "listeClient.jsp");
          request.setAttribute("targetPageTitle", "Clients");
        }
        break;

      case "doDepot":
        if (request.getAttribute("selectCompte") == null) {
          // Message d'erreur 
        }
        int dIdCompte = (int) Integer.parseInt(request.getParameter("selectCompte"));
        float dMontant = Float.parseFloat(request.getParameter("montant"));
        try {
          new ServicesImpl().verser(dIdCompte, dMontant);
          // Ce passe bien..
          // Appelle le controleur pour affcher le client
          alertMessages.add(new AlertMessage("success", "Succès", "Dépot de " + dMontant + "CHF effectué"));
          request.setAttribute("RedirectionAction", "afficherClient");
          URLRedirection = "BankController";
        } catch (MetierException ex) {
          alertMessages.add(new AlertMessage("warning", "Attention", "Erreur Dépot: " + ex));
          request.setAttribute("RedirectionAction", "depot");
          URLRedirection = "BankController";
        } finally {

        }
        break;

      case "retrait":
        Client clRetrait = getClientbyRequestIDorSession(request);
        if (clRetrait != null) {
          request.setAttribute("Client", clRetrait);
          //Page cible
          request.getSession().setAttribute("currentPage", "retrait");
          request.setAttribute("targetPage", "retrait.jsp");
          request.setAttribute("targetPageTitle", "Retrait");
        } else {
          // Erreur Redirection page clients avec message d'erreur
          alertMessages.add(new AlertMessage("warning", "Attention", "Aucun client sélectionné"));
          request.getSession().setAttribute("currentPage", "clients");
          request.setAttribute("targetPage", "listeClient.jsp");
          request.setAttribute("targetPageTitle", "Clients");
        }
        break;

      case "doRetrait":
        if (request.getAttribute("selectCompte") == null) {
          // Message d'erreur 
        }
        int RIdCompte = (int) Integer.parseInt(request.getParameter("selectCompte"));
        float RMontant = Float.parseFloat(request.getParameter("montant"));
        try {
          new ServicesImpl().verser(RIdCompte, RMontant);
          // Ce passe bien..
          // Appelle le controleur pour affcher le client
          alertMessages.add(new AlertMessage("success", "Succès", "Retrait de " + RMontant + "CHF effectué"));
          request.setAttribute("RedirectionAction", "afficherClient");
          URLRedirection = "BankController";
        } catch (MetierException ex) {
          alertMessages.add(new AlertMessage("warning", "Attention", "Erreur Retrait: " + ex));
          request.setAttribute("RedirectionAction", "depot");
          URLRedirection = "BankController";
        } finally {

        }
        break;

      case "afficherClient":
        Client cliAfficherClient = getClientbyRequestIDorSession(request);
        if (cliAfficherClient != null) {
          request.setAttribute("Client", cliAfficherClient);
          //Page cible
          request.getSession().setAttribute("currentPage", "clients");
          request.setAttribute("targetPage", "detailClient.jsp");
          request.setAttribute("targetPageTitle", "Details client");
        } else {
          // Erreur Redirection page clients avec message d'erreur
          alertMessages.add(new AlertMessage("warning", "Attention", "Aucun client sélectionné"));
          request.getSession().setAttribute("currentPage", "clients");
          request.setAttribute("targetPage", "listeClient.jsp");
          request.setAttribute("targetPageTitle", "Clients");
        }
        break;

      case "transfertCompteACompte":
        Client clTransfert = getClientbyRequestIDorSession(request);
        if (clTransfert != null) {
          request.setAttribute("Client", clTransfert);
          //Page cible
          request.getSession().setAttribute("currentPage", "virement");
          request.setAttribute("targetPage", "virement.jsp");
          request.setAttribute("targetPageTitle", "Virement");
        } else {
          // Erreur Redirection page clients avec message d'erreur
          alertMessages.add(new AlertMessage("warning", "Attention", "Aucun client sélectionné"));
          request.getSession().setAttribute("currentPage", "clients");
          request.setAttribute("targetPage", "listeClient.jsp");
          request.setAttribute("targetPageTitle", "Clients");
        }
        break;

      case "dotransfertCompteACompte":
        if (request.getAttribute("selectCompte") == null) {
          // Message d'erreur 
        }
        int idCompteDebit = (int) Integer.parseInt(request.getParameter("compteDebit"));
        int idCompteCredi = (int) Integer.parseInt(request.getParameter("compteCredit"));
        float montantTransfert = Float.parseFloat(request.getParameter("montant"));
        try {
          new ServicesImpl().forTransfert(idCompteDebit, idCompteCredi, montantTransfert);
          // Ce passe bien..
          // Appelle le controleur pour affcher le client
          alertMessages.add(new AlertMessage("success", "Succès", "Transfert de " + montantTransfert + "CHF effectué"));
          request.setAttribute("RedirectionAction", "afficherClient");
          URLRedirection = "BankController";
        } catch (MetierException ex) {
          alertMessages.add(new AlertMessage("warning", "Attention", "Erreur transfert: " + ex));
          request.setAttribute("RedirectionAction", "virement");
          URLRedirection = "BankController";
        } finally {

        }

        break;

      case "virementCACompteEtranger":
        if (request.getAttribute("selectCompte") == null) {
          // Message d'erreur 
        }
        int idCompteDebitVirement = (int) Integer.parseInt(request.getParameter("compteDebitVirement"));
        int idCompteCreditVirement = (int) Integer.parseInt(request.getParameter("creditVirement"));
        float montantVirement = Float.parseFloat(request.getParameter("montantVirement"));
        try {
          new ServicesImpl().forTransfert(idCompteDebitVirement, idCompteCreditVirement, montantVirement);
          // Ce passe bien..
          // Appelle le controleur pour affcher le client
          alertMessages.add(new AlertMessage("success", "Succès", "Virement de " + montantVirement + "CHF effectué"));
          request.setAttribute("RedirectionAction", "afficherClient");
          URLRedirection = "BankController";
        } catch (MetierException ex) {
          alertMessages.add(new AlertMessage("warning", "Attention", "Erreur virement: " + ex));
          request.setAttribute("RedirectionAction", "virement");
          URLRedirection = "BankController";
        } finally {

        }

        break;

      //Erreur 404
      default:
        alertMessages.add(new AlertMessage("info", "404", "Page introuvable", "La page demandée est introuvable"));

      //Page par défaut
      case "dashboard":
        //Page cible
        request.getSession().setAttribute("currentPage", "accueil");
        request.setAttribute("targetPage", "dashboard.jsp");
        request.setAttribute("targetPageTitle", "Accueil");
        break;
    }
    //Affecte les mssg dans tous les cas
    request.getSession().setAttribute("alertMessages", alertMessages);
    //Redirection
    request.getRequestDispatcher(URLRedirection).forward(request, response);
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

  private Client getClientbyRequestIDorSession(HttpServletRequest request) {
    Client clDepot = null;
    String IdClient = null;
    if (request.getParameter("id") != null) {
      IdClient = request.getParameter("id");
    } else {
      if (request.getSession().getAttribute("SelectedClient") == null) {
        // Pad de client dans la session
        IdClient = null;

      } else {
        clDepot = (Client) request.getSession().getAttribute("SelectedClient");
        IdClient = clDepot.getIdentifiant().toString();
      }
    }
    if (IdClient != null) {
      // Rechargement du client (il a pu être modifié par quelqu'un d'autre....
      clDepot = new ServicesImpl().searchClientById(IdClient);
      new ServicesImpl().loadAccounts(clDepot);
    }
    request.getSession().setAttribute("SelectedClient", clDepot);
    return clDepot;
  }
}
