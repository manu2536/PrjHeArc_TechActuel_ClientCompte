<%-- 
    Document   : chartnbCPOuverts
    Created on : 19 avr. 2015, 14:38:23
    Author     : jeremy.wermeill
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
  <script type="text/javascript">
    var chartContainer2 = new CanvasJS.Chart("chartContainer2",
    {
      title:{
        text: "Nombres de comptes ouverts par mois",
         fontFamily: "Arial",
         fontColor: "black",
         fontSize: 18
      },
      animationEnabled: true,
      axisX: {
        valueFormatString: "MMM",
        interval:1,
        intervalType: "month"
        
      },
      axisY:{
        includeZero: false
        
      },
      data: [
      {        
        type: "line",
        color:"#3374B7",
        //lineThickness: 3,        
        dataPoints: [
          <c:forEach items="${mapCpOpenByMonth}" var="entry">
            { x: new Date(${annee},${entry.key},1), y: ${entry.value} },
         </c:forEach>
        ]
      }
      
      
      ]
    });
chartContainer2.render();
</script>
<script type="text/javascript" src="assets/canvasjs/canvasjs.min.js"></script>
  <div id="chartContainer2" style="height: 300px; width: 50%;">
  </div>