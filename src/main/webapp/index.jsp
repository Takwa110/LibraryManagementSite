<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accueil</title>
    <style>
      :root {
    --main-color: #3498db;
    --button-color: #555;
    --button-hover-color: #333;
}

body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #f8f9fa;
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
}

form {
    max-width: 400px;
    width: 100%;
    padding: 30px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    background-color: #ffffff;
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 50px;
    transition: transform 0.3s ease-in-out;
}

form:hover {
    transform: scale(1.02);
}

h1 {
    color: var(--main-color);
    text-align: center;
    margin-bottom: 20px;
}

label {
    display: block;
    margin-bottom: 10px;
    color: #555;
    font-weight: bold;
}

input {
    width: 100%;
    padding: 12px;
    margin-bottom: 20px;
    box-sizing: border-box;
    border: 1px solid #ddd;
    border-radius: 6px;
    transition: border-color 0.3s ease;
}

input:focus {
    border-color: var(--main-color);
}

input[type="submit"] {
    background-color: var(--main-color);
    color: #fff;
    padding: 15px;
    font-size: 16px;
    cursor: pointer;
    border: none;
    border-radius: 6px;
    transition: background-color 0.3s ease;
}

input[type="submit"]:hover {
    background-color: #2980b9;
}

button {
    margin-top: 10px;
    background-color: var(--button-color);
    color: #fff;
    padding: 10px;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

button:hover {
    background-color: var(--button-hover-color);
}

button:first-of-type {
    margin-right: 5px;
}

button[type="button"] {
    width: 100%;
}
    </style>
</head>
<body>
    <% if (request.getAttribute("errorMessage") != null) { %>
        <div style="color: red;">
            <%= request.getAttribute("errorMessage") %>
        </div>
    <% } %>
    <h1>Page d'accueil</h1>
    <form action="AuthentificationServlet" method="post">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required><br>
        
        <label for="motDePasse">Mot de passe:</label>
        <input type="password" id="motDePasse" name="motDePasse" required><br>

        <input type="submit" value="Se connecter">

        <button type="button" onclick="window.location.href='creerCompte.jsp'">Créer un compte</button>
        <button type="button" onclick="window.location.href='recherchelivre.jsp'">Rechercher un livre</button>
        
    </form>
</body>
</html>