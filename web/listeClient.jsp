<%-- 
    Document   : listeClient
    Created on : 8 avr. 2015, 22:20:49
    Author     : emmanuel.rondez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<c:choose>
  <%-- Sans recherche --%>
  <c:when test="${empty searchedValue}">
    <h1>Liste de tous les clients</h1>
  </c:when>

  <%-- Avec recherche --%>
  <c:otherwise>
    <h1>Liste des clients correspondants à "${searchedValue}"</h1>
  </c:otherwise>
</c:choose>


<div> <!-- MAIN -->
  <table class="table table-hover" style="width: 100%;">
    <tr>
      <th></th>
      <th> Nom </th>
      <th> Adresse </th>
      <th> Operation </th>
    </tr>
    <c:forEach var="customer" items="${ListCustomers}">
      <tr>
        <td>

          
        </td>
        <td>${customer.nom} ${customer.prenom}</td>
        <td>${customer.adresse} ${customer.ville}</td>
        <td>
          <a href="BankController?action=afficherClient&id=${customer.identifiant}" class="btn btn-info btn-mini"><i class="glyphicon glyphicon-eye-open"></i></a>
          <a href="BankController?action=updateClient&id=${customer.identifiant}" class="glyphicon glyphicon-pencil btn btn-info btn-mini" ><i class="icon-white icon-eye-open"></i></a>
          
          
          <!--<form class="navbar-form navbar-right" name="editClient" method="post" action="BankController?action=updateClient&id=${customer.identifiant}">
            
                <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-pencil" aria-hidden="false"></span></button>
             
          </form>-->
          
          <a href="BankController?action=virement&id=${customer.identifiant}" class="btn btn-info btn-mini"><i class="icon-white icon-eye-open"></i>Virement</a>
          <a href="BankController?action=depot&id=${customer.identifiant}" class="btn btn-info btn-mini"><i class="icon-white icon-eye-open"></i>Depôt</a>
          <a href="BankController?action=retrait&id=${customer.identifiant}" class="btn btn-info btn-mini"><i class="icon-white icon-eye-open"></i>Retrait</a>

          <!--<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>-->
        </td>
      </tr>
    </c:forEach>
  </table>
</div>
