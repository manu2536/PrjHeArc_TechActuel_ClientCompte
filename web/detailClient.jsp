<%-- 
    Document   : detailClient
    Created on : 10 avr. 2015, 21:23:06
    Author     : emmanuel.rondez
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container">
  <h1>Details client</h1>
  
  <!--<form class="navbar-form navbar-right" name="formSearch" method="post" action="BankController?action=updateClient&id=${Client.identifiant}">
    <div class="input-group">

      <span class="input-group-btn">
        
      </span>
    </div>
  </form> -->
 
    <fieldset>
      <form  name="editClient" method="post" action="BankController?action=updateClient&id=${Client.identifiant}">
      <legend>${Client.nom} ${Client.prenom} <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-pencil" aria-hidden="false"></span></button></legend>
      ${Client.adresse}<br/>
      ${Client.ville}<br/>
      </form>
    </fieldset>
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
      <%@include file="ajouterCompte.jsp"%>
    </div>
</div>
