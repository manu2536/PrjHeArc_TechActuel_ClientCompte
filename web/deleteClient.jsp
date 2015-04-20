<%-- 
    Document   : deleteClient
    Created on : 20 avr. 2015, 19:36:41
    Author     : Fabien Maître
--%>

<!-- Modal confirmation suppression client -->
<div class="modal fade" id="deleteClientConfirm" tabindex="-1" role="dialog" aria-labelledby="Confirmation de suppression" aria-hidden="true">
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
   $('#deleteClientConfirm').on('show.bs.modal', function (event) {
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