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

@WebServlet("/RendreLivreServlet")
public class RendreLivreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/bibliotheque";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Takoua020103";

    public RendreLivreServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titreLivre = request.getParameter("titreLivre");
        String email = request.getParameter("email");
        String mdp = request.getParameter("motDePasse");
        // Charger le pilote JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            int utilisateurID = obtenirUtilisateurID(email, mdp);

            if (utilisateurID == -1) {
                envoyerErreur(request, response, "Utilisateur n'existe pas");
                return;
            }

            int livreID = obtenirLivreID(titreLivre);

            if (livreID == -1) {
                envoyerErreur(request, response, "Livre n'existe pas");
                return;
            }

            String sql = "UPDATE Emprunts SET Retourne = true WHERE LivreID = ? AND UtilisateurID = ? AND Retourne = false";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, livreID);
                statement.setInt(2, utilisateurID);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    String updateSql = "UPDATE Livres SET ExemplairesDisponibles = ExemplairesDisponibles + 1 WHERE ID = ?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                        updateStatement.setInt(1, livreID);
                        updateStatement.executeUpdate();
                    }

                    envoyerSucces(request, response, "Livre rendu avec succès");
                } else {
                    envoyerErreur(request, response, "Vous ne pouvez pas rendre ce livre");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            envoyerErreur(request, response, "Erreur de connexion à la base de données");
        }
    }

    private int obtenirUtilisateurID(String email, String mdp) throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
        String sql = "SELECT ID FROM Utilisateurs WHERE Email = ? AND MotDePasse = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, mdp);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("ID");
                } else {
                    return -1;
                }
            }
        }
    }

    private int obtenirLivreID(String titreLivre) throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
        String sql = "SELECT ID FROM Livres WHERE Titre = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, titreLivre);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("ID");
                } else {
                    return -1;
                }
            }
        }
    }

    private void envoyerSucces(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.setAttribute("successMessage", message);
        request.getRequestDispatcher("succes.jsp").forward(request, response);
    }

    private void envoyerErreur(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher("erreur.jsp").forward(request, response);
    }
}
