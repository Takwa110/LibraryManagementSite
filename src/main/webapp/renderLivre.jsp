<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Rendre un Livre - Bibliothèque</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #3498db;
            text-align: center;
            margin-bottom: 20px;
        }

        form {
            text-align: center;
            margin-top: 20px;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input {
            padding: 10px;
            font-size: 16px;
            margin-bottom: 20px;
        }

        button {
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

        button:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Rendre un Livre</h1>
        <form action="RendreLivreServlet" method="post">
            <label for="titreLivre">Titre du Livre :</label>
            <input type="text" id="titreLivre" name="titreLivre" required>
             <label for="email">Email:</label>
             <input type="text" id="email" name="email" required><br>
        
             <label for="motDePasse">Mot de passe:</label>
             <input type="password" id="motDePasse" name="motDePasse" required><br>
            

            <button type="submit">Rendre le Livre</button>
        </form>
    </div>
</body>
</html>