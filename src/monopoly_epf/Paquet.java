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
    Boolean [] tabstatuts = new Boolean [16];
    
    public void melanger() {
        Carte [] tabtemp = new Carte [16]; //création d'un paquet temporaire identique au paquet à mélanger
        for(int i=0; i<tabtemp.length; i++) {
            tabtemp[i]=paquet[i];
        }
        for(int i=0; i<paquet.length; i++) {
            paquet[i]=null; //on vide le paquet
        }
        for(int i=19; i>0; i--) {
            Random generateur = new Random();
            int nbaléat = generateur.nextInt(i);
            paquet[nbaléat] = tabtemp[nbaléat]; // on tire aléatoirement des cartes à mettre à une certaine position du paquet
            if(nbaléat!=i) {
                tabtemp[nbaléat] = tabtemp[i]; // si on a pas tiré la dernière carte on la réinjecte dans la partie du tableau tirée au sort
            }
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
