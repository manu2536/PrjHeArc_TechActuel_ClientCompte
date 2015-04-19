<%-- 
    Document   : detailClient
    Created on : 10 avr. 2015, 21:23:06
    Author     : emmanuel.rondez
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container">
  <h1>Details client</h1>
  <fieldset>
    
      <legend>${Client.prenom} ${Client.nom}  <a href="BankController?action=updateClient&id=${Client.identifiant}" class="glyphicon glyphicon-pencil btn btn-info btn-mini" >
        </a></legend>
      Adresse : ${Client.adresse}<br/>
      Ville : ${Client.ville}<br/>
      
     
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
                <a href="BankController?action=updateAccount&id=${account.identifiant}" class="glyphicon glyphicon-pencil btn btn-info btn-mini" >
                  <i class="icon-white icon-eye-open"></i></a>
                <a href="BankController?action=deleteCompte&id=${account.identifiant}" class="glyphicon glyphicon-trash btn btn-info btn-mini" >
                 <i class="icon-white icon-eye-open"></i></a>
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
