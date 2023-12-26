<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="projetJEE.Emprunt" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%! 
    // Define the formattedDate method
    public String formattedDate(java.util.Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
%>

<%
    List<Emprunt> historiqueEmprunts = (List<Emprunt>) request.getAttribute("historiqueEmprunts");
%>

<html>
<head>
    <title>Historique des Emprunts</title>
    <style>
           body {
            font-family: 'Arial', sans-serif;
            background-color: #f2f2f2;
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
            width: 50%;
            margin: 20px auto;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        label {
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
            transition: background-color 0.3s ease;
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

        p {
            color: #555;
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h1>Historique des Emprunts</h1>
    <form action="HistoriqueEmpruntServlet" method="get">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required><br>
        
        <label for="motDePasse">Mot de passe:</label>
        <input type="password" id="motDePasse" name="motDePasse" required><br>
        <input type="submit" value="Rechercher Historique">
    </form>
 
    <table>
        <tr>
            <th>Livre</th>
            <th>Date d'emprunt</th>
            <th>Date de retour</th>
            <th>Statut</th>
        </tr>
        <% if (historiqueEmprunts != null) { %>
            <% for (Emprunt emprunt : historiqueEmprunts) { %>
                <tr>
                    <td><%= emprunt.getLivre() %></td>
                    <td><%= formattedDate(emprunt.getDateEmprunt()) %></td>
                    <td><%= formattedDate(emprunt.getDateRetour()) %></td>
                    <td><% if (emprunt.isRetourne()) out.println("Retourné"); else out.println("En cours"); %></td>
                </tr>
            <% } %>
        <% } else { %>
            <tr>
                <td colspan="4"><p>Aucun emprunt trouvé.</p></td>
            </tr>
        <% } %>
    </table>
</body>
</html>
