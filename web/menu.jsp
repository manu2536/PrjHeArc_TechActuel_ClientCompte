<%-- 
    Document   : menu
    Created on : 9 avr. 2015, 10:58:39
    Author     : Fabien Maître
--%>
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand navbar-left" href="BankController?action=dashboard">
        <img alt="Ma Banque" src="img/logo.png">
      </a>
    </div>
    <div id="navbar" class="collapse navbar-collapse">
      <ul class="nav navbar-nav navbar-default">
        <% 
          //Récupère la page courrante
          String currentPage = "accueil";
          if(session.getAttribute("currentPage") != null){
            currentPage = (String) session.getAttribute("currentPage");
          }
        %>
        <li class="${currentPage == "accueil" ? 'active' : ''}"><a href="BankController?action=dashboard">Accueil</a></li>
        <li class="${currentPage == "clients" ? 'active' : ''}"><a href="BankController?action=listClient">Clients</a></li>
        <li class="${currentPage == "virement" ? 'active' : ''}"><a href="BankController?action=virement">Virement</a></li>
        <li class="${currentPage == "depot" ? 'active' : ''}"><a href="BankController?action=depot">Dépôt</a></li>
        <li class="${currentPage == "retrait" ? 'active' : ''}"><a href="BankController?action=retrait">Retrait</a></li>
      </ul>
    </div><!--/.nav-collapse -->
  </div>
</nav>