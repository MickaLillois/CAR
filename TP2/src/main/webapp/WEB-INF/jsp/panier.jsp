<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>La Taverne</title>
    <%@ page import="com.Carceles.TP2.Models.Produit" %>
    <%@ page import="com.Carceles.TP2.Repositories.ProduitRepository" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.HashMap" %>
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
<h2>Le panier</h2>
        <div>
                <%
                     ProduitRepository repo = (ProduitRepository) request.getAttribute("repository");
                     HashMap<Integer, Integer> panier = (HashMap<Integer, Integer>) session.getAttribute("panier");

                     for(int id : panier.keySet()){
                        Produit leProd = repo.findById(id).get();
                        Integer qte = panier.get(id);
                        out.println("<div>" +
                                    "<p>" + leProd.getNom() + " : " + qte + "</p>" +
                                    "<a href=\"/retirer?id=" + leProd.getId() + "\"><button>Retirer du panier</button></a>"  +
                                    "</div>");
                     }
                %>
        </div>
        <br /><br /><br />
        <a href="/commander"><button>Enregistrer la commande</button></a>
</form>
</body>
</html>