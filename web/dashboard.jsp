<%-- 
    Document   : dashboard
    Created on : 10 avr. 2015, 02:55:12
    Author     : Fabien Maître
--%>

<center><h1>Bienvenue sur le dashboard</h1></center>
<script type="text/javascript" src="assets/canvasjs/canvasjs.min.js"></script>
<%--<%@include file="ajouterClient.jsp"%>--%>
<%--<%@include file="chartStat.jsp"%>--%>
<div class="center-block">
<%@include file="derniersVirements.jsp"%>
</div>
<div class="container">
  <div class="row">
    <div class="col-xs-6">
      <div class ="span4" id="chartContainer" style="width: 400px; height: 300px;"></div> 
      <%@include file="chartUserStat.jsp"%>
    </div>
    <div class="col-xs-6">
      <div class ="span5" id="chartContainer2" style="width: 400px; height: 300px;;"></div>
      <%@include file="chartnbCPOuverts.jsp"%>
    </div>
  </div>
</div>


