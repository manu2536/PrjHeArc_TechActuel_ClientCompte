<%-- 
    Document   : deleteCompte
    Created on : 20 avr. 2015, 19:45:41
    Author     : Fabien Maître
--%>

<!-- Modal confirmation suppression compte -->
<div class="modal fade" id="deleteCompteConfirm" tabindex="-1" role="dialog" aria-labelledby="Confirmation de suppression" aria-hidden="true">
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
          <input type="submit" id="btnConfirmDelete" class="btn btn-danger btn-primary" value="Supprimer"/>
        </form>
      </div>
    </div>
  </div>
</div>
 
<script>
 jQuery(document).ready(function($) {
   //Ouverture confirmation suppression
   $('#deleteCompteConfirm').on('show.bs.modal', function (event) {
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