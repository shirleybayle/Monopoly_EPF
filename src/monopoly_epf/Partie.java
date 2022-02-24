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
    int argentParcGratuit;
    
    public void tourJoueur() { 
        lancerDes();
        if (paquetChance.paquetVide() == true) { //au début du tour on vérifie qu'il reste des carte dans les paquets et si non on les réinitialise
            paquetChance.melanger();
            paquetChance.MAJtab();
        }
        if (paquetCommunaute.paquetVide() == true) {
            paquetCommunaute.melanger();
            paquetCommunaute.MAJtab();
        }
        
        if(joueurCourant.credits<0) { // à la fin du tour on regarde si le joueur a toujours des credits, si non il est éliminé
            eliminationJoueur(); 
        }
    }
    
   /* public void changerJoueur() { //penser à réinitialiser le compteur double
        if(joueurCourant==tabJoueurs[3]) {
            joueurCourant=tabJoueurs[0];
        }
        else{
            for(int i=0; i<3; i++){
                if(joueurCourant==tabJoueurs[i]) {
                    
                }
            }
        }
    }*/
    
    public void changerJoueur() { // j'ai gardé ta fonction en commentaire et j'en ai écrite une autre parce que j'ai modifié elimination joueur en supprimant les joueurs du tableau
        int temp = 0;
        boolean changementi = false;
        for (int i=0; i<4; i++) { //i représente l'indice du joueur courant dans le tableau tabjoueurs
            for (int j=1; j<4; j++) { //j représente le décalage du nouveau joueur courant dans le tableau tabjoueurs
                
                if (joueurCourant == tabJoueurs[i]) {
                    if (i==3 || i+j>3) { //si on sort de l'intervalle des indices du tableau
                        temp = i; //on garde en mémoire la position du joueur courant initial
                        i = i-3;
                        changementi = true; //on indique qu'on a changé la valeur de i
                    }
                    if (tabJoueurs[i+j] != null) { //si le joueur qu'on veut passer en joueur courant n'est pas éliminé
                        joueurCourant = tabJoueurs[i+j]; //il devient joueur courant
                    }
                    else if (changementi == true) { // si i a été changé 
                        i = temp; //sinon i reprend sa valeur initale
                    }
                }
               
            }
        }
        compteurDouble = 0; //réinitialisation du compteur double à 0
    }
    
    
    public int lancerDes() { //lire double et initialiser compteur
        Random lancerdes = new Random();
        de1.valeur = lancerdes.nextInt(); //ça va a 6 ça ?
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
    
    public void deplacerPion(Pion pionAssocie, int nbcases, Case caseInitiale) { //gérer si i+nbcases dépasse l'indice max du plateau
        for(int i=0; i<plateau.plateaudejeu.length; i++) {
            if(caseInitiale==plateau.plateaudejeu[i]) {
                if (i+nbcases <= 39) {
                    pionAssocie.caseassociee=plateau.plateaudejeu[i+nbcases];
                }
                else {
                    pionAssocie.caseassociee=plateau.plateaudejeu[i+nbcases-39]; //tour de plateau
                    if (i+nbcases-39!=0) {
                        joueurCourant.credits = joueurCourant.credits + 200; //200crédits quand on a fait un tour de plateau
                    }
                    else {
                        joueurCourant.credits = joueurCourant.credits + 400; //400crédits quand on tombe sur la case départ
                    }
                }
            }
        }
    }
    
    public boolean faireActionCase(){ // A COMPLETER
        Case caseDuJoueur = joueurCourant.pion.caseassociee;
        if (caseDuJoueur == plateau.plateaudejeu[2] || caseDuJoueur == plateau.plateaudejeu[17] || caseDuJoueur == plateau.plateaudejeu[33]) { //s'il est sur une case caisse de communauté
            Carte carteTiree = tirerCarte(paquetCommunaute);
            faireActionCarte(carteTiree);
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[7] || caseDuJoueur == plateau.plateaudejeu[22] || caseDuJoueur == plateau.plateaudejeu[36]) { //s'il est sur une case chance
            Carte carteTiree = tirerCarte(paquetChance);
            faireActionCarte(carteTiree);
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[4]) { //s'il est sur la premiere taxe
            joueurCourant.credits = joueurCourant.credits-200;
            argentParcGratuit = argentParcGratuit + 200;
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[38]) {
            joueurCourant.credits = joueurCourant.credits-100;
            argentParcGratuit = argentParcGratuit + 100;
            return true;
        }
        return false;
    }
    
    public boolean acheter(){
        
    }
    
    public boolean payer() {
        
    }
    
    public Carte tirerCarte(Paquet paquet) {
        for (int i=0; i<16; i++) {
            if(paquet.tabstatuts[i] == true) {
                paquet.tabstatuts[i] = false;
                return paquet.paquet[i];
            }
        }
        return null;
    }
    
    public void faireActionCarte(Carte carteTiree) {
        
    }
    
    public void eliminationJoueur() { //réinitialiser toutes ses cases
        joueurCourant.sallesPossedees=null;
        joueurCourant.tabCartes=null; 
        for (int i=0; i<4; i++) {
            if (joueurCourant == tabJoueurs[i]) { //enlever le joueur du tableau
                tabJoueurs[i] = null;
            }
        }
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
