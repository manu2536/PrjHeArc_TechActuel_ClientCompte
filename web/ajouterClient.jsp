<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          Ajouter un client
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
        <div class="container">
          <div class="col-md-12">
            <form class="form-horizontal" method="post" action="BankController">
              <legend>Ajouter un client</legend>
              <input type="hidden" name="action" value="doAddClient">
              <div class="form-group">
                <label class="col-md-2 control-label" for="nom">Nom</label>
                <div class="col-md-4">
                  <input id="nom" name="nom" type="text" placeholder="Nom" class="form-control input-md" required> 
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-2 control-label" for="prenom">Prénom</label>  
                <div class="col-md-4">
                  <input id="prenom" name="prenom" type="text" placeholder="Prénom" class="form-control input-md" required> 
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-2 control-label" for="adresse">Adresse</label>  
                <div class="col-md-4">
                  <input id="adresse" name="adresse" type="text" placeholder="Adresse" class="form-control input-md" required> 
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-2 control-label" for="ville">Ville</label>  
                <div class="col-md-4">
                  <input id="ville" name="ville" type="text" placeholder="Ville" class="form-control input-md" required> 
                </div>
              </div>
              <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                  <button id="SEND" name="SEND" class="btn btn-primary">Ajouter</button>
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