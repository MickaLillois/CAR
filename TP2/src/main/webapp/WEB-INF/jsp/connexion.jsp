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
<h2>Connexion</h2>
<form action="/checkConnexion" method="POST">
        <div>
            <label for="mail">Votre adresse mail : </label>
            <input type="text" id="mail" name="mail">
        </div>
        <br />
        <div>
            <label for="mdpForm">Votre mot de passe : </label>
            <input type="password" id="mdpForm" name="mdpForm">
        </div>
        <br />
        <div class="button">
            <button type="submit">Connexion</button>
        </div>
</form>
<br />
<%
            if(request.getParameter("vide") != null){
                out.println("<div role='alert'>" +
                                "Veuillez renseigner votre <strong>mot de passe ainsi que votre email</strong>." +
                             "</div>");
            }else if(request.getParameter("existe") != null){
                out.println("<div role='alert'>" +
                             "<strong>Aucun compte</strong> n'utilise cette adresse email." +
                          "</div>");
            }else if(request.getParameter("mdp") != null){
                 out.println("<div role='alert'>" +
                                "Le mot de passe saisi est <strong>incorrect</strong>." +
                              "</div>");
            }
        %>
</body>
</html>