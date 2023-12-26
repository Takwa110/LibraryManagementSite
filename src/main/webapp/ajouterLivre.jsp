<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Ajout de Livre</title>
    <style>
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
            color: #3498db;
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
    </style>

    <script>
        // Function to handle form submission
        function handleFormSubmit() {
            alert("Livre ajouté avec succès!"); // Add your desired action or remove this line
        }
    </script>
</head>
<body>

    <h1>Ajout de Livre</h1>
    <form action="AjoutLivreServlet" method="post" >
        <label for="titre">Titre :</label>
        <input type="text" id="titre" name="titre" required>

        <label for="auteur">Auteur :</label>
        <input type="text" id="auteur" name="auteur" required>

        <label for="genre">Genre :</label>
        <input type="text" id="genre" name="genre">

        <label for="exemplaires">Exemplaires Disponibles :</label>
        <input type="number" id="exemplaires" name="exemplaires" required>

        <input type="submit" value="Ajouter Livre">
    </form>
</body>
</html>
