package projetJEE;


import java.sql.Date;

public class Emprunt {
    private String livre;
    private Date dateEmprunt;
    private Date dateRetour;
    private boolean retourne;

    public Emprunt(String livre, Date dateEmprunt, Date dateRetour, boolean retourne) {
        this.livre = livre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.retourne = retourne;
    }

    public String getLivre() {
        return livre;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public boolean isRetourne() {
        return retourne;
    }
}