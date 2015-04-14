package ch.hearc.ig.ta.servlets;

import ch.hearc.ig.ta.business.Client;
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
    String action = "dashboard";
    if (request.getParameter("action") != null) {
      action = request.getParameter("action");
    }

    //Liste contenant des messages d'erreur
    List<AlertMessage> alertMessages = new ArrayList<>();

    switch (action) {
      case "demo":
        //Demo de toutes les types de messages d'erreur
        alertMessages.add(new AlertMessage("success", "Succès", "L'opération s'est déroulée correctement"));
        alertMessages.add(new AlertMessage("info", "Information", "Juste pour info"));
        alertMessages.add(new AlertMessage("warning", "Attention", "Il pourrait y avoir un problème"));
        alertMessages.add(new AlertMessage("danger", "Erreur", "L'opération a échouée"));
        request.getSession().setAttribute("alertMessages", alertMessages);

        //Page cible
        request.getSession().setAttribute("currentPage", "demo"); //Utile uniquement si le lien est également dans le menu afin de le mettre en sélection
        request.setAttribute("targetPage", "demo.jsp");
        request.setAttribute("targetPageTitle", "Demo");

        break;

      case "listClient":
        //Test : http://localhost:8080/crud/BankController?action=listClient&recherche=non
        String textRecherche = "non";
        if (request.getParameter("recherche") != null) {
          textRecherche = request.getParameter("recherche");
        }
        System.out.println("Recherche:" + textRecherche);
        request.setAttribute("ListCustomers", new ServicesImpl().searchClientFullText(textRecherche));

        //Page cible
        request.getSession().setAttribute("currentPage", "clients");
        request.setAttribute("targetPage", "listeClient.jsp");
        request.setAttribute("targetPageTitle", "Clients");
        break;

      case "virement":

        Client cliVirement = (Client) request.getSession().getAttribute("selectedClient");

        if (request.getSession().getAttribute("selectedClient") == null) {
          alertMessages.add(new AlertMessage("warning", "Attention", "Aucun client sélectionné"));
        }

        request.setAttribute("ClientVirement", cliVirement);
        //Page cible
        request.getSession().setAttribute("currentPage", "virement");
        request.setAttribute("targetPage", "virement.jsp");
        request.setAttribute("targetPageTitle", "Virement");
        break;

      case "addClient":
        ServicesImpl si = new ServicesImpl();
        int id1 = si.addClient(request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("adresse"), request.getParameter("ville"));

        Client cli1 = new ServicesImpl().searchClientById(String.valueOf(id1));
        request.setAttribute("Client", cli1);

        //Page cible
        request.getSession().setAttribute("currentPage", "clients");
        request.setAttribute("targetPage", "detailClient.jsp");
        request.setAttribute("targetPageTitle", "Details client");
        break;

      case "addCompte":

        ServicesImpl siAddCompte = new ServicesImpl();

        if (request.getSession().getAttribute("selectedClient") == null) {
          alertMessages.add(new AlertMessage("warning", "Attention", "Aucun client sélectionné"));
        }
        Client cli = (Client) request.getSession().getAttribute("selectedClient");
        int idClie = cli.getIdentifiant();
        siAddCompte.addCompte(request.getParameter("nom"), request.getParameter("solde"), request.getParameter("taux"), idClie);

        //Client cli = new ServicesImpl().searchClientById(String.valueOf(id1));
        request.setAttribute("Client", cli);

        //Page cible
        request.getSession().setAttribute("currentPage", "clients");
        request.setAttribute("targetPage", "detailClient.jsp");
        request.setAttribute("targetPageTitle", "Details client");
        break;

      case "depot":
        Client clDepot = getClientbyRequestIDorSession(request);
        if(clDepot !=null){
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

      case "retrait":
        Client clRetrait = getClientbyRequestIDorSession(request);
        if(clRetrait !=null){
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

      case "afficherClient":
        Client cliAfficherClient = getClientbyRequestIDorSession(request);
        if(cliAfficherClient !=null){ 
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

      case "transfertCAC":

        break;

      //Erreur 404
      default:
        alertMessages.add(new AlertMessage("info", "404", "Page introuvable", "La page demandée est introuvable"));
        request.getSession().setAttribute("alertMessages", alertMessages);

      //Page par défaut
      case "dashboard":
        //Page cible
        request.getSession().setAttribute("currentPage", "accueil");
        request.setAttribute("targetPage", "dashboard.jsp");
        request.setAttribute("targetPageTitle", "Accueil");
        break;
    }
    //Redirection
    request.getRequestDispatcher("index.jsp").forward(request, response);
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

  
private Client getClientbyRequestIDorSession(HttpServletRequest request){
    Client clDepot = null;
    if(request.getParameter("id") != null ){
        clDepot =  new ServicesImpl().searchClientById(request.getParameter("id"));
        request.getSession().setAttribute("SelectedClient", clDepot);
    } else {
        if(request.getSession().getAttribute("SelectedClient") == null ){
            // Pad de client dans la session
            clDepot = null;
            
        } else {
            clDepot = (Client) request.getSession().getAttribute("SelectedClient");
        }    
    }
    return clDepot;
}  
}
