<%-- 
    Document   : updateClient
    Created on : 18 avr. 2015, 16:14:33
    Author     : colin.schaffne
--%>

<%@page import="ch.hearc.ig.ta.business.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
  <h1>Modifier client ${Client.prenom} ${Client.nom}</h1>
  <form class="form-horizontal" method="post" action="BankController">
    <input type="hidden" name="action" value="doUpdateClient">
    <input type="hidden" name="id" value="${Client.identifiant}">
    <div class="form-group">
      <label class="col-md-2 control-label" for="nom">Nom</label>
      <div class="col-md-4">
        <input id="nom" name="nom" type="text" value="${Client.nom}" class="form-control input-md"  placeholder="Nom" required class="form-control input-md">
      </div>
    </div>
    <div class="form-group">
      <label class="col-md-2 control-label" for="prenom">Prénom</label>  
      <div class="col-md-4">
        <input id="prenom" name="prenom" type="text" value="${Client.prenom}" class="form-control input-md" placeholder="Prénom" required class="form-control input-md">
      </div>
    </div>
    <div class="form-group">
      <label class="col-md-2 control-label" for="adresse">Adresse</label>  
      <div class="col-md-4">
        <input id="adresse" name="adresse" type="text" value="${Client.adresse}" class="form-control input-md" placeholder="Adresse" required class="form-control input-md">
      </div>
    </div>
    <div class="form-group">
      <label class="col-md-2 control-label" for="ville">Ville</label>  
      <div class="col-md-4">
        <input id="ville" name="ville" type="text" value="${Client.ville}" class="form-control input-md" placeholder="Ville" required class="form-control input-md">
      </div>
    </div>
    <div class="form-group">
      <div class="col-md-offset-2 col-md-10">
        <button id="SEND" name="SEND" class="btn btn-primary">Valider</button>
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
