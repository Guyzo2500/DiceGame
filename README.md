Voici le contenu que vous devez copier et coller dans votre fichier **README.md** :

---

```markdown
# 🎲 DiceGame - API REST de gestion des lancers de dés

## 📌 Description
DiceGame est une API REST développée en **Spring Boot**, permettant de gérer des lancers de dés et d'enregistrer leurs résultats dans une base de données H2.  
L’API fournit plusieurs endpoints pour **créer, lire, modifier et supprimer** les enregistrements des lancers de dés.

---

## 🔧 Technologies Utilisées
- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **H2 Database (base de données en mémoire)**
- **Lombok**
- **Swagger UI**
- **Postman (pour les tests d’API)**

---

## 🚀 Installation et Exécution du Projet
### 📌 Prérequis
1. **Git** doit être installé sur votre machine. [Télécharger Git](https://git-scm.com/downloads)
2. **Java 17** doit être installé. [Télécharger Java](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
3. **Maven** doit être installé. [Télécharger Maven](https://maven.apache.org/download.cgi)
4. **Postman** (pour tester l’API). [Télécharger Postman](https://www.postman.com/downloads/)

### 📥 Cloner le projet depuis GitHub
```bash
git clone https://github.com/Guyzo2500/DiceGame.git
cd DiceGame
```

### ▶️ Lancer l’application
Avec **Maven** :
```bash
mvn spring-boot:run
```
Avec **IntelliJ IDEA** :
1. Ouvrez le projet.
2. Exécutez la classe **DiceGameApplication.java**.

---

## ⚙️ Configuration
Le projet utilise une base de données H2 en mémoire. La configuration est définie dans `src/main/resources/application.properties` :
```properties
server.port=8081
spring.application.name=DiceGame
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:devDb
spring.h2.console.path=/h2-console
```
Accédez à la **console H2** via : [http://localhost:8081/h2-console](http://localhost:8081/h2-console)  
- **JDBC URL** : `jdbc:h2:mem:devDb`
- **Username** : `sa`
- **Password** : _(laisser vide)_

---

## 📖 Documentation de l’API
L’API REST est documentée avec **Swagger**.  
📌 **URL de la documentation Swagger** : [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

### 📌 Endpoints Disponibles
| Méthode | Endpoint | Description |
|---------|------------------------|-----------------------------------|
| **GET** | `/api/DiceRollLogs` | Récupérer tous les lancers de dés |
| **POST** | `/api/DiceRollLogs` | Créer un nouveau lancer |
| **GET** | `/api/DiceRollLog/{id}` | Récupérer un lancer spécifique |
| **PUT** | `/api/DiceRollLog/{id}` | Mettre à jour un lancer existant |
| **PATCH** | `/api/DiceRollLog/{id}` | Modifier partiellement un lancer |
| **DELETE** | `/api/DiceRollLog/{id}` | Supprimer un lancer |

---

## 🛠️ Tests avec Postman
### 📌 Importer la collection de tests
Une collection Postman contenant des tests est incluse dans le projet :
- 📂 **Fichier** : `postman/DiceGame_API_Collection.json`
- 📌 **Étapes pour importer** :
  1. Ouvrez **Postman**.
  2. Allez dans **File > Import**.
  3. Sélectionnez le fichier `postman/DiceGame_API_Collection.json`.

### 📌 Tests Automatisés
Chaque requête Postman contient des scripts pour valider la réponse.  
Exemple de test pour **GET `/api/DiceRollLogs`** :
```javascript
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Response is an array", function () {
    pm.expect(pm.response.json()).to.be.an("array");
});
```

---

## 🏗️ Contributions
Les contributions sont les bienvenues ! 🚀  
Si vous souhaitez améliorer le projet :
1. **Fork** le dépôt.
2. Créez une **nouvelle branche** :
   ```bash
   git checkout -b feature-nouvelle-fonctionnalite
   ```
3. **Apportez vos modifications** et **commitez** :
   ```bash
   git commit -m "Ajout d'une nouvelle fonctionnalité"
   ```
4. **Poussez vos changements** :
   ```bash
   git push origin feature-nouvelle-fonctionnalite
   ```
5. Créez une **Pull Request** sur GitHub.

---

## 📜 Licence
📌 Ce projet est sous licence **MIT**, ce qui signifie que vous pouvez l’utiliser et le modifier librement.

---

## 📧 Contact
📧 Pour toute question ou suggestion, contactez-moi via [GitHub](https://github.com/Guyzo2500).

---

### ✅ Instructions pour l’ajout au projet :
1. Créez un fichier nommé **README.md** à la racine de votre projet.
2. Copiez-collez ce texte dans le fichier.
3. **Ajoutez-le à Git** :
   ```bash
   git add README.md
   git commit -m "Ajout du fichier README"
   git push origin main
   ```
4. Vérifiez sur GitHub que le fichier est bien ajouté.

Si vous avez des modifications spécifiques à ajouter, faites-moi savoir ! 😊🚀
