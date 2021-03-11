<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>La Taverne</title>
</head>
<header>
    <a href="/"><h1><strong>Bienvenue à La Taverne !</strong></h1></a>
   <% if(session.getAttribute("idUtilisateur") == null) {%>
       <a href="/connexion"><button>Connexion</button></a>
       <a href="/inscription"><button>Inscription</button></a>
       <a href="/panier"><button>Panier</button></a>
       <a href="/carte"><button>Carte</button></a>
   <% }else{ %>
       <a href="/deconnexion"><button>Déconnexion</button></a>
       <a href="/profil"><button>Profil</button></a>
       <a href="/panier"><button>Panier</button></a>
       <a href="/carte"><button>Carte</button></a>
   <% } %>
</header>
<body>
<h2>Créer un compte</h2>
<form action="/checkInscription" method="POST">
        <div>
            <label for="mail">Votre adresse mail : </label>
            <input type="text" id="mail" name="mail">
        </div>
        <div>
            <label for="nom">Votre nom : </label>
            <input type="text" id="nom" name="nom">
        </div>
        <div>
            <label for="prenom">Votre prénom : </label>
            <input type="text" id="prenom" name="prenom">
        </div>
        <div>
            <label for="pseudo">Votre pseudo : </label>
            <input type="text" id="pseudo" name="pseudo">
        </div>
        <div>
            <label for="mdpForm">Votre mot de passe : </label>
            <input type="password" id="mdpForm" name="mdpForm">
        </div>
        <br />
        <div>
            <a href="/connexion">Vous avez déjà un compte ?</a>
        </div>
        <br />
        <div class="button">
            <button type="submit">Créer un compte</button>
        </div>
</form>
<br />
        <%
            if(request.getParameter("vide") != null){
                out.println("<div role='alert'>" +
                                "Tous les champs sont <strong>obligatoires</strong>." +
                             "</div>");
            }else if(request.getParameter("existe") != null){
                out.println("<div role='alert'>" +
                             "<strong>Ce mail est déjà</strong> utilisé pour un autre compte." +
                          "</div>");
            }
        %>
</body>
</html>