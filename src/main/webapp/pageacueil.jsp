<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accueil - Bibliothèque</title>
    <style>
       body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background: linear-gradient(45deg, #3498db, #2c3e50);
    color: #fff;
    margin: 0;
    padding: 0;
}

.container {
    max-width: 800px;
    margin: 50px auto;
    background-color: rgba(255, 255, 255, 0.9);
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

h1 {
    color: #3498db;
    text-align: center;
    margin-bottom: 20px;
}

p {
    text-align: center;
    margin-bottom: 20px;
       color: #555;
    
    
}

h2 {
    color: #333;
}

form {
    text-align: center;
    margin-top: 20px;
}

button {
    width: 200px; /* Ajoutez la largeur souhaitée en pixels */
    padding: 10px 20px;
    font-size: 16px;
    background-color: #3498db;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin-right: 10px;
}
form button {
    width: 200px; /* Ajoutez la largeur souhaitée en pixels */
}

button:hover {
    background-color: #1f618d;
}

.reviews {
    margin-top: 30px;
    text-align: left;
}

.review {
    border: 1px solid #ddd;
    padding: 20px;
    border-radius: 8px;
    margin-bottom: 20px;
    background-color: #f9f9f9;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.review h3 {
    color: #2980b9;
    margin-bottom: 10px;
}

.review p {
    color: #555;
    line-height: 1.6;
}

form:hover {
    transform: scale(1.02);
}
    </style>
</head>
<body>
    <div class="container">
        <h1>Bienvenue à la Bibliothèque</h1>
        <p>La bibliothèque offre une vaste collection de livres pour votre plaisir de lecture.</p>

        <h2>Fonctionnalités :</h2>
        <form action="recherchelivre.jsp">
            <button type="submit">Recherche d'un Livre</button>
        </form>

        <form action="ajouterLivre.jsp" >
            <button type="submit">Ajout de Livres</button>
        </form>

        <form action="gestionEmprunt.jsp">
        <input type="hidden" name="email" value="<%= request.getParameter("email") %>">
         <input type="hidden" name="mdp" value="<%= request.getParameter("mdp") %>">
            <button type="submit">Gestion des Emprunts</button>
        </form>

       <form action="HistoriqueEmprunt.jsp">
       <input type="hidden" name="email" value="<%= request.getParameter("email") %>">
       <input type="hidden" name="mdp" value="<%= request.getParameter("mdp") %>">
       <button type="submit">Consultation de l'Historique d'Emprunts</button>
       </form>

        <form action="renderLivre.jsp">
       <input type="hidden" name="email" value="<%= request.getParameter("email") %>">
       <input type="hidden" name="mdp" value="<%= request.getParameter("mdp") %>">
       <button type="submit">rendre un livres</button>
       </form>
        <div class="reviews">
            <h2>Avis des Visiteurs :</h2>
            <div class="review">
                <h3>Excellent service!</h3>
                <p>La bibliothèque propose une variété incroyable de livres. L'expérience de lecture est exceptionnelle!</p>
            </div>

            <div class="review">
                <h3>Large collection</h3>
                <p>J'ai trouvé tous les livres que je cherchais. La bibliothèque a une collection impressionnante.</p>
            </div>
        </div>
    </div>
</body>
</html>
