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
        <li class="dropdown ${currentPage == "clients" || currentPage == 'addClient' ? 'active' : ''}">
          <a href="BankController?action=listClient" class="dropdown-toggle" data-hover="dropdown" role="button" aria-expanded="true">Clients <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a class="${currentPage == 'addClient' ? 'active' : ''}" href="BankController?action=addClient">Ajouter client</a></li>
          </ul>
        </li>
        <%-- Si client selectionné --%>
        <c:if test="<%=session.getAttribute("SelectedClient") != null%>">
          <li class="${currentPage == "client" ? 'active' : ''}"><a class="inline" href="BankController?action=afficherClient">Client: ${SelectedClient.nom} ${SelectedClient.prenom}</a><a class="inline" href="BankController?action=deselectClient"><span class="glyphicon glyphicon-off"></span></a></li>
          <li class="${currentPage == "virement" ? 'active' : ''}"><a href="BankController?action=virement">Virement</a></li>
          <li class="${currentPage == "depot" ? 'active' : ''}"><a href="BankController?action=depot">Dépôt</a></li>
          <li class="${currentPage == "retrait" ? 'active' : ''}"><a href="BankController?action=retrait">Retrait</a></li>
        </c:if>
        
        <%@include  file="autoCompletion.jsp"%>
        <form class="navbar-form navbar-right" id="formSearch" name="formSearch" method="post" action="BankController?action=listClient">
          <div class="input-group">
            <input type="text" id="rechercheclient" name="recherche" class="form-control" placeholder="Rechercher un client..."/>
            <span class="input-group-btn">
              <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button>
            </span>
          </div>
        </form>
      </ul>
  
        
        
      <ul class="nav navbar-nav navbar-right">
        <li class="navbar-righ ${currentPage == "profil" ? 'active' : ''}"><a href="BankController?action=profil">${authUser}</a></li>
        <li class="navbar-righ"><a href="BankController?action=logout">Déconnexion</a></li>
      </ul>
    </div><!--/.nav-collapse -->
  </div>
</nav>