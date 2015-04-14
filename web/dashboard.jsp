<%-- 
    Document   : dashboard
    Created on : 10 avr. 2015, 02:55:12
    Author     : Fabien Maître
--%>

<h1>Bienvenue sur le dashboard</h1>
Cette page sert de page d'accueil

<table>
  <tr>
    <td>
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
              <form id="form1" name="form1" method="post" action="BankController">
                <input type="hidden" name="action" value="addClient">
                      
                <table>
                  <tr>
                    <td width="100px"><label for="nom">Nom</label></td>
                    <td><input type="text" name="nom" id="nom"/></td>
                  </tr>
                  <tr>
                    <td><label for="prenom">Prénom</label></td>
                    <td><input type="text" name="prenom" id="prenom" /></td>
                  </tr>
                  <tr>
                    <td><label for="adresse">Adresse</label></td>
                    <td><input type="text" name="adresse" id="adresse" /></td>
                  </tr>
                  <tr>
                    <td><label for="ville">Ville</label></td>
                    <td><input type="text" name="ville" id="ville" /></td>
                  </tr>
                </table>
                <button class="btn btn-primary" type="submit"><i class="icon-white icon-plus"></i> Ajouter</button>
                <button class="btn btn-success" type="reset"><i class="icon-white icon-refresh"></i> Vider le formulaire</button>
                <!--<a href="index" class="btn btn-inverse"><i class="icon-white icon-share-alt"></i> Retour à la liste</a>-->
                </p>
              </form>
            </div>
          </div>
        </div>
      </div>
    </td>
    <td></td>
  </tr>
</table>

