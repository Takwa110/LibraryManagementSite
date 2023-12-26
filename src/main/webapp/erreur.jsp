<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Erreur</title>
</head>
<body>
    <div style="color: red; text-align: center;">
        <h2>Erreur</h2>
        <p><%= request.getAttribute("errorMessage") %></p>
    </div>
</body>
</html>