<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestion des Emprunts</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f8f8;
            margin: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        h1 {
            text-align: center;
            color: #3498db;
            margin-bottom: 20px;
        }

        form {
            max-width: 400px;
            width: 100%;
            padding: 30px;
            border: 1px solid #ddd;
            border-radius: 10px;
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        form:hover {
            transform: scale(1.02);
        }

        label {
            display: block;
            margin-bottom: 10px;
            color: #555;
        }

        input {
            width: 100%;
            padding: 14px;
            margin-bottom: 20px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 8px;
            transition: border-color 0.3s ease;
        }

        input:focus {
            border-color: #3498db;
        }

        input[type="submit"] {
            background-color: #2ecc71;
            color: #fff;
            padding: 16px;
            font-size: 18px;
            cursor: pointer;
            border: none;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #27ae60;
        }
    </style>
</head>
<body>
    <h1>Gestion des Emprunts</h1>
    <form action="GestionEmpruntServlet" method="post" onsubmit="submitForm()">
        <label for="email">email:</label>
        <input type="text" id="email" name="email" required>
        <label for="mdp">MotDePasse:</label>
        <input type="password" id="mdp" name="mdp" required>
        <label for="livre">Livre:</label>
        <input type="text" id="livre" name="livre" required>

        <label for="dateEmprunt">Date d'emprunt:</label>
        <input type="date" id="dateEmprunt" name="dateEmprunt" required>

        <label for="dateRetour">Date de retour prévue:</label>
        <input type="date" id="dateRetour" name="dateRetour" required>

        <input type="submit" value="Emprunter">
    </form>
</body>
</html>