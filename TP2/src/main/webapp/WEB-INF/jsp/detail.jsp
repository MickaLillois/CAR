<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>La Taverne</title>
    <%@ page import="com.Carceles.TP2.Models.Lignecommande" %>
    <%@ page import="com.Carceles.TP2.Models.Produit" %>
    <%@ page import="com.Carceles.TP2.Repositories.ProduitRepository" %>
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
<% List<Lignecommande> listeLigne = (List<Lignecommande>) request.getAttribute("listeLigne"); %>
<h2>Détail de la commande n°<% out.println(listeLigne.get(0).getIdCommande()); %></h2>
        <br />
        <div>
            <table border="1" cellpadding="2" cellspacing="0">
                <thead>
                    <tr>
                        <th> Nom produit </th>
                        <th> Prix unitaire </th>
                        <th> Quantité </th>
                        <th> Sous-total </th>
                    </tr>
                </thead>
                <tbody>
                <%
                    ProduitRepository repo = (ProduitRepository) request.getAttribute("produitRepo");
                    for(Lignecommande lc : listeLigne){
                        Produit leProd = repo.findById(lc.getIdProduit()).get();
                        out.println("<tr>" +
                                    "<td>" + leProd.getNom() + "</td>" +
                                    "<td>" + String.format("%.2f", lc.getPrixunitaire()) + "€</td>" +
                                    "<td>" + lc.getQte() + "</td>" +
                                    "<td>" + (lc.getQte()*lc.getPrixunitaire()) + "</td>" +
                                    "</tr>");
                    }
                %>
                </tbody>
            </table>
        </div>
</form>
</body>
</html>