<%-- 
    Document   : listeClient
    Created on : 8 avr. 2015, 22:20:49
    Author     : emmanuel.rondez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Liste des clients</h1>

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
          <a href="BankController?action=afficherClient&id=${customer.identifiant}" class="btn btn-info btn-mini"><i class="icon-white icon-eye-open"></i>Voir</a>
          <a href="BankController?action=depot&id=${customer.identifiant}" class="btn btn-info btn-mini"><i class="icon-white icon-eye-open"></i>Dep�t</a>
          <a href="BankController?action=retrait&id=${customer.identifiant}" class="btn btn-info btn-mini"><i class="icon-white icon-eye-open"></i>Retrait</a>
          <a href="BankController?action=virement&id=${customer.identifiant}" class="btn btn-info btn-mini"><i class="icon-white icon-eye-open"></i>Transfert</a>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>
