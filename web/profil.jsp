<%-- 
    Document   : profil
    Created on : 18 avr. 2015, 11:29:19
    Author     : Fabien Maître
--%>
<script type="text/javascript" src="assets/bootstrap-progressbar-0.8.5/bootstrap-progressbar.min.js"></script>
<div class="container">
  <h1>Votre profil</h1>
  <div class="progress progress-striped">
    <div class="progress-bar" role="progressbar" aria-valuemin="${Level.from}" aria-valuemax="${Level.to}" data-transitiongoal="${User.points}"></div>
  </div>
  ${User.id}
  <br>
  ${User.login}
  <br>
  
</div>

<script>
  jQuery(document).ready(function($){
    $('.progress .progress-bar').progressbar({display_text: 'fill', use_percentage: false});
  });
</script>
