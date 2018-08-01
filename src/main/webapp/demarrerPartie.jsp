<%--
    Document   : demarrerPartie
    Created on : 13 juil. 2018, 11:51:00
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:import url="_LINKHEAD.jsp"></c:import>
        <title>MagieMagie - Démarrer la partie</title>
    </head>
    <body>
      <header>
        <h1>MagieMagie</h1>
      </header>

      <section id="demarrerPartie" class="mainSection">
        <h2>Demarrer la partie : NomPartie</h2>
        <h3>Joueurs</h3>
        <article class="joueursEnnemis">
            <c:forEach items="${listeJoueurs}" var="joueurAct">
              <div class="joueurPartie">
                <img src="images/${joueurAct.avatar}.jpg" alt="${joueurAct.avatar}">
                <span>${joueurAct.pseudo}</span>
              </div>
            </c:forEach>
        </article>
        <form action="<c:url value="demarrerPartie"></c:url>" method="post">
          <button type="submit" name="demarrerPartie">Démarrer la partie</button>
        </form>
      </section>

      <section class="buttonBottomLeft">
          <a href="<c:url value="retour"></c:url>"><button type="button" name="retourAuMenu">Revenir à la list des parties</button></a>
      </section>
    </body>
</html>
