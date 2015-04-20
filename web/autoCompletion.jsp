<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="css/jquery-ui.css">
  <script>
  $(function() {
    var availableTags = [
   <c:forEach var="client" items="${listClients}"> 
     "${client.nom} ${client.prenom}",
   </c:forEach>
   
    ];
    $( "#tags" ).autocomplete({
      source: availableTags
    });
  });
  </script>