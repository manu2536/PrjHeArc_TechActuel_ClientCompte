<%-- 
    Document   : retrait
    Created on : 14 avr. 2015, 12:18:26
    Author     : Fabien Maître
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
  <h1>Retrait d'argent</h1>
  <form class="form-horizontal" method="post" action="BankController">
    <legend>Par ${Client.prenom} ${Client.nom}</legend>
    <input type="hidden" name="action" value="doRetrait"/>
    <div class="form-group">
      <label class="col-md-2 control-label" for="selectCompte">Compte de retrait</label>
      <div class="col-md-4">
        <select id="selectCompte" name="selectCompte" class="form-control" required>
          <c:forEach var="Compte" items="${Client.listeCompte}">
            <option value="${Compte.identifiant}">${Compte.identifiant} - ${Compte.nom}</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="col-md-2 control-label" for="montant">Montant</label>  
      <div class="col-md-4">
        <div class="input-group">
          <span class="input-group-addon">CHF</span>
          <input id="montant" name="montant" type="text" placeholder="123.45" pattern="^[0-9]*.[0-9][05]$" required class="form-control input-md"> 
        </div>
      </div>
    </div>
    <div class="form-group">
      <div class="col-md-offset-2 col-md-10">
        <button id="SEND" name="SEND" class="btn btn-primary">Retrait</button>
      </div>
    </div>
  </form
</div>