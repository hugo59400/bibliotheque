package fr.pgah.java.unbrco.model.livre;

import fr.pgah.java.unbrco.model.bibliotheque.Bibliotheque;

public class Livre {

  private String titre;
  private String auteur;
  private GenreLivre genre;
  private int annee;
  private int edition;
  private Bibliotheque bibliothequeMere;
  private boolean estSorti;

  public Livre(String titre, String auteur, GenreLivre genre, int annee, int edition) {
    this.titre = titre;
    this.auteur = auteur;
    this.genre = genre;
    this.annee = annee;
    this.edition = edition;
  }

  public String getTitre() {
    return titre;

  }

  public String getAuteur() {
    return auteur;
  }

  public GenreLivre getGenre() {
    return genre;
  }

  public int getAnnee() {
    return annee;
  }

  public int getEdition() {
    return edition;
  }

  public Bibliotheque getBibliothequeMere() {
    return bibliothequeMere;
  }

  public void setBibliothequeMere(Bibliotheque bibliotheque) {
    
    this.bibliothequeMere = bibliotheque;
  }
  // MODIFIE : this
  // EFFETS : enregistre le fait que le livre est revenu
  public void enregistrerRetour() {
    estSorti = false;
  }
  // RENVOIE : vrai si le livre est sorti
  public boolean estSorti() {
    return estSorti;
  }

  // MODIFIE : this
  // EFFETS : enregistre le fait que le livre est sorti
  public void enregistrerSortie() {
    estSorti = true;
  }
  

}
