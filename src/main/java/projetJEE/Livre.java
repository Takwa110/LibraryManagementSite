package projetJEE;

public class Livre {
    private int id;
    private String titre;
    private String auteur;
    private String genre;
    private int exemplairesDisponibles;

    // Constructeur
    public Livre(int id, String titre, String auteur, String genre, int exemplairesDisponibles) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.genre = genre;
        this.exemplairesDisponibles = exemplairesDisponibles;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getGenre() {
        return genre;
    }

    public int getExemplairesDisponibles() {
        return exemplairesDisponibles;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setExemplairesDisponibles(int exemplairesDisponibles) {
        this.exemplairesDisponibles = exemplairesDisponibles;
    }
}

