<%-- 
    Document   : detailClient
    Created on : 10 avr. 2015, 21:23:06
    Author     : emmanuel.rondez
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container">
  <h1>Details client</h1>
  <fieldset>
    <form  name="editClient" method="post" action="BankController?action=updateClient&id=${Client.identifiant}">
      <legend>${Client.prenom} ${Client.nom} <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-pencil" aria-hidden="false"></span></button></legend>
      Adresse : ${Client.adresse}<br/>
      Ville : ${Client.ville}<br/>
    </form>
  </fieldset>
  <br>
  <br>
    
  
  <legend>Liste des comptes</legend>
  <c:choose>
    <%-- Aucun compte --%>
    <c:when test="${empty Client.listeCompte}">
      Aucun compte ouvert
    </c:when>

    <%-- Avec recherche --%>
    <c:otherwise>
      <div> <!-- Affichage compte -->
        <table>
          <tr>
            <th>Nom</th>
            <th>Taux</th>
            <th>Solde</th>
          </tr>

          <c:forEach var="account" items="${Client.listeCompte}">
            <tr>
              <td>${account.nom}</td>
              <td>${account.taux}</td>
              <td>${account.solde}</td>
              <td>
                <a href="BankController?action=afficherCompte&id=${account.identifiant}" class="btn btn-info btn-mini"><i class="glyphicon glyphicon-eye-open"></i></a>
              </td>
              <td>
                <form class="navbar-form navbar-right" name="formSearch" method="post" action="BankController?action=updateAcccount&id=${account.identifiant}">

                  <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-pencil" aria-hidden="false"></span></button>

                </form>
              </td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </c:otherwise>
  </c:choose>
      
  <br>
  <br>
  <%@include file="ajouterCompte.jsp"%>
</div>
