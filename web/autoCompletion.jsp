<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

<script>
  jQuery(document).ready(function($) {
    var inputRecherche = $("#rechercheclient");
    
    var availableTags = [
      <c:forEach var="client" items="${listClients}"> 
        "${client.nom} ${client.prenom}",
      </c:forEach>
    ];

    inputRecherche.autocomplete({
      source: availableTags,
      select: function(e, ui) {
        inputRecherche.val(ui.item.value);
        $("#formSearch").submit();
      }
    });
  });
</script>