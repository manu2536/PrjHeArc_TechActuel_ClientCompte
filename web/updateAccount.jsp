<%-- 
    Document   : updateClient
    Created on : 18 avr. 2015, 16:14:33
    Author     : colin.schaffne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Modification d'un compte</title>
  </head>
  <body>
    <form class="form-horizontal" method="post" action="BankController">
      <legend>Modifier ${Compte.nom}</legend>
      <input type="hidden" name="action" value="doUpdateCompte">
      <input type="hidden" name="id" value="${Compte.identifiant}">
      <div class="form-group">
        <label class="col-md-2 control-label" for="montant">Nom</label>  
        <div class="col-md-4">
          <input id="textinput" name="nom" type="text" value="${Compte.nom}" class="form-control input-md" required class="form-control input-md">
        </div>
      </div>
      <div class="form-group">
        <label class="col-md-2 control-label" for="ville">Taux</label>  
        <div class="col-md-4">
          <input id="textinput" name="taux" type="text" value="${Compte.taux}" class="form-control input-md" required class="form-control input-md">
        </div>
      </div>
      <div class="form-group">
        <label class="col-md-2 control-label" for="montant">Solde</label>  
        <div class="col-md-4">
          <input id="textinput" name="solde" type="text" value="${Compte.solde}" pattern="^[0-9]*.[0-9][05]$" class="form-control input-md" required class="form-control input-md">
        </div>
      </div>
      <div class="form-group">
        <label class="col-md-2 control-label" for="SEND"></label>
        <div class="col-md-4">
          <button id="singlebutton" name="SEND" class="btn btn-primary">VALIDER</button>
        </div>
      </div>
    </form>
        <form class="form-horizontal" method="post" action="BankController">
          <input type="hidden" name="action" value="afficherClient">
          <div class="form-group">
        <label class="col-md-2 control-label" for="SEND"></label>
        <div class="col-md-4">
          <button id="singlebutton" name="SEND" class="btn btn-primary">ANNULER</button>
        </div>
      </div>
          
        </form>
  </body>
</html>
