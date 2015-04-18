<%-- 
    Document   : virement
    Created on : 10 avr. 2015, 02:56:37
    Author     : Fabien Maître
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
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
              <form class="form-horizontal" method="post" action="BankController">
                <legend>Transfert de ${Client.nom} ${Client.prenom}</legend>
                <input type="hidden" name="action" value="dotransfertCompteACompte">
                
                <!-- ------------DEBIT --------------- -->
                <div class="form-group">
                  <label class="col-md-2 control-label" for="compteDebit">Compte à débiter</label>
                  <div class="col-md-4">
                    <select id="selectbasic" name="compteDebit" class="form-control">
                      <c:forEach var="Compte" items="${Client.listeCompte}">
                        <option value="${Compte.identifiant}">${Compte.identifiant} ${Compte.numero} ${Compte.nom}</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
                
                <!-- ------------CREDIT --------------- -->
                <div class="form-group">
                  <label class="col-md-2 control-label" for="compteCredit">Compte à créditer</label>
                  <div class="col-md-4">
                    <select id="selectbasic" name="compteCredit" class="form-control">
                      <c:forEach var="Compte" items="${Client.listeCompte}">
                        <option value="${Compte.identifiant}">${Compte.identifiant} ${Compte.numero} ${Compte.nom}</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
                
                <!-- ------------MONTANT --------------- -->
                <div class="form-group">
                  <label class="col-md-2 control-label" for="montant">Montant</label>  
                  <div class="col-md-4">
                    <div class="input-group">
                      <span class="input-group-addon">CHF</span>
                      <input id="textinput" name="montant" type="text" placeholder="123.45" pattern="^[0-9]*.[0-9][05]$" required class="form-control input-md"> 
                    </div>
                  </div>
                </div>
                <!-- ------------Bouton transférer --------------- -->
                <div class="form-group">
                  <div class="col-md-offset-2 col-md-10">
                    <button id="singlebutton" name="SEND" class="btn btn-primary">Transférer</button>
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
              <form class="form-horizontal" method="post" action="BankController">
                <legend>Virement de ${Client.nom} ${Client.prenom}</legend>
                <input type="hidden" name="action" value="virementCACompteEtranger">
                <input type="hidden" name="ClientIdVirement" value="${Client.identifiant}">
                <!-- ------------DEBIT --------------- -->
                <div class="form-group">
                  <label class="col-md-2 control-label" for="compteDebitVirement">Compte à débiter</label>
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
                  <label class="col-md-2 control-label" for="creditVirement">Compte à créditer</label>  
                  <div class="col-md-4">
                    <input id="textinput" name="creditVirement" type="text" placeholder="Entrez un numéro de comtpe"  pattern="^[0-9]*$" required class="form-control input-md"> 
                  </div>
                </div>
                <!-- ------------MONTANT --------------- -->
                <div class="form-group">
                  <label class="col-md-2 control-label" for="montantVirement">Montant</label>  
                  <div class="col-md-4">
                    <div class="input-group">
                      <span class="input-group-addon">CHF</span>
                      <input id="textinput" name="montantVirement" type="text" placeholder="123.45" pattern="^[0-9]*.[0-9][05]$" required class="form-control input-md"> 
                    </div>
                  </div>
                </div>

                <!-- ------------Bouton Virement --------------- -->
                <div class="form-group">
                  <div class="col-md-10 pull-right">
                    <button id="singlebutton" name="SEND" class="btn btn-primary">Virer</button>
                  </div>
                </div>
              </form>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</div>