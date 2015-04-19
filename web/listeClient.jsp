<%-- 
    Document   : listeClient
    Created on : 8 avr. 2015, 22:20:49
    Author     : emmanuel.rondez
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
  <%-- Sans recherche --%>
  <c:when test="${empty searchedValue}">
    <h1>Liste de tous les clients</h1>
  </c:when>

  <%-- Avec recherche --%>
  <c:otherwise>
    <h1>Liste des clients correspondants à "${searchedValue}"</h1>
  </c:otherwise>
</c:choose>

<table class="table table-hover">
  <thead>
    <tr>
      <th>Nom / Prénom</th>
      <th>Adresse</th>
      <th>Opérations</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="customer" items="${ListCustomers}">
      <tr>
        <td>${customer.nom} ${customer.prenom}</td>
        <td>${customer.adresse} ${customer.ville}</td>
        <td>
          <a href="BankController?action=afficherClient&id=${customer.identifiant}" class="btn btn-info btn-mini" title="Afficher"><span class="glyphicon glyphicon-eye-open"></span></a>
          <a href="BankController?action=updateClient&id=${customer.identifiant}" class="btn btn-info btn-mini" title="Modifier"><span class="glyphicon glyphicon-pencil"></span></a>
          <button class="btn btn-info btn-mini" title="Supprimer" data-toggle="modal" data-target="#deleteConfirm" data-nomclient="${customer.prenom} ${customer.nom}" data-idclient="${customer.identifiant}"><span class="glyphicon glyphicon-trash"></span></button>
          <a href="BankController?action=virement&id=${customer.identifiant}" class="btn btn-info btn-mini">Virement</a>
          <a href="BankController?action=depot&id=${customer.identifiant}" class="btn btn-info btn-mini">Depôt</a>
          <a href="BankController?action=retrait&id=${customer.identifiant}" class="btn btn-info btn-mini">Retrait</a>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
    
    
<!-- Modal confirmation suppression client -->
<div class="modal fade" id="deleteConfirm" tabindex="-1" role="dialog" aria-labelledby="Confirmation de suppression" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Fermer"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Confirmation de suppression</h4>
      </div>
      <div class="modal-body">
        La suppression du client implique également la suppression de tous les comptes lui étant rattachés. 
        <br>
        Voulez-vous vraiment supprimer <b><span class="nomClient">ce client</span></b> et l'ensemble de ses comptes?
      </div>
      <div class="modal-footer">
        <form action="BankController?action=doDeleteClient" method="post">
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
     var nomClient = button.data('nomclient'); // Extract info from data-nomClient attribute
     var idClient = button.data('idclient'); 
     var modal = $(this);
     
     //Renseigne le nom du client sélectionné
     modal.find('.nomClient').text(nomClient);
     //Renseigne l'id du client sélectionné
     modal.find('#id').val(idClient);
   });
 });
</script>