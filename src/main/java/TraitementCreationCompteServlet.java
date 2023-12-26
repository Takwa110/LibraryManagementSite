import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class TraitementCreationCompteServlet
 */
public class TraitementCreationCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/bibliotheque";
	    private static final String DB_USER = "root";
	    private static final String DB_PASSWORD = "Takoua020103";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraitementCreationCompteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();

	        try {
	            String nom = request.getParameter("nom");
	            String prenom = request.getParameter("prenom");
	            String email = request.getParameter("email");
	            String motDePasse = request.getParameter("motDePasse");

	            Class.forName("com.mysql.cj.jdbc.Driver");

	            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
	                String sql = "INSERT INTO bibliotheque.utilisateurs (Nom, Prenom, Email, MotDePasse) VALUES (?, ?, ?, ?)";
	                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	                    preparedStatement.setString(1, nom);
	                    preparedStatement.setString(2, prenom);
	                    preparedStatement.setString(3, email);
	                    preparedStatement.setString(4, motDePasse);

	                    preparedStatement.executeUpdate();


	                    out.println("<html><head>");
	                    out.println("<style>");
	                    out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; text-align: center; }");
	                    out.println("h2 { color: green; }");
	                    out.println("a { color: blue; text-decoration: none; }");
	                    out.println("</style>");
	                    out.println("</head><body>");
	                    out.println("<h2>Compte créé avec succès pour : " + email + "</h2>");
	                    out.println("<a href='index.jsp'>Se connecter</a>");
	                    out.println("</body></html>");
	                }
	            }
	        } catch (ClassNotFoundException | SQLException e) {
	            out.println("<html><head>");
	            out.println("<style>");
	            out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; text-align: center; }");
	            out.println("h2 { color: red; }");
	            out.println("</style>");
	            out.println("</head><body>");
	            out.println("<h2>Erreur lors de la création du compte</h2>");
	            e.printStackTrace(out);
	            out.println("</body></html>");
	        } finally {
	            out.close();
	        }

	    }

}