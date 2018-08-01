<%--
    Document   : creerNouvellePartie
    Created on : 13 juil. 2018, 11:42:27
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:import url="_LINKHEAD.jsp"></c:import>
        <title>MagieMagie - Créer une nouvelle partie</title>
    </head>
    <body>
      <header>
        <h1>MagieMagie</h1>
      </header>

      <section id="creerPartie" class="mainSection">
        <h2>Créer une nouvelle partie</h2>
        <form action="<c:url value="creerPartie"></c:url>" method="post">
          <div>
            <label for="nomNouvellePartie">Nom de la partie</label>
            <input type="text" name="nomNouvellePartie">
          </div>
          <button type="submit" name="creerNouvellePartie">Créer la partie</button>
        </form>
      </section>

      <section class="buttonBottomLeft">
        <a href="<c:url value="retour"></c:url>"><button type="button" name="retourAuMenu">Revenir à la list des parties</button></a>
      </section>
    </body>
</html>
