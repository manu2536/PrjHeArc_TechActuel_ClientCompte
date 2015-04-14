<%-- 
    Document   : virement
    Created on : 10 avr. 2015, 02:56:37
    Author     : Fabien Maître
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Bienvenue sur la page de virement</h1>


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
        
        <form id="transfertCompteAcompte" name="transfertCompteAcompte" method="post" action="BankController">
          <input type="hidden" name="action" value="transfertCAC">

          <table>
            <tr>
            
              <td width="150px"><b>Compte à débiter : </b></td>
              <td><b>Compte à créditer : </b></td>
              <td><b>Montant :</b></td>
            </tr>
            <tr>
              <td><select name="nom" size="1">

                  <c:forEach var="client" items="${ClientVirement}">
                    <OPTION>${client.listeCompte}</OPTION>

                  </c:forEach>
                </select></td>
              <td>
                <select name="nom" size="1">

                  <c:forEach var="compte" items="${ClientVirement.listeCompte}">
                    <OPTION>${compte.nom}</OPTION>

                  </c:forEach>
                </select>
              </td>
              <td><input type="text" name="montant"></td>


            </tr>

          </table>

          <p>

          </p>
          <p>

          </p>
          <p>

          </p>
          <button class="btn btn-primary" type="submit"><i class="icon-white icon-plus"></i> Valider la transaction</button>
          <button class="btn btn-success" type="reset"><i class="icon-white icon-refresh"></i> Vider le formulaire</button>




        </form>  
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