<%-- 
    Document   : listeClient
    Created on : 8 avr. 2015, 22:20:49
    Author     : emmanuel.rondez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>List des clients</h1>

<div> <!-- DIV Form Search -->
  <form name="formSearch" method="get" action="BankController">
    <input type="hidden" name="action" value="listClient"/>
    <input type="text" name="recherche"/>
    <button type="submit">Search</button>
  </form>
</div> <!-- DIV Form Search -->
<div> <!-- MAIN -->
  <table class="table table-hover" style="width: 100%;">
    <tr>
      <th> Nom </th>
      <th> Adresse </th>
      <th> Operation </th>
    </tr>
    <c:forEach var="customer" items="${ListCustomers}">
      <tr>
        <td>${customer.nom} ${customer.prenom}</td>
        <td>${customer.adresse} ${customer.ville}</td>
        <td>
          <a href="afficherClient?id=${customer.identifiant}" class="btn btn-info btn-mini"><i class="icon-white icon-eye-open"></i>Voir</a>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>