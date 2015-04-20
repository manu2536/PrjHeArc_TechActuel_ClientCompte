<%-- 
    Document   : updateClient
    Created on : 18 avr. 2015, 16:14:33
    Author     : colin.schaffne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
  <h1>Modifier le compte</h1>
  <form class="form-horizontal" method="post" action="BankController">
    <legend>${Compte.identifiant} - ${Compte.nom}</legend>
    <input type="hidden" name="action" value="doUpdateCompte">
    <input type="hidden" name="id" value="${Compte.identifiant}">
    <div class="form-group">
      <label class="col-md-2 control-label" for="nom">Nom</label>  
      <div class="col-md-4">
        <input id="nom" name="nom" type="text" value="${Compte.nom}" class="form-control input-md" required >
      </div>
    </div>
    <div class="form-group">
      <label class="col-md-2 control-label" for="taux">Taux</label>  
      <div class="col-md-4">
        <div class="input-group">
          <input id="taux" name="taux" type="text" value="${Compte.taux}" class="form-control input-md" required>
          <span class="input-group-addon">%</span>
        </div>
      </div>
    </div>
    <div class="form-group">
      <label class="col-md-2 control-label" for="solde">Solde</label>  
      <div class="col-md-4">
        <div class="input-group">
          <span class="input-group-addon">CHF</span>
          <input id="solde" name="solde" type="text" value="${Compte.solde}" class="form-control input-md" pattern="^[0-9]*.[0-9][05]$" required>
        </div>
      </div>
    </div>
    <div class="form-group">
      <div class="col-md-offset-2 col-md-10">
        <button id="SEND" name="SEND" class="btn btn-primary">Modifier</button>
        <a class="btn btn-primary" href="BankController?action=afficherClient">Annuler</a>
      </div>
    </div>
  </form>
</div>