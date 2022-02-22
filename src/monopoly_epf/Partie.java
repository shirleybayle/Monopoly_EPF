/*
 * BOCAHUT Manon - FEVRIER Titouan - CHAUVET Ylan - BAYLE Shirley
 */
package monopoly_epf;

/**
 *
 * @author shirl
 */
public class Partie {
    Plateau plateau;
    Joueur joueurCourant;
    Paquet paquetChance;
    Paquet paquetCommunaute;
    Joueur [] tabJoueurs = new Joueur[4];
    Des de1;
    Des de2;
    int compteurDouble;
    
    public void tourJoueur() { //check credit à la fin du tour (positif ou pas)
        
    }
    
    public void changerJoueur() { //penser à réinitialiser le compteur double
        
    }
    
    public int lancerDes() { //lire double et initialiser compteur
        
    }
    
    public boolean lireDouble() {
        if (de1.valeur == de2.valeur) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public void deplacerPion(Pion pionAssocie, int nbcases, Case caseInitiale) {
        
    }
    
    public boolean faireAction(){
        
    }
    
    public boolean acheter(){
        
    }
    
    public boolean payer() {
        
    }
    
    public Carte tirerCarte() {
        
    }
    
    public void faireActionCarte(Carte carteTiree) {
        
    }
    
    public void eliminationJoueur() { //réinitialiser toutes ses cases
        
    }
    
    public void finDePartie() {
        
    }
    
    public boolean acheter(Case caseassociee){
        if (caseassociee.proprietaire == null) {
            caseassociee.proprietaire = joueurCourant;
            joueurCourant.credits = joueurCourant.credits-caseassociee.prixTerrain;
            joueurCourant.sallesPossedees.add(caseassociee);
            return true;
        }
        else {
            return false;
        }
    }
    
}
