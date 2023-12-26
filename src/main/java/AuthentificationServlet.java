import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/AuthentificationServlet")
public class AuthentificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/bibliotheque";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Takoua020103";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");

        // Vérifier l'authentification
        if (authentifierUtilisateur(email, motDePasse)) {
            // Rediriger vers la page d'accueil en cas de succès
        	 request.getSession().setAttribute("email", email);

            request.getSession().setAttribute("motDePasse", motDePasse);
            response.sendRedirect("pageacueil.jsp");
        } else {
            // En cas d'échec, renvoyer un message d'erreur à la page index.jsp
            request.setAttribute("errorMessage", "Identifiants incorrects. Veuillez réessayer.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private boolean authentifierUtilisateur(String email, String motDePasse) {
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion à la base de données
            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                // Requête SQL pour vérifier l'authentification
                String sql = "SELECT * FROM Utilisateurs WHERE Email=? AND MotDePasse=?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, email);
                    statement.setString(2, motDePasse);

                    // Exécuter la requête
                    try (ResultSet resultSet = statement.executeQuery()) {
                        // Si l'utilisateur existe dans la base de données
                        return resultSet.next();
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Gérer les exceptions de manière appropriée dans un environnement de production
        }
        return false;
    }
}