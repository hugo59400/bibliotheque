package fr.pgah.java.unbrco.model.bibliotheque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import fr.pgah.java.unbrco.model.livre.Livre;
import fr.pgah.java.unbrco.model.livre.GenreLivre;

public class BibliothequeTests {

  private Livre livreRef;
  private Livre livreRom;
  private Livre livreBiblio;
  private Livre livreMan;
  private Livre livreCui;
  private Bibliotheque testBibliotheque;
  private Bibliothecaire testBibliothecaire;

  @BeforeEach
  public void setUp() {
    testBibliotheque = new Bibliotheque("Bibliothèque privée Dampierre", testBibliothecaire);
    testBibliothecaire = new Bibliothecaire("Kevin Roy", 10, testBibliotheque, livreRom);
    livreRef = new Livre("Dormir n'importe où", "E. Clément, B. Briendo", GenreLivre.REFERENCE, 2021, 1);
    livreRef.setBibliothequeMere(testBibliotheque);
    livreRom = new Livre("Pizza mon amour", "T. Geneste", GenreLivre.ROMAN, 2021, 1);
    livreRom.setBibliothequeMere(testBibliotheque);
    livreBiblio = new Livre("Ma vie avec lui", "A. Krzykawsky", GenreLivre.BIBLIOGRAPHIE, 2023, 1);
    livreBiblio.setBibliothequeMere(testBibliotheque);
    livreMan = new Livre("Introduction à la programmation C#", "M. Giera", GenreLivre.MANUEL, 2035, 2);
    livreMan.setBibliothequeMere(testBibliotheque);
    livreCui = new Livre("Maîtriser l'Art de l'Américain-cervelas", "Q. Delaporte", GenreLivre.CUISINE, 2020, 2);
    livreCui.setBibliothequeMere(testBibliotheque);
  }

  @Test
  public void retourne_le_nom_de_la_biliotheque() {
    assertEquals("Bibliothèque privée Dampierre", testBibliotheque.getNom());

  }

  @Test
  public void retourne_vrai_pour_toutes_les_categories_de_livre_quiSontDansLeCatalogue() {

    assertEquals(true, testBibliotheque.estDansCatalogue(livreRef));
    assertEquals(true, testBibliotheque.estDansCatalogue(livreCui));
    assertEquals(true, testBibliotheque.estDansCatalogue(livreRom));
    assertEquals(true, testBibliotheque.estDansCatalogue(livreMan));
  }

  @Test
  public void retourne_vrai_si_le_livrepeutEtreEmprunteEtEnregistreLaSortie() {
    assertEquals(testBibliotheque.peutEtreEmprunte(livreRef), true);
    testBibliotheque.enregistrerSortie(livreRef);
    assertEquals(testBibliotheque.peutEtreEmprunte(livreRef), false);
    assertEquals(testBibliotheque.peutEtreEmprunte(livreCui), true);
    testBibliotheque.enregistrerSortie(livreCui);
    assertEquals(testBibliotheque.peutEtreEmprunte(livreCui), false);
  }

  @Test
  public void test4() {
    assertEquals(true, testBibliotheque.peutEtreEmprunte(livreMan));

    assertEquals(true, testBibliotheque.enregistrerSortie(livreMan));
    assertEquals(false, testBibliotheque.peutEtreEmprunte(livreMan));
    assertEquals(true, testBibliotheque.enregistrerRetour(livreMan));
    assertEquals(true, testBibliotheque.peutEtreEmprunte(livreMan));
  }

  
}
