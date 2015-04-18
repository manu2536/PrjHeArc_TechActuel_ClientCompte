<%-- 
    Document   : updateClient
    Created on : 18 avr. 2015, 16:14:33
    Author     : colin.schaffne
--%>

<%@page import="ch.hearc.ig.ta.business.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <form class="form-horizontal" method="get" action="BankController">
      <legend>Modifier ${Client.prenom} ${Client.nom}</legend>
      <input type="hidden" name="action" value="doUpdateClient">
      <input type="hidden" name="id" value="${Client.identifiant}">
      <div class="form-group">
        <label class="col-md-2 control-label" for="selectCompte">Nom</label>
        <div class="col-md-4">
          <input id="textinput" name="nom" type="text" value="${Client.nom}" class="form-control input-md">
          <!--<input id="textinput" name="nom" type="text" placeholder="${Client.nom}" class="form-control input-md">-->
        </div>
      </div>
      <div class="form-group">
        <label class="col-md-2 control-label" for="montant">Prénom</label>  
        <div class="col-md-4">
          <input id="textinput" name="prenom" type="text" value="${Client.prenom}" class="form-control input-md">
          <!--<input id="textinput" name="prenom" type="text" placeholder="${Client.prenom}" class="form-control input-md">--> 
        </div>
      </div>
      <div class="form-group">
        <label class="col-md-2 control-label" for="montant">Adresse</label>  
        <div class="col-md-4">
          <input id="textinput" name="adresse" type="text" value="${Client.adresse}" class="form-control input-md">
          <!--<input id="textinput" name="adresse" type="text" placeholder="${Client.adresse}" class="form-control input-md">--> 
        </div>
      </div>
      <div class="form-group">
        <label class="col-md-2 control-label" for="ville">Ville</label>  
        <div class="col-md-4">
          <input id="textinput" name="ville" type="text" value="${Client.ville}" class="form-control input-md">
          <!--<input id="textinput" name="ville" type="text" placeholder="${Client.ville}" class="form-control input-md">--> 
        </div>
      </div>
      <div class="form-group">
        <label class="col-md-2 control-label" for="SEND"></label>
        <div class="col-md-4">
          <button id="singlebutton" name="SEND" class="btn btn-primary">VALIDER</button>
        </div>
      </div>
      <div>
    </form>
  </body>
</html>
