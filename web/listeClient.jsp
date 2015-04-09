<%-- 
    Document   : listeClient
    Created on : 8 avr. 2015, 22:20:49
    Author     : emmanuel.rondez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <div> <!-- MAIN -->
            <table class="table table-hover" style="width: 100%;">
                <th>
                    <td> Nom </td>
                    <td> Adresse </td>
                    <td> Operation </td>
                </th>
                <c:forEach var="customer" items="${ListCustomers}">
                    <tr>
                        <td>${customer.nom} ${customer.prenom}</td>
                        <td>${customer.adresse} ${customer.ville}</td>
                        <td>
                            <a href="afficherClient?id=${customer.identifiant}" class="btn btn-info btn-mini"><i class="icon-white icon-eye-open"></i>Voir</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>


