<%-- 
    Document   : derniersVirements
    Created on : 19 avr. 2015, 12:08:59
    Author     : jeremy.wermeill
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h3>Liste des 10 derniers virements effectués</h3>
<table class="table table-hover">
  <thead>
    <tr>
      <th>Client débité</th>
      <th>N° Compte débité</th>
      <th>Client crédité</th>
      <th>N° Compte crédité</th>
      <th>Montant</th>
      <th>Date du virement</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="virement" items="${listVirement}">
      <tr>
        <td>${virement.nomClientDebit}</td>
        <td>${virement.noCptDebit}</td>
        <td>${virement.nomClientCredit}</td>
        <td>${virement.noCptCredit}</td>
        <td>CHF <fmt:formatNumber pattern="0.00" value="${virement.montant}"/></td>
        <td>${virement.formatedDate}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>