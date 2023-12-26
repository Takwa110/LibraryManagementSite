<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Créer un compte</title>
    <style>
        body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #f5f5f5;
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
    margin-top: 20px;
}
form:hover {
            transform: scale(1.02);
        }
h1 {
    color: #3498db; /* Change header color to a nice blue */
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
    border-color: #3498db;
}

input[type="submit"] {
    background-color: #3498db;
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

.success-message {
    color: #4CAF50;
    text-align: center;
    display: none;
    margin-top: 20px;
}


    </style>

    <script>
        // Function to show a success message after form submission
        function showSuccessMessage() {
            document.getElementById("success-message").style.display = "block";
        }
    </script>
</head>
<body>

    <h1>Créer un compte</h1>
    <form action="TraitementCreationCompteServlet" method="post" onsubmit="showSuccessMessage()">
        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" required>

        <label for="prenom">Prénom :</label>
        <input type="text" id="prenom" name="prenom" required>

        <label for="email">Email :</label>
        <input type="text" id="email" name="email" required>

        <label for="motDePasse">Mot de passe :</label>
        <input type="password" id="motDePasse" name="motDePasse" required>

        <input type="submit" value="Créer le compte">

        <p id="success-message" class="success-message">Compte créé avec succès!</p>
    </form>
</body>
</html>