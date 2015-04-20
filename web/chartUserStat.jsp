<%-- 
    Document   : chartUserStat
    Created on : 19 avr. 2015, 10:09:40
    Author     : jeremy.wermeill
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script language ="javascript">
        var chart = new CanvasJS.Chart("chartContainer", {
            title: {
                text: "Comparaison des scores des utilisateurs",
                fontFamily: "Arial",
                fontColor: "black",
                fontSize: 18

            },
            animationEnabled: true,
            axisY: {
                tickThickness: 0,
                lineThickness: 0,
                valueFormatString: " ",
                gridThickness: 0                    
            },
            axisX: {
                tickThickness: 0,
                lineThickness: 0,
                labelFontSize: 18,
                labelFontColor: "black"

            },
            data: [
            {
                indexLabelFontSize: 12,
                toolTipContent: "<span style='\"'color: {color};'\"'><strong>{label}</strong></span><span style='\"'font-size: 20px; color:black '\"'><strong>{y}</strong></span>",
                indexLabelPlacement: "inside",
                indexLabelFontColor: "white",
                indexLabelFontWeight: 300,
                indexLabelFontFamily: "Verdana",
                color: "#3374B7",
                type: "bar",
                dataPoints: [
                   <c:forEach var="user" items="${listUsers}"> 
                     <c:choose>
                       <c:when test="${user.login == authUser}">
                       { y: ${user.points}, label: "vous", color: "green"},
                       </c:when> 
                     <c:otherwise>
                          { y: ${user.points}, label: "${user.login}"},
                     </c:otherwise>
                     </c:choose>
                   </c:forEach>	
                ]
            }
            ]
        });
        chart.render();
  </script>