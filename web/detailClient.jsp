<%-- 
    Document   : detailClient
    Created on : 10 avr. 2015, 21:23:06
    Author     : emmanuel.rondez
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
  <h1>Details client</h1>
  <fieldset>
    <legend>${Client.prenom} ${Client.nom} 
      <a href="BankController?action=updateClient&id=${Client.identifiant}" class="btn btn-info btn-mini" title="Modifier"><span class="glyphicon glyphicon-pencil"></span></a>
      <button class="btn btn-info btn-mini" title="Supprimer" data-toggle="modal" data-target="#deleteClientConfirm" data-nomclient="${Client.prenom} ${Client.nom}" data-idclient="${Client.identifiant}"><span class="glyphicon glyphicon-trash"></span></button>
    </legend>
    
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

    <%-- Un ou plusieurs compte --%>
    <c:otherwise>
      <div> <!-- Affichage compte -->
        <table class="table table-hover">
          <thead>
            <tr>
              <th>Nom</th>
              <th>Taux</th>
              <th>Solde</th>
              <th>Opérations</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="account" items="${Client.listeCompte}">
              <tr>
                <td>${account.nom}</td>
                <td><fmt:formatNumber pattern="0.00" value="${account.taux}"/> %</td>
                <td>CHF <fmt:formatNumber pattern="0.00" value="${account.solde}"/></td>
                <td>
                  <a href="BankController?action=updateAccount&id=${account.identifiant}" class="btn btn-info btn-mini" title="Modifier"><span class="glyphicon glyphicon-pencil"></span></a>
                  <button class="btn btn-info btn-mini" title="Supprimer" data-toggle="modal" data-target="#deleteCompteConfirm" data-idcompte="${account.identifiant}" data-nomcompte="${account.nom}"><span class="glyphicon glyphicon-trash"></span></button>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </c:otherwise>
  </c:choose>
  <br>
  <br>
  <%@include file="ajouterCompte.jsp"%>
</div>

<%@include file="deleteClient.jsp"%>
<%@include file="deleteCompte.jsp"%>
