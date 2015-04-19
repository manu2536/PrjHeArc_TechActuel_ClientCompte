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
      
        <!--<a href="BankController?action=updateClient&id=${Client.identifiant}" class="glyphicon glyphicon-pencil btn btn-info btn-mini" ><i class="icon-white icon-eye-open"></i></a>-->
      <legend>${Client.nom} ${Client.prenom} <span> <a href="BankController?action=updateClient&id=${Client.identifiant}" class="glyphicon glyphicon-pencil btn btn-info btn-mini" ></a></span></legend>
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
              <!--<span><a href="BankController?action=afficherCompte&id=${account.identifiant}" class="btn btn-info btn-mini"><i class="glyphicon glyphicon-eye-open"></i></a></span>-->
            </td>
            <td>
              <span><a href="BankController?action=updateAccount&id=${account.identifiant}" class="glyphicon glyphicon-pencil btn btn-info btn-mini" ><i class="icon-white icon-eye-open"></i></a></span>
              <!--<form class="navbar-form navbar-right" name="formSearch" method="post" action="BankController?action=updateAcccount&id=${account.identifiant}">
            
                <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-pencil" aria-hidden="false"></span></button>
             
          </form>-->
            </td>
          </tr>
        </c:forEach>
      </table>
      <%@include file="ajouterCompte.jsp"%>
    </div>
</div>
