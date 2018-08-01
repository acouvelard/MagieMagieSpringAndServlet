<%--
    Document   : plateauJeu
    Created on : 13 juil. 2018, 11:51:09
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:import url="_LINKHEAD.jsp"></c:import>
        <title>MagieMagie - Partie</title>
    </head>
    <body>
      <section id="plateauJeu" class="mainSection">
        <article>
      <!--      <c:forEach items="${afficherEnnemis}" var="joueurAct">
              <div class="joueurPartie" joueurId="${joueurAct.id}">
                <img src="images/${joueurAct.avatar}.jpg" alt="avatar">
                <span>${joueurAct.pseudo}</span>
                <span>${joueurAct.cartes.size} cartes</span>
                <span class="infoJoueur"></span>
              </div>
            </c:forEach>-->
            <div class="joueurPartie aLaMain" joueurId="1">
              <img src="images/avatar1.png" alt="avatar"  class="monJoueur">
              <span>Richard</span>
              <span>15 cartes</span>
              <span class="infoJoueur"></span>
            </div>
            <div class="joueurPartie sommeil" joueurId="1">
              <img src="images/avatar2.png" alt="avatar">
              <span>Mariposa</span>
              <span>10 cartes</span>
              <span class="infoJoueur"></span>
            </div>
            <div class="joueurPartie perdu" joueurId="1">
              <img src="images/avatar3.png" alt="avatar">
              <span>Merlin</span>
              <span>0 cartes</span>
              <span class="infoJoueur"></span>
            </div>

        </article>

        <article class="moi">

            <form action="action" class="boutons">
              <label>
                <input type="radio" name="action" value="LancerSort">
                <button type="button" >Lancer un sort</button>
              </label>
              <label>
                <input type="radio" name="action" value="PasserTour">
                <button type="button" >Passer son tour</button>
              </label>
            </form>

            <div class="checkCartes">
      <!--      <c:forEach items="${mesCartes}" var="carteAct">
                <img src="images/${carteAct.ingre}.jpg" alt="${carteAct.id}" >
            </c:forEach>-->
              <img src="images/ailes.png" alt="ingre">
              <img src="images/corne.png" alt="ingre">
              <img src="images/ailes.png" alt="ingre">
              <img src="images/lapis.png" alt="ingre">
              <img src="images/mandragore.png" alt="ingre">
              <img src="images/ailes.png" alt="ingre">
              <img src="images/lapis.png" alt="ingre">
              <img src="images/crapaud.png" alt="ingre">
              <img src="images/mandragore.png" alt="ingre">
              <img src="images/lapis.png" alt="ingre">
            </div>
        </article>

        <div class="listeSorts">
          <a>?
            <div>
              <span>Invisibilité : corne + bave de crapaud</span>
              <span>Philtre d'amour : corne + mandragore</span>
              <span>Hypnose : bave de crapaud + lapis lazuli</span>
              <span>Divination : lapis lazuli + aile de chauve-sourie</span>
              <span>Sommeil profond : mandragore + ailes de chauve-sourie</span>
            </div>
          </a>
        </div>
      </section>
      <c:import url="_LINKSCRIPT.jsp"></c:import>
    </body>
</html>
