<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>La Taverne</title>
    <%@ page import="com.Carceles.TP2.Models.Produit" %>
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
<h2>La carte</h2>
        <div>
            <table border="1" cellpadding="2" cellspacing="0">
                <thead>
                <tr>
                    <th> Id </th>
                    <th> Nom </th>
                    <th> Description </th>
                    <th> Prix </th>
                    <th> Quantité Stock </th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Produit> listeProduits = (List<Produit>) request.getAttribute("listeProduits");
                    for(int i=0; i<listeProduits.size(); i++){
                        out.println("<tr>" +
                                         "<td>" + listeProduits.get(i).getId() +"</td>" +
                                         "<td>" + listeProduits.get(i).getNom() +"</td>" +
                                         "<td>" + listeProduits.get(i).getDesc() + "</td>" +
                                         "<td>" + listeProduits.get(i).getPrix() + " €</td>" +
                                         "<td>" + listeProduits.get(i).getQteStock() + "</td>" +
                                         "<td><a href=\"/panier?id=" + listeProduits.get(i).getId() + "\"><button>Ajouter au panier</button></a></td>" +
                                     "</tr>");
                    }
                %>
                </tbody>
            </table>
        </div>
</form>
</body>
</html>