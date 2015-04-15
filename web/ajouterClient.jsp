<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          Ajouter un Client
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
        <div class="container">
          <div class="col-md-12">
            <form class="form-horizontal" method="get" action="BankController">
              <legend>Ajouter un client</legend>
              <input type="hidden" name="action" value="addClient">
              <div class="form-group">
                <label class="col-md-2 control-label" for="selectCompte">Nom</label>
                <div class="col-md-4">
                  <input id="textinput" name="nom" type="text" placeholder="Nom" class="form-control input-md"> 
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-2 control-label" for="montant">Prénom</label>  
                <div class="col-md-4">
                  <input id="textinput" name="prenom" type="text" placeholder="Prénom" class="form-control input-md"> 
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-2 control-label" for="montant">Adresse</label>  
                <div class="col-md-4">
                  <input id="textinput" name="adresse" type="text" placeholder="Adresse" class="form-control input-md"> 
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-2 control-label" for="ville">Ville</label>  
                <div class="col-md-4">
                  <input id="textinput" name="ville" type="text" placeholder="Ville" class="form-control input-md"> 
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-2 control-label" for="SEND"></label>
                <div class="col-md-4">
                  <button id="singlebutton" name="SEND" class="btn btn-primary">AJOUTER</button>
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