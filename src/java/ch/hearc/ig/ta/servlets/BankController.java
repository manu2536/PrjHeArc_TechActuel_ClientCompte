package ch.hearc.ig.ta.servlets;

import ch.hearc.ig.ta.business.Client;
import ch.hearc.ig.ta.business.Compte;
import ch.hearc.ig.ta.business.Virement;
import ch.hearc.ig.ta.dao.CompteDao;
import ch.hearc.ig.ta.exceptions.MetierException;
import ch.hearc.ig.ta.services.GamificationService;
import ch.hearc.ig.ta.utilities.Level;
import ch.hearc.ig.ta.services.ServicesImpl;
import ch.hearc.ig.ta.utilities.AlertMessage;
import ch.hearc.ig.ta.utilities.FakeData;
import ch.hearc.ig.ta.utilities.authentification.User;
import ch.hearc.ig.ta.utilities.authentification.Users;
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
    // Définition de l'URL de destination
    // - Pour un forward, mettre le nom du fichier, par ex : login.jsp
    // - Pour une redirection, utiliser de préférence le frontController avec une action, par ex : BankController?action=login
    String URLRedirection = "index.jsp";

    //Forward ou redirect
    String forwardOrRedirect = "forward";

    //Action par défaut
    String action = "login";
    
    //Récupération de l'action
    if (request.getParameter("action") != null) {
      action = request.getParameter("action");
    }
    
    //Liste contenant des messages d'erreur
    List<AlertMessage> alertMessages = new ArrayList<>();
    if (request.getSession().getAttribute("alertMessages") != null) {
      alertMessages = (List<AlertMessage>) request.getSession().getAttribute("alertMessages");
    }

    //Si utilisateur est connecté
    Boolean authentified = false;
    if (request.getSession().getAttribute("authUser") != null) {
      if (Users.userExists((String) request.getSession().getAttribute("authUser"))) {
        authentified = true;
      }
    }

    //Si pas authentifié, page login (sauf pour traitement page login)
    if (!authentified) {
      if (!action.equals("login") && !action.equals("dologin")) {
        alertMessages.add(new AlertMessage("warning", "Connexion requise", "Vous devez vous connecter pour accéder à cette page"));
        action = "login";
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

      case "login":
        forwardOrRedirect = "redirect";
        if (!authentified) {
          //Page cible
          URLRedirection = "login.jsp";

        } else {
          URLRedirection = "BankController?action=dashboard";
        }

        break;

      //Lors de la connexion
      case "dologin":
        URLRedirection = "BankController?action=login";
        forwardOrRedirect = "redirect";
        if (request.getParameter("username") != null && request.getParameter("password") != null) {
          if (Users.verifyUser(request.getParameter("username"), request.getParameter("password"))) {
            //On sauve le login en session
            request.getSession().setAttribute("authUser", request.getParameter("username"));
            alertMessages.add(new AlertMessage("info", "Bienvenue", "dans l'application MyBank"));
            //chargement des données à afficher dans le dashboard
            FakeData fakedata = new FakeData();
            List<Virement> listVirement = fakedata.getVirementList();
            request.getSession().setAttribute("listVirement", listVirement);
            //chargement de la liste des users 
            GamificationService gamificationService = new GamificationService();
            List<User> users = gamificationService.getUsersWithScores();
            request.getSession().setAttribute("listUsers", users);
            URLRedirection = "BankController?action=dashboard";
          } else {
            alertMessages.add(new AlertMessage("danger", "Erreur de connexion", "Nom d'utilisateur ou mot de passe incorrect"));
          }
        }
        break;

      case "logout":
        request.getSession().invalidate();
        alertMessages.add(new AlertMessage("success", "Déconnexion", "effectuée avec succès"));
        URLRedirection = "login.jsp";
        forwardOrRedirect = "redirect";
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

      case "deselectClient":
        request.getSession().removeAttribute("SelectedClient");
        forwardOrRedirect = "redirect";
        URLRedirection = "BankController?action=dashboard";
        break;

      case "afficherClient":
        Client cliAfficherClient = getClientbyRequestIDorSession(request);
        if (cliAfficherClient != null) {
          request.setAttribute("Client", cliAfficherClient);
          //Page cible
          request.getSession().setAttribute("currentPage", "client");
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

      case "doAddClient":
        forwardOrRedirect = "redirect";
        URLRedirection = "BankController?action=dashboard";
        
        if (request.getParameter("nom") != null && request.getParameter("prenom") != null && request.getParameter("adresse") != null && request.getParameter("ville") != null) {
          try {
            ServicesImpl services = new ServicesImpl();
            int id1 = services.addClient(request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("adresse"), request.getParameter("ville"));
            Client cli1 = services.searchClientById(String.valueOf(id1));
            alertMessages.add(new AlertMessage("success", "Succès", "Client ajouté"));

            //Ajout des points
            User authUser = services.getUser((String) request.getSession().getAttribute("authUser"));
            new GamificationService().incrementScore(10, authUser);
            
            //Mise à jour session client sélectionné
            request.getSession().setAttribute("SelectedClient", cli1);

            //Redirection page client
            URLRedirection = "BankController?action=afficherClient";

          } catch (MetierException ex) {
            alertMessages.add(new AlertMessage("warning", "Erreur d'ajout du client", ex.getMessage()));
          }
          
        }else{
          alertMessages.add(new AlertMessage("danger", "Paramètre manquant", "Veuillez renseigner tous les paramètres requis"));
        }
        
        break;
        
      case "updateClient":
        Client clientModifier = getClientbyRequestIDorSession(request);
        request.setAttribute("Client", clientModifier);
        //Page cible
        request.getSession().setAttribute("currentPage", "client");
        request.setAttribute("targetPage", "updateClient.jsp");
        request.setAttribute("targetPageTitle", "Details client");
        break;

      case "doUpdateClient":
        forwardOrRedirect = "redirect";
        URLRedirection = "BankController?action=afficherClient";
        
        if (request.getParameter("id") != null && request.getParameter("nom") != null && request.getParameter("prenom") != null && request.getParameter("adresse") != null && request.getParameter("ville") != null) {
          Client clientAModif = new Client();
          clientAModif.setIdentifiant(new Integer(request.getParameter("id")));
          clientAModif.setNom(request.getParameter("nom"));
          clientAModif.setPrenom(request.getParameter("prenom"));
          clientAModif.setAdresse(request.getParameter("adresse"));
          clientAModif.setVille(request.getParameter("ville"));

          try {
            new ServicesImpl().updateClient(clientAModif);
            alertMessages.add(new AlertMessage("success", "Succès", "Client modifié"));
            //Mise à jour session client sélectionné
            request.getSession().setAttribute("SelectedClient", clientAModif);
            
          } catch (MetierException ex) {
            alertMessages.add(new AlertMessage("warning", "Attention", "Erreur de modification du client : " + ex));
          }

        } else {
          alertMessages.add(new AlertMessage("danger", "Paramètre manquant", "Veuillez renseigner tous les paramètres requis"));
        }
        break;

      case "doAddCompte":
        forwardOrRedirect = "redirect";
        URLRedirection = "BankController?action=afficherClient";
        
        Client cli = getClientbyRequestIDorSession(request);
        if (cli != null && request.getParameter("nom") != null && request.getParameter("solde") != null && request.getParameter("taux") != null) {
          int idClie = cli.getIdentifiant();

          try {
            ServicesImpl services = new ServicesImpl();
            services.addCompte(request.getParameter("nom"), request.getParameter("solde"), request.getParameter("taux"), idClie);
            alertMessages.add(new AlertMessage("success", "Succès", "Compte ajouté"));
            
            //Ajout des points
            User authUser = services.getUser((String) request.getSession().getAttribute("authUser"));
            new GamificationService().incrementScore(5, authUser);
            
            //Recharge liste comptes client session
            new ServicesImpl().loadAccounts(cli);
            request.getSession().setAttribute("SelectedClient", cli);
            
          } catch (MetierException ex) {
            alertMessages.add(new AlertMessage("warning", "Erreur d'ajout du compte", ex.getMessage()));
          }
          
        }else{
          alertMessages.add(new AlertMessage("danger", "Paramètre manquant", "Veuillez renseigner tous les paramètres requis"));
        }
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


      case "doTransfertCompteACompte":
        URLRedirection = "BankController?action=virement";
        forwardOrRedirect = "redirect";

        if (request.getParameter("compteDebit") != null && request.getParameter("compteCredit") != null && request.getParameter("montant") != null) {
          int idCompteDebit = (int) Integer.parseInt(request.getParameter("compteDebit"));
          int idCompteCredit = (int) Integer.parseInt(request.getParameter("compteCredit"));
          float montantTransfert = Float.parseFloat(request.getParameter("montant"));

          try {
            ServicesImpl services = new ServicesImpl();
            Virement virement = services.transfert(idCompteDebit, idCompteCredit, montantTransfert);
            
            //Ajout des points
            User authUser = services.getUser((String) request.getSession().getAttribute("authUser"));
            new GamificationService().incrementScore(3, authUser);
            
            //Ajout du virement à la liste
            if(virement != null){
              List<Virement> virements = services.addVirementToList((List<Virement>) request.getSession().getAttribute("listVirement"), virement);
              request.getSession().setAttribute("listVirement", virements);
            }
            
            alertMessages.add(new AlertMessage("success", "Succès", "Transfert de CHF " + montantTransfert + " effectué"));

          } catch (MetierException ex) {
            alertMessages.add(new AlertMessage("danger", "Erreur de transfert", ex.getMessage()));
          }
        } else {
          alertMessages.add(new AlertMessage("danger", "Paramètre manquant", "Veuillez renseigner tous les paramètres requis"));
        }
        break;

      case "doVirementCompteEtranger":
        URLRedirection = "BankController?action=virement";
        forwardOrRedirect = "redirect";

        if (request.getParameter("compteDebitVirement") != null && request.getParameter("creditVirement") != null && request.getParameter("montantVirement") != null) {
          int idCompteDebitVirement = (int) Integer.parseInt(request.getParameter("compteDebitVirement"));
          int idCompteCreditVirement = (int) Integer.parseInt(request.getParameter("creditVirement"));
          float montantVirement = Float.parseFloat(request.getParameter("montantVirement"));

          try {
            ServicesImpl services = new ServicesImpl();
            Virement virement = services.transfert(idCompteDebitVirement, idCompteCreditVirement, montantVirement);
            
            //Ajout des points
            User authUser = services.getUser((String) request.getSession().getAttribute("authUser"));
            new GamificationService().incrementScore(3, authUser);
            
            //Ajout du virement à la liste
            if(virement != null){
              List<Virement> virements = services.addVirementToList((List<Virement>) request.getSession().getAttribute("listVirement"), virement);
              request.getSession().setAttribute("listVirement", virements);
            }
            
            alertMessages.add(new AlertMessage("success", "Succès", "Virement de CHF " + montantVirement + " effectué"));

          } catch (MetierException ex) {
            alertMessages.add(new AlertMessage("danger", "Erreur de virement", ex.getMessage()));
          }

        } else {
          alertMessages.add(new AlertMessage("danger", "Paramètre manquant", "Veuillez renseigner tous les paramètres requis"));
        }
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
        URLRedirection = "BankController?action=depot";
        forwardOrRedirect = "redirect";
        
        if (request.getParameter("selectCompte") != null && request.getParameter("montant") != null) {
          int dIdCompte = (int) Integer.parseInt(request.getParameter("selectCompte"));
          float dMontant = Float.parseFloat(request.getParameter("montant"));
          
          try {
            new ServicesImpl().verser(dIdCompte, dMontant);
            alertMessages.add(new AlertMessage("success", "Succès", "Dépot de CHF " + dMontant + " effectué"));

          }catch(MetierException ex) {
            alertMessages.add(new AlertMessage("danger", "Erreur de dépôt", ex.getMessage()));
          }
          
        }else{
          alertMessages.add(new AlertMessage("danger", "Paramètre manquant", "Veuillez renseigner tous les paramètres requis"));
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
        URLRedirection = "BankController?action=retrait";
        forwardOrRedirect = "redirect";
        
        if (request.getParameter("selectCompte") != null && request.getParameter("montant") != null) {
          int RIdCompte = (int) Integer.parseInt(request.getParameter("selectCompte"));
          float RMontant = Float.parseFloat(request.getParameter("montant"));
          
          try {
            new ServicesImpl().retirer(RIdCompte, RMontant);
            alertMessages.add(new AlertMessage("success", "Succès", "Retrait de CHF " + RMontant + " effectué"));

          } catch (MetierException ex) {
            alertMessages.add(new AlertMessage("danger", "Erreur de retrait", ex.getMessage()));
          }
          
        }else{
          alertMessages.add(new AlertMessage("danger", "Paramètre manquant", "Veuillez renseigner tous les paramètres requis"));
        }
        
        break;

      case "profil":
        User user = new ServicesImpl().getUser((String) request.getSession().getAttribute("authUser"));
        request.setAttribute("User", user);
        request.setAttribute("UserLevel", new GamificationService().getLevel(user));
        request.setAttribute("Levels", Level.values());

        //Page cible
        request.getSession().setAttribute("currentPage", "profil");
        request.setAttribute("targetPage", "profil.jsp");
        request.setAttribute("targetPageTitle", "Profil");
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
    //Forward = garde les paramètres dans l'url
    if(forwardOrRedirect.equals("forward")) {
      request.getRequestDispatcher(URLRedirection).forward(request, response);

    //Redirect = recharge une nouvelle page en effaçant l'URL
    }else if(forwardOrRedirect.equals("redirect")) {
      response.sendRedirect(URLRedirection);
    }
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

  //Récupère le client en paramètre GET/POST ou de SESSION
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
