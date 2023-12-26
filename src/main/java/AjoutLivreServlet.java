import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class AjoutLivreServlet
 */
public class AjoutLivreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/bibliotheque";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Takoua020103";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutLivreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();

	        try {	        
	        String titre = request.getParameter("titre");
	        String auteur = request.getParameter("auteur");
	        String genre = request.getParameter("genre");
	        int exemplaires = Integer.parseInt(request.getParameter("exemplaires"));


            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String sql = "INSERT INTO Livres (Titre, Auteur, Genre, ExemplairesDisponibles) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, titre);
                    preparedStatement.setString(2, auteur);
                    preparedStatement.setString(3, genre);
                    preparedStatement.setInt(4, exemplaires);

                    preparedStatement.executeUpdate();

                    out.println("<html><body>");
                    out.println("<h2>livre ajoutee avec succès pour : " + auteur + "</h2>");
                    out.println("<a href pageAcceuil.jsp'> page d'acceuil</a>");
                    out.println("</body></html>");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<html><body>");
            out.println("<h2>Erreur lors de la création du livre</h2>");
            e.printStackTrace(out);
            out.println("</body></html>");
        } finally {
            out.close();
        }

	    }

}