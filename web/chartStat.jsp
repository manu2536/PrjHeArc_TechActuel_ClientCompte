<%-- 
    Document   : chartStat
    Created on : 17 avr. 2015, 23:22:15
    Author     : jeremy.wermeill
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>

<head>  
	<script type="text/javascript" >
	window.onload = function () {
		var chart = new CanvasJS.Chart("chartContainer",
		{
			title: {
				text: "Virements par date"
			},
                        animationEnabled: true,
			axisX:{      
				valueFormatString: "DD-MM-YYYY" ,
				interval: 20,
				intervalType: "day",
				labelAngle: -50,
				labelFontColor: "rgb(0,75,141)",
                               //on garde les dates jusqu'à 1 année
				minimum: new Date(2014,11,01)
			},
			axisY: {
				title: "Nombre de virements réalisé",
				interlacedColor: "#FAFAFA",
				tickColor: "azure",
				titleFontColor: "rgb(0,75,141)",
				valueFormatString: "#M,,.",
				interval: 5000
			},
			data: [
			{        
				indexLabelFontColor: "darkSlateGray",
				name: 'views',
				type: "area",
				color: "#3374B7",
				markerSize:8,
				dataPoints: [
                               <c:forEach var="virement" items="${listVirement}"> 
                                { x: new Date(${virement.year},${virement.month},${virement.day}), y: ${virement.montant} }, 
                               </c:forEach>		
//                               { x: new Date(2012, 06, 18), y: 2000000 }, 
//				{ x: new Date(2012, 06, 23), y: 6000000 }, 
//				{ x: new Date(2012, 07, 1), y: 10000000, indexLabel:"10m"}, 
//				{ x: new Date(2012, 07, 11), y: 21000000 }, 
//				{ x: new Date(2012, 07, 23), y: 50000000 }, 
//				{ x: new Date(2012, 07, 31), y: 75000000  }, 
//				{ x: new Date(2012, 08, 04), y: 100000000, indexLabel:"100m" },
//				{ x: new Date(2012, 08, 10), y: 125000000 },
//				{ x: new Date(2012, 08, 13), y: 150000000},	
//				{ x: new Date(2012, 08, 16), y: 175000000},	
//				{ x: new Date(2012, 08, 18), y: 200000000, indexLabel:"200m"},	
//				{ x: new Date(2012, 08, 21), y: 225000000},	
//				{ x: new Date(2012, 08, 24), y: 250000000},	
//				{ x: new Date(2012, 08, 26), y: 275000000},	
//				{ x: new Date(2012, 08, 28), y: 302000000, indexLabel:"300m"}	
				]
			}
			
			]
		});

chart.render();
}
</script>
<script type="text/javascript" src="assets/canvasjs/canvasjs.min.js"></script>
</head>
<body>
	<div id="chartContainer" style="height: 350px; width: 50%;">
	</div>
</body>
</html>