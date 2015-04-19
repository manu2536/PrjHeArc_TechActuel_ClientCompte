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
          <div class="col-md-12">
            <h1>Ajouter un compte</h1>
            <form class="form-horizontal" method="post" action="BankController">
              <input type="hidden" name="action" value="doAddCompte">
              <input type="hidden" name="idClient" value="${Client.identifiant}">
              <legend>Pour ${Client.prenom} ${Client.nom}</legend>
              <div class="form-group">
                <label class="col-md-2 control-label" for="nom">Nom</label>
                <div class="col-md-4">
                  <input id="nom" name="nom" type="text" placeholder="Nom du compte" required class="form-control input-md"> 
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-2 control-label" for="solde">Solde</label>  
                <div class="col-md-4">
                  <div class="input-group">
                    <span class="input-group-addon">CHF</span>
                    <input id="solde" name="solde" type="text" placeholder="Solde du compte" required pattern="^[0-9]*.[0-9][05]$" class="form-control input-md">
                  </div>
                </div>
              </div>

              <div class="form-group">
                <label class="col-md-2 control-label" for="taux">Taux</label>  
                <div class="col-md-4">
                  <div class="input-group">
                    <input id="taux" name="taux" type="text" placeholder="Taux" required pattern="^[0-9]*.[0-9][0-9]$" class="form-control input-md">
                    <span class="input-group-addon">%</span>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                  <button id="SEND" name="SEND" class="btn btn-primary">Ajouter</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>