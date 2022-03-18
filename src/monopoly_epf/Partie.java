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
        lancerDes();  //prévoir le fait qu'on puisse acheter des maisons au début du tour
        if (paquetChance.paquetVide() == true) { //au début du tour on vérifie qu'il reste des carte dans les paquets et si non on les réinitialise
            paquetChance.melanger();
            paquetChance.MAJtab();
        }
        if (paquetCommunaute.paquetVide() == true) {
            paquetCommunaute.melanger();
            paquetCommunaute.MAJtab();
        }
        
        if (joueurCourant.prison == true) {
            //LE JOUEUR EST EN PRISON --> double ?
            boolean testDouble = lireDouble();
            if (testDouble == true) {
                joueurCourant.prison = false;
                lancerDes();
                for(int i=0;i<plateau.plateaudejeu.length;i++) {
                    if(joueurCourant.pion.caseassociee == plateau.plateaudejeu[i]) {
                        if(i+(de1.valeur+de2.valeur)>39) {
                            int caseoualler = ((i+(de1.valeur+de2.valeur))-39)-1; //i c'est la position actuelle à laqelle on ajoute la somme des dés et si c'est > à 39 on lui enlève 39 pour revenir au début du plateau et -1 pour le 0 (ex: on est à 38 on fait 7, 38+7=45, 45-39=6, -1 pour être à la case[5] qui est la 6ème du plateau
                            joueurCourant.pion.caseassociee = plateau.plateaudejeu[caseoualler];
                            faireActionCase();
                        }
                        else {
                            joueurCourant.pion.caseassociee = plateau.plateaudejeu[i+(de1.valeur+de2.valeur)];
                            faireActionCase();
                        }
                    }
                }
                
            }
            else {
                joueurCourant.compteurTourPrison++;
                if(joueurCourant.compteurTourPrison == 3) {
                    joueurCourant.credits = joueurCourant.credits-50;
                    joueurCourant.prison = false;
                    joueurCourant.compteurTourPrison = 0;
                }
            }
        }
        else {
            for(int i=0;i<plateau.plateaudejeu.length;i++) {
                    if(joueurCourant.pion.caseassociee == plateau.plateaudejeu[i]) {
                        if(i+(de1.valeur+de2.valeur)>39) {
                            int caseoualler = ((i+(de1.valeur+de2.valeur))-39)-1; //i c'est la position actuelle à laqelle on ajoute la somme des dés et si c'est > à 39 on lui enlève 39 pour revenir au début du plateau et -1 pour le 0 (ex: on est à 38 on fait 7, 38+7=45, 45-39=6, -1 pour être à la case[5] qui est la 6ème du plateau
                            joueurCourant.pion.caseassociee = plateau.plateaudejeu[caseoualler];
                            faireActionCase();
                        }
                        else {
                            joueurCourant.pion.caseassociee = plateau.plateaudejeu[i+(de1.valeur+de2.valeur)];
                            faireActionCase();
                        }
                    }
            }
        }
        if(joueurCourant.credits<0) { // à la fin du tour on regarde si le joueur a toujours des credits, si non il est éliminé
            eliminationJoueur(); 
        }
    }
    
    
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
        de1.valeur = lancerdes.nextInt(6); 
        de2.valeur = lancerdes.nextInt(6);
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
    
    public boolean faireActionCase(){ // A COMPLETER - il manque les gares
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
        else if (caseDuJoueur == plateau.plateaudejeu[38]) { //deuxième taxe
            joueurCourant.credits = joueurCourant.credits-100;
            argentParcGratuit = argentParcGratuit + 100;
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[20]) { //parc gratuit
            joueurCourant.credits= joueurCourant.credits + argentParcGratuit;
            argentParcGratuit = 0;
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[30]) { //prison
            joueurCourant.prison = true;
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[12] && caseDuJoueur.proprietaire != null) { //premiere compagnie déjà achetée
            if (caseDuJoueur.proprietaire == plateau.plateaudejeu[28].proprietaire) {
                joueurCourant.credits = joueurCourant.credits - (de1.valeur + de2.valeur)*10;
                caseDuJoueur.proprietaire.credits = caseDuJoueur.proprietaire.credits + (de1.valeur + de2.valeur)*10;
                return true;
            }
            else {
                joueurCourant.credits = joueurCourant.credits - (de1.valeur + de2.valeur)*4;
                caseDuJoueur.proprietaire.credits = caseDuJoueur.proprietaire.credits + (de1.valeur + de2.valeur)*4;
                return true;
            }
        }
        else if (caseDuJoueur == plateau.plateaudejeu[28] && caseDuJoueur.proprietaire != null) { //deuxième compagnie déjà achetée
            if (caseDuJoueur.proprietaire == plateau.plateaudejeu[12].proprietaire) {
                joueurCourant.credits = joueurCourant.credits - (de1.valeur + de2.valeur)*10;
                caseDuJoueur.proprietaire.credits = caseDuJoueur.proprietaire.credits + (de1.valeur + de2.valeur)*10;
                return true;
            }
            else {
                joueurCourant.credits = joueurCourant.credits - (de1.valeur + de2.valeur)*4;
                caseDuJoueur.proprietaire.credits = caseDuJoueur.proprietaire.credits + (de1.valeur + de2.valeur)*4;
                return true;
            }
        }
        else if (caseDuJoueur.proprietaire != null) { //autre cases déjà achetées
            joueurCourant.credits = joueurCourant.credits - caseDuJoueur.loyer;
            caseDuJoueur.proprietaire.credits = caseDuJoueur.proprietaire.credits + caseDuJoueur.loyer;
            return true;
        }
        return false;
    }
   
    
    public void payerloyer(Case caseassociee) {
        joueurCourant.credits = joueurCourant.credits-caseassociee.loyer;
        caseassociee.proprietaire.credits = caseassociee.proprietaire.credits+caseassociee.loyer;
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
