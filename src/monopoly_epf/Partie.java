/*
 * BOCAHUT Manon - FEVRIER Titouan - CHAUVET Ylan - BAYLE Shirley
 */
package monopoly_epf;

import java.util.Random;

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
        lancerDes();
        
        
        if(joueurCourant.credits<0) {
            eliminationJoueur();
        }
    }
    
    public void changerJoueur() { //penser à réinitialiser le compteur double
        if(joueurCourant==tabJoueurs[3]) {
            joueurCourant=tabJoueurs[0];
        }
        else{
            for(int i=0; i<3; i++){
                if(joueurCourant==tabJoueurs[i]) {
                    joueurCourant=tabJoueurs[i+1];
                }
            }
        }
    }
    
    public int lancerDes() { //lire double et initialiser compteur
        Random lancerdes = new Random();
        de1.valeur = lancerdes.nextInt();
        de2.valeur = lancerdes.nextInt();
        if(lireDouble()==true) {
            compteurDouble++;
        }
        return(de1.valeur+de2.valeur);
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
        for(int i=0; i<plateau.plateaudejeu.length; i++) {
            if(caseInitiale==plateau.plateaudejeu[i]) {
                pionAssocie.caseassociee=plateau.plateaudejeu[i+nbcases];
            }
        }
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
        joueurCourant.sallesPossedees=null;
        joueurCourant.tabCartes=null;
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
