<%-- 
    Document   : dashboard
    Created on : 10 avr. 2015, 02:55:12
    Author     : Fabien Maître
--%>

<h1>Bienvenue sur le dashboard</h1>
Cette page sert de page d'accueil
<script type="text/javascript" src="assets/canvasjs/canvasjs.min.js"></script>

<%@include file="ajouterClient.jsp"%>
<%--<%@include file="chartStat.jsp"%>--%>
<%@include file="derniersVirements.jsp"%>
<div id="chartContainer" style="width: 400px; height: 300px;float:left;"></div> 
<%@include file="chartUserStat.jsp"%>
<div id="chartContainer2" style="width: 400px; height: 300px;"></div>
<%@include file="chartnbCPOuverts.jsp"%>

