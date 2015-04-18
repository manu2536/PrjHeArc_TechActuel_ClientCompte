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
        <div class="container">
          <h1>Ajouter un compte</h1>
          <div class="col-md-12">
            <form class="form-horizontal" method="get" action="BankController">
              <legend>Ajouter un compte pour ${Client.nom} ${Client.prenom}</legend>
              <input type="hidden" name="action" value="addCompte">
              <input type="hidden" name="ClientId" value="${Client.identifiant}">
              <div class="form-group">
                <label class="col-md-2 control-label" for="selectCompte">Nom</label>
                <div class="col-md-4">
                  <input id="textinput" name="nom" type="text" placeholder="Nom du compte" class="form-control input-md"> 
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-2 control-label" for="montant">Solde</label>  
                <div class="col-md-4">
                  <input id="textinput" name="solde" type="text" placeholder="Solde du compte" class="form-control input-md"> 
                </div>
              </div>

              <div class="form-group">
                <label class="col-md-2 control-label" for="montant">Taux</label>  
                <div class="col-md-4">
                  <input id="textinput" name="taux" type="text" placeholder="Taux" class="form-control input-md"> 
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-2 control-label" for="SEND"></label>
                <div class="col-md-4">
                  <button id="singlebutton" name="SEND" class="btn btn-primary">AJOUTER </button>
                </div>
              </div>
              <div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>