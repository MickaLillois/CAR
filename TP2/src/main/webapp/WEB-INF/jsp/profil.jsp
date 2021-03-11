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
<h2>Bienvenue <% out.println(utilisateur.getPrenom() + " " + utilisateur.getNom()); %></h2>
        <div>
            <p><strong>Mail</strong> : <% out.println(utilisateur.getMail()); %></p>
            <p><strong>Prénom</strong> : <% out.println(utilisateur.getPrenom()); %></p>
            <p><strong>Nom</strong> : <% out.println(utilisateur.getNom()); %></p>
            <p><strong>Pseudo</strong> : <% out.println(utilisateur.getPseudo()); %></p>
        </div>
        <br /><br /><br />
        <div>
            <h3> Votre historique de commande</h3>
                <%
                    List<Commande> listeCommande = (List<Commande>) request.getAttribute("listeCommande");
                    if(listeCommande.size() == 0){
                        out.println("<p>Vous n'avez jamais commandé.</p>");
                    }
                    else{
                        out.println("<table border='1' cellpadding='2' cellspacing='0'>" +
                                                        "<thead>" +
                                                            "<tr>" +
                                                                "<th> Id </th>" +
                                                                "<th> Date Commande </th>" +
                                                                "<th> Prix total TTC </th>" +
                                                                "<th></th>" +
                                                            "</tr>" +
                                                        "</thead>" +
                                                        "<tbody>");
                        for(Commande c : listeCommande){
                            out.println("<tr>" +
                                        "<td>" + c.getId() + "</td>" +
                                        "<td>" + c.getDate() + "</td>" +
                                        "<td>" + String.format("%.2f", c.getPrix()) + "€</td>" +
                                        "<td>" + "<a href='/detail?id=" + c.getId() + "'><button>Détail de la commande</button></a>" + "</td>" +
                                        "</tr>");
                        }
                        out.println("</tbody>" + "</table>");
                    }
                    %>
        </div>
</form>
</body>
</html>