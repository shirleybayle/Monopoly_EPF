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
            boolean testDouble = lireDouble(); //gérer la carte sortie de prison
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
    
    public Carte faireActionCarteSimple(int choix) { // A VERIFIER
        if (choix == 0) { //choix=0 --> perdre des crédits
            joueurCourant.credits = joueurCourant.credits - 10;
            return null;
        }
        else { //choix=1 tirer une carte chance
            Carte laCarte = tirerCarte(paquetChance);
            return laCarte;
        }
    }
    
    public boolean faireActionChoix(Case caseChoisie, Carte carteTiree) { // A VERIFIER
        if (caseChoisie == null) {
            return false;
        }
        else {
            caseChoisie.maison = caseChoisie.maison+1; //loyer à changer
            return true;
        }
        
    }
    
    public void faireActionCarte(Carte carteTiree) {
        boolean com = carteTiree.communaute;
        int id = carteTiree.idCarte;
        if (com == true){
            if (id==0) {
                joueurCourant.credits = joueurCourant.credits-20;
                argentParcGratuit = argentParcGratuit + 20;
            }
            else if (id==1) {
                joueurCourant.credits = joueurCourant.credits-50;
                argentParcGratuit = argentParcGratuit + 50;
            }
            else if (id==2) {
                joueurCourant.pion.caseassociee = plateau.plateaudejeu[10];
                joueurCourant.prison = true;
            }
            else if (id==3) joueurCourant.recuperercarte(carteTiree);
            else if (id==5) joueurCourant.credits = joueurCourant.credits+25;
            else if (id==6) joueurCourant.credits = joueurCourant.credits-25*joueurCourant.nbMaisonJoueur-100*joueurCourant.nbHotelJoueur;
            else if (id==7) { // a verifier
                int nbJoueursRestants=0;
                for (int i=0; i<4; i++){ //calcul du nombre de joueurs restants
                    if (tabJoueurs[i] != null) nbJoueursRestants = nbJoueursRestants+1;
                }
                joueurCourant.credits = joueurCourant.credits-15*nbJoueursRestants; //cagnotte du joueur courant qui s'actualise
                for (int i=0; i<4; i++){
                    if (tabJoueurs[i] != null && tabJoueurs[i] != joueurCourant) {
                        tabJoueurs[i].credits = tabJoueurs[i].credits+15; //credits des autres joueurs qui d'actualisent
                    }
                }
            }
            else if (id==8) joueurCourant.pion.avancer(8);
            else if (id==9) {
                joueurCourant.droitdejouer = false;
            }
            else if (id==10) joueurCourant.credits = joueurCourant.credits + 150;
            else if (id==11) joueurCourant.credits = joueurCourant.credits + 100;
            else if (id==12) joueurCourant.pion.téléportation(32);  //indice 32 du plateau
            else if (id==13) { 
               int indicecase = 0;
               int indicebdo = 35;
               for (int i=0; i<40; i++) {
                   if (plateau.plateaudejeu[i] == joueurCourant.pion.caseassociee) indicecase = i;
               }
               if (indicecase-indicebdo>=0) {
                   joueurCourant.pion.avancer(indicecase-indicebdo);
               }
               else { //indice case est entre 35 et 39
                   joueurCourant.pion.avancer(36+39-indicecase); //36 = départ jusqu'à bdo ; 39-indicecase = nb cases jusqu'à case départ
               }
            }
            else if (id==14) joueurCourant.credits = joueurCourant.credits + 100;
            else if (id==15) joueurCourant.credits = joueurCourant.credits + 10;
        }
        else {
            if (id==0) {
                int nbJoueursRestants=0;
                for (int i=0; i<4; i++){ //calcul du nombre de joueurs restants
                    if (tabJoueurs[i] != null) nbJoueursRestants = nbJoueursRestants+1;
                }
                joueurCourant.credits = joueurCourant.credits-10*nbJoueursRestants; //cagnotte du joueur courant qui s'actualise
                for (int i=0; i<4; i++){
                    if (tabJoueurs[i] != null && tabJoueurs[i] != joueurCourant) {
                        tabJoueurs[i].credits = tabJoueurs[i].credits+10; //credits des autres joueurs qui d'actualisent
                    }
                }
            }
            else if (id==1) joueurCourant.pion.reculer(3);
            else if (id==2) {
                joueurCourant.pion.caseassociee = plateau.plateaudejeu[10];
                joueurCourant.prison = true;
            }
            else if (id==3) {
                joueurCourant.pion.téléportation(0);
                // la bourse n'est pas doublée
            }
            else if (id==4) {
                int indicecase = 0;
                int indicek3 = 31;
                for (int i=0; i<40; i++) {
                      if (plateau.plateaudejeu[i] == joueurCourant.pion.caseassociee) indicecase = i;
                }
                if (indicecase-indicek3>=0) {
                      joueurCourant.pion.avancer(indicecase-indicek3);
                }
                else { //indice case est entre 31 et 39
                  joueurCourant.pion.avancer(32+39-indicecase); //36 = départ jusqu'à k3 ; 39-indicecase = nb cases jusqu'à case départ
                }
            }
            else if (id==5) joueurCourant.credits = joueurCourant.credits+100;
            else if (id==6) joueurCourant.credits = joueurCourant.credits+50;
            else if (id==7) joueurCourant.recuperercarte(carteTiree);
            else if (id==8) {
                joueurCourant.credits = joueurCourant.credits-20;
                argentParcGratuit = argentParcGratuit+20;
            }
            else if (id==9) joueurCourant.credits = joueurCourant.credits+25;
            else if (id==10) {
                int indicecase = 0;
                int indicek2 = 15;
                for (int i=0; i<40; i++) {
                      if (plateau.plateaudejeu[i] == joueurCourant.pion.caseassociee) indicecase = i;
                }
                if (indicecase-indicek2>=0) {
                      joueurCourant.pion.avancer(indicecase-indicek2);
                }
                else { //indice case est entre 15 et 39
                  joueurCourant.pion.avancer(15+39-indicecase); //15 = départ jusqu'à k2 ; 39-indicecase = nb cases jusqu'à case départ
                }
            }
            else if (id==11) joueurCourant.credits = joueurCourant.credits-25*joueurCourant.nbMaisonJoueur-100*joueurCourant.nbHotelJoueur;
            else if (id==12) {
                joueurCourant.credits = joueurCourant.credits-40;
                argentParcGratuit = argentParcGratuit+40;
            }
            else if (id==13) { //téléporter tous les joueurs à la case départ
                for (int i=0; i<4; i++){ 
                    if (tabJoueurs[i] != null) tabJoueurs[i].pion.téléportation(0);
                }
            }
            else if (id==14) {
                joueurCourant.credits = joueurCourant.credits-75;
                argentParcGratuit = argentParcGratuit+75;
            }
        }
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
