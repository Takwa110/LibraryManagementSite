import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GestionEmpruntServlet")
public class GestionEmpruntServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/bibliotheque";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Takoua020103";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        String livre = request.getParameter("livre");
        String dateEmpruntString = request.getParameter("dateEmprunt");
        String dateRetourString = request.getParameter("dateRetour");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDateEmprunt;
        java.util.Date parsedDateRetour;

        try {
            parsedDateEmprunt = dateFormat.parse(dateEmpruntString);
            parsedDateRetour = dateFormat.parse(dateRetourString);
        } catch (ParseException e) {
            e.printStackTrace();
            envoyerErreur(request, response, "Format de date incorrect");
            return;
        }

        Date dateEmprunt = new Date(parsedDateEmprunt.getTime());
        Date dateRetour = new Date(parsedDateRetour.getTime());
        
        // Charger le pilote JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            envoyerErreur(request, response, "Erreur lors du chargement du pilote JDBC");
            return;
        }
        

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            int utilisateurID = obtenirUtilisateurID(connection, email, mdp);
            int livreID = obtenirLivreID(connection, livre);
            int nbExemplaire = obtenirNbExemplaire(connection, livre);

            if (utilisateurID == -1) {
                envoyerErreur(request, response, "Utilisateur n'existe pas");
                return;
            }
            if (livreID == -1) {
                envoyerErreur(request, response, "Livre n'existe pas");
                return;
            }
            if (nbExemplaire == 0) {
                envoyerErreur(request, response, "Nombre d'exemplaire = 0");
                return;
            }

            String sql = "INSERT INTO Emprunts (UtilisateurID, LivreID, DateEmprunt, DateRetour, Retourne) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, utilisateurID);
                statement.setInt(2, livreID);
                statement.setDate(3, dateEmprunt);
                statement.setDate(4, dateRetour);
                statement.setBoolean(5, false);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    // Mise à jour du nombre d'exemplaires disponibles du livre
                    String updateSql = "UPDATE Livres SET ExemplairesDisponibles = ExemplairesDisponibles - 1 WHERE ID = ?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                        updateStatement.setInt(1, livreID);
                        updateStatement.executeUpdate();
                    }

                    envoyerSucces(request, response, "Emprunt effectué avec succès");
                } else {
                    envoyerErreur(request, response, "Vous ne pouvez pas emprunter ce livre");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            envoyerErreur(request, response, "Erreur de connexion à la base de données");
        }
    }

    private int obtenirUtilisateurID(Connection connection, String email, String mdp) throws SQLException {
        String sql = "SELECT ID FROM Utilisateurs WHERE Email = ? AND MotDePasse = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, mdp);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? resultSet.getInt("ID") : -1;
            }
        }
    }

    private int obtenirLivreID(Connection connection, String livre) throws SQLException {
        String sql = "SELECT ID FROM Livres WHERE Titre = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, livre);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? resultSet.getInt("ID") : -1;
            }
        }
    }

    private int obtenirNbExemplaire(Connection connection, String livre) throws SQLException {
        String sql = "SELECT ExemplairesDisponibles FROM Livres WHERE Titre = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, livre);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? resultSet.getInt("ExemplairesDisponibles") : 0;
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