# Java API Rest

<p>Base d'une API REST en java avec <a href="https://spring.io/">Spring Boot</a></p>
<br>

Création de la base de donnée :
<ol>
<li>Se connecter à MYSQL <code>sudo mysql --password</code></li>
<li>Création de la base de donnée <code>create database db_example;</code></li>
<li>Création de l'utilisateur <code>create user 'springuser'@'%' identified by 'ThePassword';</code></li>
<li>donner accès à l’utilisateur <code>grant all on db_example.* to 'springuser'@'%';</code></li>
</ol>
<br>
Tester la base de donnée :
<ol>
<li>Ajouter un utilisateur <code>curl http://localhost:8080/API/users -d name=First -d email=someemail@someemailprovider.com</code></li>
<li>La réponse devrait être <code>Saved</code></li>
<li>Afficher tout les utilisateurs <code>curl http://localhost:8080/API/users</code></li>
<li>Exemple de réponse <code>[{"id":1,"name":"First","email":"someemail@someemailprovider.com"}]</code></li>
</ol>
