$(document).ready(function(e) {

  /*Récupère l'id du joueur en session*/
  var sessionJoueurId = "<%Session('idJoueurMoi')%>";

  /*REJOINDRE PARTIE IMG*/
  $('.radioAvatar img').click(function(e) {
    if ($('.monJoueur').length == 0) {
      $(this).addClass('monJoueur');
    } else {
      $('.radioAvatar img').removeClass('monJoueur');
      $(this).addClass('monJoueur');
    }
  });

  /*CLASS CARTES*/
  $('.checkCartes img').click(function(e) {
    if ($(this).hasClass('selectIngre')) {
      $(this).removeClass('selectIngre');
    }else {
      $(this).addClass('selectIngre');
    }

    if ($('.selectIngre').length == 2) {
      $('.boutons label:first-child').css('visibility', 'visible');
    } else {
      $('.boutons label:first-child').css('visibility', 'hidden');
    }
  });

  /*CLASS JOUEUR VICTIME*/
  $('.joueurPartie').click( function(e) {
    if ($(this).hasClass('selectVict')) {
      $(this).removeClass('selectVict');
    } else {
      if ($('.selectVict').length == 0) {
        $(this).addClass('selectVict');
      } else {
        $('.joueurPartie').removeClass('selectVict');
        $(this).addClass('selectVict');
      }
    }
  });

  /*Sommeil profond*/
  if ($('.joueurPartie').hasClass('sommeil')) {
    $('.sommeil .infoJoueur').append('En sommeil profond');
  }

  /*A la main et cahce ou montre bouton passer son tour*/
  if ($('.joueurPartie').hasClass('aLaMain')) {
    $('.aLaMain .infoJoueur').append('A la main');

    if (sessionJoueurId == $('div.joueurPartie').attr('joueurId')) {
      $('.boutons label:last-child').css('visibility', 'visible');
    }
  } else {
    $('.boutons label:last-child').css('visibility', 'hidden');
  }

  if ($('.joueurPartie').hasClass('perdu')) {
    $('.perdu .infoJoueur').append('Perdu');
  }

});
