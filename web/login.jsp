<%-- 
    Document   : login
    Created on : 15 avr. 2015, 11:32:43
    Author     : Fabien Maître
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ch.hearc.ig.ta.utilities.AlertMessage"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%
  //Message d'erreur
  List<AlertMessage> alertMessages = null;
  if (session.getAttribute("alertMessages") != null) {
    alertMessages = (ArrayList<AlertMessage>) session.getAttribute("alertMessages");
  }

%>


<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Login</title>

    <!-- Bootstrap core CSS -->
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/custom.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
    
    <!-- Modal -->
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true" data-backdrop="static">
      <div class="modal-dialog">
        <form class="form-signin" role="form" method="post" action="BankController?action=dologin">
          <div class="modal-content">
            <div class="modal-header">
              <h2 class="modal-title">Connexion à MyBank</h2>
            </div>
            <div class="modal-body">
                <div class="login-page">
                <%--Si message d'erreur, l'afficher --%>
                <c:if test="${not empty alertMessages}">
                  <c:forEach var="alertMessage" items="${alertMessages}">
                    <div class="alert alert-${alertMessage.getType()} alert-dismissible fade in" role="alert">
                      <b>${alertMessage.getTitre()}</b> 
                      <c:if test="${not empty alertMessage.getCode()}">Erreur ${alertMessage.getCode()} : </c:if>
                      ${alertMessage.getMessage()}
                      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    </div>
                  </c:forEach>
                  <%--Vide la session quand affiché --%>
                  <% session.removeAttribute("alertMessages");%>
                </c:if>

                <input type="text" class="form-control" placeholder="Nom d'utilisateur" aria-describedby="username" id="username" name="username" pattern="^[a-zA-Z-\s]*" autofocus required>
                <input type="password" class="form-control" placeholder="Mot de passe" aria-describedby="password" id="password" name="password" required>
                  
              </div> <!-- /container -->
            </div>
            <div class="modal-footer">
              <button type="submit" class="btn btn-primary">Connexion</button>
            </div>
          </div>
        </form>
      </div>
    </div>
    
    

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src=".//assets/js/ie10-viewport-bug-workaround.js"></script>
    
    <script>
      jQuery(document).ready(function($) {
        $('#loginModal').on('shown.bs.modal', function () {
          $('#username').focus()
        });
        $('#loginModal').modal('show');
      });
      
    </script>
  </body>
</html>
