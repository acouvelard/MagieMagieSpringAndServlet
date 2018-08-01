<%--
    Document   : listerParties
    Created on : 13 juil. 2018, 11:50:48
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <c:import url="_LINKHEAD.jsp"></c:import>
        <title>MagieMagie - Liste des parties</title>
    </head>
    <body>
      <header>
        <h1>MagieMagie</h1>
      </header>

      <section id="listeParties" class="mainSection">
        <h2>Parties non-démarrées</h2>
        <ul>
            <c:forEach items="${listeParties}" var="partieAct">
              <li><span><span class="nomPartie">${partieAct.nom}</span>(${partieAct.joueurs.size()} joueur)</span>
                <a href="<c:url value="rejoindrePartie"></c:url>"><button type="button" name="rejoindrePartie">Rejoindre cette partie</button></a>
              </li>
            </c:forEach>
        </ul>
      </section>

      <section class="buttonBottomLeft">
          <a href="<c:url value="creerPartie"></c:url>"><button type="button" name="creerUnePartie">Créer une nouvelle partie</button></a>
      </section>
    </body>
</html>
