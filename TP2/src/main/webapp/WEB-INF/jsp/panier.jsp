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
                     Float total = 0.00F;
                     ProduitRepository repo = (ProduitRepository) request.getAttribute("repository");
                     HashMap<Integer, Integer> panier = (HashMap<Integer, Integer>) session.getAttribute("panier");

                     for(int id : panier.keySet()){
                        Produit leProd = repo.findById(id).get();
                        Integer qte = panier.get(id);
                        total += qte * leProd.getPrix();
                        out.println("<div>" +
                                    "<p>" + leProd.getNom() + " : " + qte + "</p>" +
                                    "<a href=\"/retirer?id=" + leProd.getId() + "\"><button>Retirer du panier</button></a>"  +
                                    "</div>");
                     }
                %>
                <p>Total TTC : <% out.println(String.format("%.2f", total)); %>€</p>
        </div>
        <br /><br />
        <a href="/commander"><button>Enregistrer la commande</button></a>
        <br /><br />
         <%
            if(request.getParameter("vide") != null){
                  out.println("<div role='alert'>" +
                                "Impossible d'enregistrer la commande : votre panier est <strong>vide</strong>." +
                              "</div>");
            }else if(request.getParameter("dispo") != null){
                  out.println("<div role='alert'>" +
                                "Impossible d'ajouter ce produit au panier : le stock n'est pas <strong>suffisant</strong>." +
                              "</div>");
            }
         %>
</form>
</body>
</html>