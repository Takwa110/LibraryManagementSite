import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import projetJEE.Livre;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/RechercheLivreServlet")
public class RechercheLivresServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/bibliotheque";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Takoua020103";

    public RechercheLivresServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titre = request.getParameter("titre");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            List<Livre> livres = obtenirlivres(titre, connection);

            request.setAttribute("listelivres", livres);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/recherchelivre.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("erreur.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private List<Livre> obtenirlivres(String titre, Connection connection) throws SQLException {
        List<Livre> lstlivres = new ArrayList<>();

        String sql = "SELECT * FROM Livres WHERE Titre = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, titre);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String Titre = resultSet.getString("Titre");
                    String auteur = resultSet.getString("Auteur");
                    String genre = resultSet.getString("Genre");
                    int NBexemplaire = resultSet.getInt("ExemplairesDisponibles");

                    Livre livre = new Livre(id, Titre, auteur, genre, NBexemplaire);
                    lstlivres.add(livre);
                }
            }
        }
        return lstlivres;
    }
}