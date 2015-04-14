<%-- 
    Document   : retrait
    Created on : 14 avr. 2015, 12:18:26
    Author     : Fabien Maître
--%>


<div class="container">
  <h1>Page de retrait</h1>
  <div class="col-md-12">
    <form class="form-horizontal" method="post" action="BankController">
      <legend>Déposer par ${Client.nom} ${Client.prenom}</legend>
      <input type="hidden" name="action" value="doDepot">
      <input type="hidden" name="ClientId" value="${Client.identifiant}">
          <div class="form-group">
            <label class="col-md-2 control-label" for="selectCompte">Compte de retrait</label>
            <div class="col-md-4">
              <select id="selectbasic" name="selectCompte" class="form-control">
                <c:forEach var="Compte" items="${Client.listeCompte}">
                  <option value="${Compte.identifiant}"> ${Compte.numero} ${Compte.nom} </option>
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
            <label class="col-md-2 control-label" for="SEND">RETRAIT</label>
            <div class="col-md-4">
              <button id="singlebutton" name="SEND" class="btn btn-primary">RETRAIT</button>
            </div>
          </div>
        
      <div>

    </form>
  </div>
</div>