<%-- 
    Document   : profil
    Created on : 18 avr. 2015, 11:29:19
    Author     : Fabien Maître
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="assets/bootstrap-progressbar-0.8.5/bootstrap-progressbar.min.js"></script>
<div class="container">
  <h1>Votre profil</h1>
  <div>
    <legend>Level ${UserLevel.position} - ${UserLevel.name}</legend>
    <div class="progress progress-striped">
      <div class="progress-bar" role="progressbar" aria-valuemin="${UserLevel.from}" aria-valuemax="${UserLevel.to}" data-transitiongoal="${User.points}"></div>
    </div>
  </div>
  <br>
  <div>
    <legend>Niveaux et récompenses</legend>
    <table class="table table-hover">
      <thead>
        <tr>
          <th>Niveau</th>
          <th>Nom</th>
          <th>De</th>
          <th>A</th>
          <th>Récompense</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="level" items="${Levels}">
          <tr class="${UserLevel.position == level.position ? 'success' : ''}">
            <td>${level.position}</td>
            <td>${level.name}</td>
            <td>${level.from} points</td>
            <td>${level.to} points</td>
            <td>${level.recompense}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>

<script>
  jQuery(document).ready(function($){
    $('.progress .progress-bar').progressbar({display_text: 'fill', use_percentage: false});
  });
</script>
