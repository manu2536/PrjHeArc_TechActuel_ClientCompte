<%-- 
    Document   : virement
    Created on : 10 avr. 2015, 02:56:37
    Author     : Fabien Maître
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Page de virement</h1>


<!-- !!!!!!!!!!!!!!!!!!!!!!!  TRANSFERT DE COMPTE A COMPTE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->

<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          Transfert de compte à compte
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
        <div class="container">
          <div class="col-md-12">
            <form class="form-horizontal" method="get" action="BankController">
              <legend>Transfert de ${Client.nom} ${Client.prenom}</legend>
              <input type="hidden" name="action" value="dotransfertCompteACompte">
              <input type="hidden" name="ClientId" value="${Client.identifiant}">
              <!-- ------------DEBIT --------------- -->
              <div class="form-group">
                <label class="col-md-2 control-label" for="selectCompte">Compte à débiter</label>
                <div class="col-md-4">
                  <select id="selectbasic" name="compteDebit" class="form-control">
                    <c:forEach var="Compte" items="${Client.listeCompte}">
                      <option value="${Compte.identifiant}">${Compte.identifiant} ${Compte.numero} ${Compte.nom} </option>
                    </c:forEach>
                  </select>
                </div>
              </div>
              <!-- ------------CREDIT --------------- -->
              <div class="form-group">
                <label class="col-md-2 control-label" for="selectCompte">Compte à créditer</label>
                <div class="col-md-4">
                  <select id="selectbasic" name="compteCredit" class="form-control">
                    <c:forEach var="Compte" items="${Client.listeCompte}">
                      <option value="${Compte.identifiant}">${Compte.identifiant} ${Compte.numero} ${Compte.nom} </option>
                    </c:forEach>
                  </select>
                </div>
              </div>
              <!-- ------------MONTANT --------------- -->
              <div class="form-group">
                <label class="col-md-2 control-label" for="montant">Montant [CHF]</label>  
                <div class="col-md-4">
                  <input id="textinput" name="montant" type="text" placeholder="1000" class="form-control input-md"> 
                </div>
              </div>
              <!-- ------------Bouton transférer --------------- -->
              <div class="form-group">
                <label class="col-md-2 control-label" for="SEND"></label>
                <div class="col-md-4">
                  <button id="singlebutton" name="SEND" class="btn btn-primary">TRANSFERER</button>
                </div>
              </div>
            </form>  
          </div>
        </div>
      </div>
    </div>
  </div>
              <!-- !!!!!!!!!!!!!!!!!!!!!!!  VIREMENT !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingTwo">
      <h4 class="panel-title">
        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
          Virement
        </a>
      </h4>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
      <div class="panel-body">
        <div class="container">
        <div class="col-md-12">
          <form class="form-horizontal" method="get" action="BankController">
            <legend>Virement de ${Client.nom} ${Client.prenom}</legend>
            <input type="hidden" name="action" value="virementCACompteEtranger">
            <input type="hidden" name="ClientIdVirement" value="${Client.identifiant}">
            <!-- ------------DEBIT --------------- -->
            <div class="form-group">
              <label class="col-md-2 control-label" for="selectCompte">Compte à débiter</label>
              <div class="col-md-4">
                <select id="selectbasic" name="compteDebitVirement" class="form-control">
                  <c:forEach var="Compte" items="${Client.listeCompte}">
                    <option value="${Compte.identifiant}">${Compte.identifiant} ${Compte.numero} ${Compte.nom} </option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <!-- ------------CREDIT --------------- -->
            <div class="form-group">
              <label class="col-md-2 control-label" for="selectCompte">Compte à Créditer</label>  
              <div class="col-md-4">
                <input id="textinput" name="creditVirement" type="text" placeholder="Entrez un numéro de comtpe" class="form-control input-md"> 
              </div>
            </div>
            <!-- ------------MONTANT --------------- -->
            <div class="form-group">
              <label class="col-md-2 control-label" for="montant">Montant [CHF]</label>  
              <div class="col-md-4">
                <input id="textinput" name="montantVirement" type="text" placeholder="1000" class="form-control input-md"> 
              </div>
            </div>

            <!-- ------------Bouton Virement --------------- -->
            <div class="form-group">
              <label class="col-md-2 control-label" for="SEND"></label>
              <div class="col-md-4">
                <button id="singlebutton" name="SEND" class="btn btn-primary">VIRER</button>
              </div>
            </div>
		</form>
        </div>
      </div>
        
      </div>
    </div>
  </div>
</div>