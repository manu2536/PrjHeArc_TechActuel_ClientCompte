<%-- 
    Document   : chartStat
    Created on : 17 avr. 2015, 23:22:15
    Author     : jeremy.wermeill
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" >
  jQuery(document).ready(function ($) {
    var chart = new CanvasJS.Chart("chartContainer3",{
      title: {
        text: "Virements par date"
      },
      animationEnabled: true,
      axisX: {
        valueFormatString: "DD-MM-YYYY",
        interval: 20,
        intervalType: "day",
        labelAngle: -50,
        labelFontColor: "rgb(0,75,141)",
        //on garde les dates jusqu'à 1 année
        minimum: new Date(2014, 11, 01)
      },
      axisY: {
        title: "Nombre de virements réalisé",
        interlacedColor: "#FAFAFA",
        tickColor: "azure",
        titleFontColor: "rgb(0,75,141)",
        valueFormatString: "#M,,.",
        interval: 5000
      },
      data: [{
        indexLabelFontColor: "darkSlateGray",
        name: 'views',
        type: "area",
        color: "#3374B7",
        markerSize: 8,
        dataPoints: [
          <c:forEach var="virement" items="${listVirement}">
            {x: new Date(${virement.year},${virement.month},${virement.day}), y: ${virement.montant}},
          </c:forEach>
        ]
      }]
    });

    chart.render();
  });
</script>
