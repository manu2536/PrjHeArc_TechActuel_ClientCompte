<%-- 
    Document   : sechercheClient
    Created on : 14 avr. 2015, 12:11:54
    Author     : emmanuel.rondez
--%>

<div> <!-- DIV Form Search -->
  <form name="formSearch" method="get" action="BankController">
    <input type="hidden" name="action" value="listClient"/>
    <input type="text" name="recherche"/>
    <button type="submit">Search</button>
  </form>
</div> <!-- DIV Form Search -->