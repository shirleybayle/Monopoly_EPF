/*
 * BOCAHUT Manon - FEVRIER Titouan - CHAUVET Ylan - BAYLE Shirley
 */
package monopoly_epf;

import java.util.Random;

/**
 *
 * @author titou
 */
public class Paquet {
    Carte [] paquet = new Carte [16];
    boolean [] tabstatuts = new boolean [16];
    boolean communaute;
    boolean chance;
    
    public Paquet(boolean com) {
        if (com == true) {
            for (int i=0; i<16; i++) {
                paquet[i] = new Carte(i, true);
            }            
        }
        else {
            for (int i=0; i<16; i++) {
                paquet[i] = new Carte(15,false);
            }
        }
    }
    
    /*public void melanger() {
        Carte [] tabtemp = new Carte [16]; //création d'un paquet temporaire identique au paquet à mélanger
        for(int i=0; i<tabtemp.length; i++) {
            tabtemp[i]=paquet[i];
        }
        for(int i=0; i<paquet.length; i++) {
            paquet[i]=null; //on vide le paquet
        }
        for(int i=15; i>=0; i--) {
            Random generateur = new Random();
            int nbaléat = generateur.nextInt(i+1);
            paquet[nbaléat] = tabtemp[nbaléat]; // on tire aléatoirement des cartes à mettre à une certaine position du paquet
            if(nbaléat!=i) {
                tabtemp[nbaléat] = tabtemp[i]; // si on a pas tiré la dernière carte on la réinjecte dans la partie du tableau tirée au sort
            }
        }
    }*/
    
    public void melanger() {
        Carte [] tabtemp = new Carte [16]; //création d'un paquet temporaire identique au paquet à mélanger
        for (int i=0; i<tabtemp.length; i++) {
            tabtemp[i] = paquet[i];
        }
        for (int i =0; i<paquet.length; i++) {
            paquet[i] = null;
        }
        for (int i=15; i>=0; i--) {
            int nbaleat;
            do {
                Random generateur = new Random(); //génère un nombre
                nbaleat = generateur.nextInt(16); //entre 0 et 15 compris
            } while (tabtemp[nbaleat] == null); //tant que la carte dans le tableau à l'indice du nombre est nulle
            paquet[i] = tabtemp[nbaleat]; //place la carte dans le paquet
            tabtemp[nbaleat] = null; //remplace la carte dans le tableau temporaire par null
        }
    }
    
    public boolean paquetVide() { 
        for(int j=0; j<tabstatuts.length; j++) {
            if(tabstatuts[j]!=false) {
                return(false);
            }
        }
        return(true);
    }
    
    public void MAJtab() {
        melanger();
        for(int k=0; k<tabstatuts.length; k++) {
            tabstatuts[k] = true;
        }
    }
}
