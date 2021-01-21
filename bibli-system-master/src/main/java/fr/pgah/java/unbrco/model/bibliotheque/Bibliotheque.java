package fr.pgah.java.unbrco.model.bibliotheque;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import fr.pgah.java.unbrco.model.livre.GenreLivre;
import fr.pgah.java.unbrco.model.livre.Livre;

public class Bibliotheque {

  private Bibliothecaire gerant;
  private String nom;
  private List<Bibliotheque> branches;
  
  private HashMap<GenreLivre, ArrayList<Livre>> livres = new HashMap<>();

  public Bibliotheque(String nom, Bibliothecaire gerant) {
    this.nom = nom;
    this.gerant = gerant;
    livres.put(GenreLivre.REFERENCE, new ArrayList<>() );
    livres.put(GenreLivre.ROMAN, new ArrayList<> () );
    livres.put(GenreLivre.BIBLIOGRAPHIE, new ArrayList<> () );
    livres.put(GenreLivre.MANUEL, new ArrayList<>() );
    livres.put(GenreLivre.CUISINE, new ArrayList<>() );
  }

  public String getNom() {
    return nom;
  }

  public Bibliothecaire getGerant() {
    return gerant;
  }

  // PREREQUIS : livre != null
  // MODIFIE : this
  // EFFETS : stocke livre dans la collection appropriée de cet objet
  public void enregistrerLivre(Livre livre) {
    if (livre != null) {
      switch (livre.getGenre()) {
        case BIBLIOGRAPHIE:
          livres.get(GenreLivre.BIBLIOGRAPHIE).add(livre);
          break;
        case CUISINE:
          livres.get(GenreLivre.CUISINE).add(livre);
          break;
        case MANUEL:
          livres.get(GenreLivre.MANUEL).add(livre);
          break;
        case REFERENCE:
          livres.get(GenreLivre.REFERENCE).add(livre);
          break;
        case ROMAN:
          livres.get(GenreLivre.ROMAN).add(livre);
          break;
      }
    }
  }

    // PREREQUIS : livre != null
  // RENVOIE : vrai si livre est dans le catalogue de cette bibliothèque
  // (qu'il soit actuellement emprunté ou non)
  public boolean estDansCatalogue(Livre livre) {
    if (livre != null) {
      for (Entry<GenreLivre, ArrayList<Livre> > entry : livres.entrySet() ) {
        for (int i = 0; i < entry.getValue().size(); i++) {
          if (entry.getValue().get(i).equals(livre)) {
            return true;
          }
        }
      }
    }
    return false;
  }
  // PREREQUIS: livre != null
  // RENVOIE : vrai si livre est disponible à l'emprunt

  public boolean peutEtreEmprunte(Livre livre) {
    if (livre != null && livre.estSorti() == false) {
      return true;
    }
    return false;
    
  }


  // PREREQUIS : livre != null
  // RENVOIE : vrai si livre est dans le catalogue de cette bibliotheque
  // ou dans celui de l'un de ses branches
  public boolean estDansCatalogueEtendu(Livre livre) {
    if (livre != null) {
      for (Bibliotheque bibliotheque : branches) {
        if (bibliotheque.estDansCatalogue(livre)) {
          return true;
        }
      }
    }
    return false;
  }

  
  // PREREQUIS : livre != null
  // MODIFIE : this
  // EFFETS : enregistre le fait que livre est maintenant emprunté (si possible)
  // RENVOIE : vrai si l'opération est un succès
  public boolean enregistrerSortie(Livre livre) {
    if (livre != null) {
      if (livre.estSorti() == false) {
        livre.enregistrerSortie();
        return true;
      }
    }
    return false;
  }

  
  // PREREQUIS : livre != null
  // MODIFIE : this
  // EFFETS : enregistre le fait que livre est revenu (si possible)
  // RENVOIE : vrai si l'opération est un succès
  public boolean enregistrerRetour(Livre livre) {
    if (livre != null) {
      if (livre.estSorti()) {
        livre.enregistrerRetour();
        return true;
      }
    }
    return false;
  }

  // PREREQUIS : gerant != null
  // MODIFIE : this
  // EFFETS : set le nouveau gérant
  // RENVOIE : vrai si l'opération est un succès
  public boolean engagerGerant(Bibliothecaire bibliothecaire) {
    if (gerant != null) {
      gerant = bibliothecaire;
      return true;
    }
    return false;
  }
  // EFFETS : affiche le catalogue de cette bibliothèque
  // (toutes les informations de chaque livre)
  public void afficherCatalogue() {
    for (Entry<GenreLivre, ArrayList<Livre>> entry : livres.entrySet()) {
      for (Livre livre : entry.getValue() ) {
        System.out.println(
            "Titre du livre: " + livre.getTitre() + "Auteur du livre: " + livre.getAuteur() + "Genre du livre: "
                + livre.getGenre() + "Année du livre: " + livre.getAnnee() + "Edition du livre: " + livre.getEdition());
      }
    }
  }
}
