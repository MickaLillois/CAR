<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>La Taverne</title>
    <%@ page import="com.Carceles.TP2.Models.Utilisateur" %>
    <%@ page import="com.Carceles.TP2.Models.Commande" %>
    <%@ page import="java.util.List" %>
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
<% Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur"); %>
<h2>Profil</h2>
        <div>
            <h5>Mail : <% out.println(utilisateur.getMail()); %></h5>
            <h5>Prénom : <% out.println(utilisateur.getPrenom()); %></h5>
            <h5>Nom : <% out.println(utilisateur.getNom()); %></h5>
            <h5>Pseudo : <% out.println(utilisateur.getPseudo()); %></h5>
        </div>
        <br /><br /><br />
        <div>
            <h3> Vos commandes passées</h3>
            <%
                List<Commande> listeCommande = (List<Commande>) request.getAttribute("listeCommande");
                for(Commande c : listeCommande){
                    out.println("<p>" + c + "</p>");
                }

            %>
        </div>
</form>
</body>
</html>