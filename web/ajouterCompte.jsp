<%-- 
    Document   : ajouterCompte
    Created on : 11 déc. 2012, 13:36:02
    Author     : christop.francill
--%>

<%@page import="ch.hearc.ig.ta.utilities.WebUtilities"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<%--
<form id="form1" name="form1" method="post" action="addCompte">
  <input type="hidden" name="clientId" id="clientId" value="<%= id%>"/>
  <p>
    <label for="nom">Nom</label>
    <input type="text" name="nom" id="nom" />
  </p>
  <p>
    <label for="solde">Solde</label>
    <input type="text" name="solde" id="solde" />
  </p>
  <p>
    <label for="taux">Taux</label>
    <input type="text" name="taux" id="taux" />
  </p>
  <p>
    <button class="btn btn-primary" type="submit"><i class="icon-white icon-plus"></i> Ajouter</button>
    <button class="btn btn-success" type="reset"><i class="icon-white icon-refresh"></i> Vider le formulaire</button>
    <a href="afficherClient?id=<%= id%>" class="btn btn-inverse"><i class="icon-white icon-share-alt"></i> Retour au client</a>
  </p>
</form>

--%>

<table>
  <tr>
    <td>
      <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
        <div class="panel panel-default">
          <div class="panel-heading" role="tab" id="headingOne">
            <h4 class="panel-title">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                Ajouter un compte
              </a>
            </h4>
          </div>
          <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
            <div class="panel-body">
              <form id="form1" name="form1" method="post" action="BankController">
                <input type="hidden" name="action" value="addCompte">

                <table>
                  <tr>
                    <td><label for="nom">Nom</label></td>
                    <td><input type="text" name="nom" id="nom" /></td>
                  </tr>
                  <tr>
                    <td><label for="solde">Solde</label></td>
                    <td><input type="text" name="solde" id="solde" /></td>
                  </tr>
                   <tr>
                    <td><label for="taux">Taux</label></td>
                    <td><input type="text" name="taux" id="taux" /></td>
                  </tr>
                    
                    
                  
                </table>
                <button class="btn btn-primary" type="submit"><i class="icon-white icon-plus"></i> Ajouter</button>
                <button class="btn btn-success" type="reset"><i class="icon-white icon-refresh"></i> Vider le formulaire</button>
                <!--<a href="index" class="btn btn-inverse"><i class="icon-white icon-share-alt"></i> Retour à la liste</a>-->
              
              </form>
            </div>
          </div>
        </div>
      </div>
    </td>
    <td></td>
  </tr>
</table>



