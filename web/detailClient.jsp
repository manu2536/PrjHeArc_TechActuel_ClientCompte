<%-- 
    Document   : detailClient
    Created on : 10 avr. 2015, 21:23:06
    Author     : emmanuel.rondez
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<h1>Details client</h1>

<div>
  <fieldset>
    <legend>${Client.nom} ${Client.prenom}</legend>
    ${Client.adresse}<br/>
    ${Client.ville}<br/>
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
            <a href="BankController?action=afficherCompte&id=${account.identifiant}" class="btn btn-info btn-mini"><i class="icon-white icon-eye-open"></i>Voir</a>
          </td>
        </tr>
      </c:forEach>
    </table>  
  </div>
</div>

