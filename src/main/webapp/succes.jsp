<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Succès</title>
</head>
<body>
    <div style="color: green; text-align: center;">
        <h2>Succès</h2>
        <p><%= request.getAttribute("successMessage") %></p>
    </div>
</body>
</html>