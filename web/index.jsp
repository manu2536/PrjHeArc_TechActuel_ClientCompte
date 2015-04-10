<%-- 
    Document   : index
    Created on : 10 avr. 2015, 02:17:33
    Author     : Fabien Maître
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="ch.hearc.ig.ta.utilities.AlertMessage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  //Message d'erreur
  List<AlertMessage> alertMessages = null;
  if(session.getAttribute("alertMessages") != null){
    alertMessages = (ArrayList<AlertMessage>) session.getAttribute("alertMessages");
  }
  
  

  //Page cible
  String targetPage = "dashboard.jsp";
  if(request.getParameter("targetPage") != null){
    targetPage = request.getParameter("targetPage");
  }
  String targetPageTitle = "Accueil";
  if(request.getParameter("targetPageTitle") != null){
    targetPageTitle = request.getParameter("targetPageTitle");
  }
%>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>${targetPageTitle}</title>

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
    <!-- Menu -->
    <%@include file="menu.jsp"%>
    
    <!-- Contenu -->
    <div class="container-fluid">
      <%--Si message d'erreur, l'afficher --%>
      <c:if test="${not empty alertMessages}">
        <div class="container alert">
          <c:forEach var="alertMessage" items="${alertMessages}">
            <div class="alert alert-${alertMessage.getType()} alert-dismissible fade in" role="alert">
              <b>${alertMessage.getTitre()}</b> 
              <c:if test="${not empty alertMessage.getCode()}">Erreur ${alertMessage.getCode()} : </c:if>
              ${alertMessage.getMessage()}
              <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
          </c:forEach>
        </div>
        <%--Vide la session quand affiché --%>
        <% session.removeAttribute("alertMessages"); %>
      </c:if>
      
      <!-- Page demandée -->
      <jsp:include page="${targetPage}" />
      
    </div><!-- /.container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src=".//assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>