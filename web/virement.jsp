<%-- 
    Document   : virement
    Created on : 10 avr. 2015, 02:56:37
    Author     : Fabien Maître
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Page de virement</h1>


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
              <input type="hidden" name="action" value="transfertCAC">
              <input type="hidden" name="ClientId" value="${Client.identifiant}">
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
              <div class="form-group">
                <label class="col-md-2 control-label" for="montant">Montant [CHF]</label>  
                <div class="col-md-4">
                  <input id="textinput" name="montant" type="text" placeholder="1000" class="form-control input-md"> 
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-2 control-label" for="SEND">Transférer</label>
                <div class="col-md-4">
                  <button id="singlebutton" name="SEND" class="btn btn-primary">TRANSFERER</button>
                </div>
              </div>
            </form>  
          </div>
        </div>
      </div>
    </div>
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
          Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
        </div>
      </div>
    </div>
  </div>