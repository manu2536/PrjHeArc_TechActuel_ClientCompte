<%-- 
    Document   : depot
    Created on : 14 avr. 2015, 12:18:19
    Author     : Fabien Maître
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container">
  <h1>Page de dépôt</h1>
  <div class="col-md-12">
    <form class="form-horizontal" action="post" >

          <div class="form-group">
            <label class="col-md-2 control-label" for="selectCompte">Compte de dépôt</label>
            <div class="col-md-4">
              <select id="selectbasic" name="selectCompte" class="form-control">
                <c:forEach var="Compte" items="${SelectedClient.listeCompte}">
                  <option value="${Compte.identifiant}"> ${Compte.numro} ${Compte.nom} </option>
                </c:forEach>
              </select>
              </div>
          </div>
          <div class="form-group">
            <label class="col-md-2 control-label" for="montant">Montant [CHF]</label>  
            <div class="col-md-4">
            <input id="textinput" name="montant" type="text" placeholder="1000" class="form-control input-md"> 
            </div>
          </div>

    </form>
  </div>
</div>