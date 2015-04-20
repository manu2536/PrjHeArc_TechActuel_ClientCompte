<%-- 
    Document   : dashboard
    Created on : 10 avr. 2015, 02:55:12
    Author     : Fabien Maître
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="assets/canvasjs/canvasjs.min.js"></script>

<h1 class="text-center">Tableau de bord de ${authUser}</h1>

<div id="dashboard">
  <div class="row">
    <div class="col-md-4 col-lg-5" id="chartContainer">
      <%@include file="chartUserStat.jsp"%>
    </div>
    <div class="col-md-6 col-md-offset-1 col-lg-7 col-lg-offset-0" id="chartContainer2">
      <%@include file="chartnbCPOuverts.jsp"%>
    </div>
  </div>
  <div class="row">
    <div class="col-md-12">
      <%@include file="derniersVirements.jsp"%>
    </div>
  </div>
  <!-- Virements par date 
  <div class="row">
    <div class="col-md-6">
      <div id="chartContainer3">
        <%@include file="chartStat.jsp"%>
      </div>
    </div>
  </div>
  -->
</div>