<%-- 
    Document   : detailClient
    Created on : 10 avr. 2015, 21:23:06
    Author     : emmanuel.rondez
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
  <h1>Details client</h1>
  <fieldset>
    <legend>${Client.prenom} ${Client.nom}  <a href="BankController?action=updateClient&id=${Client.identifiant}" class="btn btn-info btn-mini" title="Modifier"><span class="glyphicon glyphicon-pencil"></span></a></legend>
    Adresse : ${Client.adresse}<br/>
    Ville : ${Client.ville}<br/>

  </fieldset>
  <br>
  <br>
  <legend>Liste des comptes</legend>
  <c:choose>
    <%-- Aucun compte --%>
    <c:when test="${empty Client.listeCompte}">
      Aucun compte ouvert
    </c:when>

    <%-- Avec recherche --%>
    <c:otherwise>
      <div> <!-- Affichage compte -->
        <table class="table table-hover">
          <thead>
            <tr>
              <th>Nom</th>
              <th>Taux</th>
              <th>Solde</th>
              <th>Opérations</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="account" items="${Client.listeCompte}">
              <tr>
                <td>${account.nom}</td>
                <td><fmt:formatNumber pattern="0.00" value="${account.taux}"/> %</td>
                <td>CHF <fmt:formatNumber pattern="0.00" value="${account.solde}"/></td>
                <td>
                  <a href="BankController?action=updateAccount&id=${account.identifiant}" class="btn btn-info btn-mini" title="Modifier"><span class="glyphicon glyphicon-pencil"></span></a>
                  <button class="btn btn-info btn-mini" title="Supprimer" data-toggle="modal" data-target="#deleteConfirm" data-idcompte="${account.identifiant}" data-nomcompte="${account.nom}"><span class="glyphicon glyphicon-trash"></span></button>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </c:otherwise>
  </c:choose>
  <br>
  <br>
  <%@include file="ajouterCompte.jsp"%>
</div>


<!-- Modal confirmation suppression compte -->
<div class="modal fade" id="deleteConfirm" tabindex="-1" role="dialog" aria-labelledby="Confirmation de suppression" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Fermer"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Confirmation de suppression</h4>
      </div>
      <div class="modal-body">
        Voulez-vous vraiment supprimer le compte <b><span class="idCompte"></span> - <span class="nomCompte"></span></b>?
      </div>
      <div class="modal-footer">
        <form action="BankController?action=doDeleteCompte" method="post">
          <input type="hidden" id="id" name="id" value="" />
          <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
          <input type="submit" id="btnConfirmDelete" class="btn btn-primary" value="Supprimer"/>
        </form>
      </div>
    </div>
  </div>
</div>
 
 
<script>
 jQuery(document).ready(function($) {
   //Ouverture confirmation suppression
   $('#deleteConfirm').on('show.bs.modal', function (event) {
     var button = $(event.relatedTarget); // Button that triggered the modal
     var idCompte = button.data('idcompte'); //data-idcompte
     var nomCompte = button.data('nomcompte');
     var modal = $(this);
     
     //Renseigne l'id et le nom du compte sélectionné
     modal.find('.idCompte').text(idCompte);
     modal.find('.nomCompte').text(nomCompte);
     //Renseigne l'id du compte sélectionné
     modal.find('#id').val(idCompte);
   });
 });
</script>
