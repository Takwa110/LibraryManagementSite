import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import projetJEE.Emprunt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/HistoriqueEmpruntServlet")
public class HistoriqueEmpruntServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/bibliotheque";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Takoua020103";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");

        // You should perform user authentication here before accessing the database.
        // For simplicity, let's assume the authentication is successful.

        List<Emprunt> historiqueEmprunts = getHistoriqueEmprunts(email, motDePasse);

        request.setAttribute("historiqueEmprunts", historiqueEmprunts);
        request.getRequestDispatcher("/HistoriqueEmprunt.jsp").forward(request, response);
    }

    private List<Emprunt> getHistoriqueEmprunts(String email, String motDePasse) {
        List<Emprunt> historiqueEmprunts = new ArrayList<>();

        // Charger le pilote JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            // TODO: Perform user authentication based on the email and motDePasse.

            // Assuming the user is authenticated, retrieve historical borrowings.
            String query = "SELECT Livres.Titre, Emprunts.DateEmprunt, Emprunts.DateRetour, Emprunts.Retourne "
                    + "FROM Emprunts "
                    + "INNER JOIN Livres ON Emprunts.LivreID = Livres.ID "
                    + "INNER JOIN Utilisateurs ON Emprunts.UtilisateurID = Utilisateurs.ID "
                    + "WHERE Utilisateurs.Email = ? AND Utilisateurs.MotDePasse = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, motDePasse);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String titreLivre = resultSet.getString("Titre");
                        java.util.Date dateEmprunt = resultSet.getDate("DateEmprunt");
                        java.util.Date dateRetour = resultSet.getDate("DateRetour");
                        boolean retourne = resultSet.getBoolean("Retourne");

                        Emprunt emprunt = new Emprunt(titreLivre, new Date(dateEmprunt.getTime()),
                                new Date(dateRetour.getTime()), retourne);

                        historiqueEmprunts.add(emprunt);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs.
        }

        return historiqueEmprunts;
    }
}
