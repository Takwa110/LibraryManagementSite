<%@ page import="java.util.List" %>
<%@ page import="projetJEE.Livre" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Livre> listelivres = (List<Livre>) request.getAttribute("listelivres");
%>

<html>
<head>
    <title>Recherche de Livre</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 20px;
        }

        h1 {
            color: #3498db;
            text-align: center;
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
            margin-top: 20px;
            display: flex;
            flex-direction: column;
        }
        form:hover {
            transform: scale(1.02);
        }

        label {
            color: #555;
            margin-bottom: 8px;
        }

        input {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 6px;
            transition: border-color 0.3s ease;
        }

        input:focus {
            border-color: #3498db;
        }

        input[type="submit"] {
            background-color: #2ecc71;
            color: #fff;
            padding: 15px;
            font-size: 16px;
            cursor: pointer;
            border: none;
            border-radius: 6px;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #27ae60;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #ffffff;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 15px;
            text-align: left;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #e0e0e0;
        }
    </style>
</head>
    <body>
    <h1>Recherche de Livre</h1>
    <form action="RechercheLivresServlet" method="get">
        <label for="titre">Titre:</label>
        <input type="text" id="titre" name="titre">
        <input type="submit" value="Rechercher Livre">
    </form>

   <table>
    <tr>
        <th>ID</th>
        <th>Titre</th>
        <th>Auteur</th>
        <th>Genre</th>
        <th>Exemplaires Disponibles</th>
    </tr>
    <% if (listelivres != null) { %>
        <% for (Livre livre : listelivres) { %>
            <tr>
                <td><%= livre.getId() %></td>
                <td><%= livre.getTitre() %></td>
                <td><%= livre.getAuteur() %></td>
                <td><%= livre.getGenre() %></td>
                <td><%= livre.getExemplairesDisponibles() %></td>
            </tr>
        <% } %>
    <% } else { %>
        <tr>
            <td colspan="5"><p>No Livres found.</p></td>
        </tr>
    <% } %>
</table>

</body>
</html>
