<%--
    Document   : rejoindrePartie
    Created on : 13 juil. 2018, 11:55:07
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:import url="_LINKHEAD.jsp"></c:import>
        <title>MagieMagie - rejoindre un partie</title>
    </head>
    <body>
      <header>
        <h1>MagieMagie</h1>
      </header>

      <section id="rejoindrePartie" class="mainSection">
        <h2>Rejoindre la partie : NomPartie</h2>
        <form action="<c:url value="rejoindrePartie"></c:url>" method="post">
          <div>
            <label for="nomJoueur">Pseudo</label>
            <input type="text" name="nomJoueur">
            <label for="avatarJoueur">Avatar</label>
            <div class="radioAvatar">
              <img src="images/avatar1.png" alt="avatar">
              <img src="images/avatar2.png" alt="avatar">
              <img src="images/avatar3.png" alt="avatar">
              <img src="images/avatar4.png" alt="avatar">
            </div>
          </div>
          <button type="submit" name="creerNouvellePartie">Rejoindre la partie</button>
        </form>
      </section>

      <section class="buttonBottomLeft">
        <a href="<c:url value="retour"></c:url>"><button type="button" name="retourAuMenu">Revenir à la list des parties</button></a>
      </section>
      <c:import url="_LINKSCRIPT.jsp"></c:import>
    </body>
</html>
