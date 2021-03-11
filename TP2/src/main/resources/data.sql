INSERT INTO utilisateur(id,mail,nom,prenom,pseudo,mdp) VALUES(1,'abc@gmail.com','Carceles','Micka','jeuneLillois','mdp');
INSERT INTO utilisateur(id,mail,nom,prenom,pseudo,mdp) VALUES(2,'def@gmail.com','Mathieu','Rudy','pseudo','1234');

INSERT INTO produit(id,nom,desc,prix,qteStock) VALUES(1,'Pastis','Boisson anisée',20,100);
INSERT INTO produit(id,nom,desc,prix,qteStock) VALUES(2,'Chouffe','Bière cool',8,0);
INSERT INTO produit(id,nom,desc,prix,qteStock) VALUES(3,'Maximator','Bière à consommer tiède',18,10);
INSERT INTO produit(id,nom,desc,prix,qteStock) VALUES(4,'Vodka','A consommer avec modération ou beaucoup de collègues',18,2);

INSERT INTO commande(id,idUtilisateur,prix,date) VALUES(1,1,200,now());

INSERT INTO lignecommande(idCommande,idProduit,prixunitaire,qte) VALUES(1,1,20,10);