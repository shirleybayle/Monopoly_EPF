/*
 * BOCAHUT Manon - FEVRIER Titouan - CHAUVET Ylan - BAYLE Shirley
 */
package monopoly_epf;

import java.util.ArrayList;

/**
 *
 * @author titou
 */
public class Joueur {
    int credits;
    Pion pion;
    ArrayList sallesPossedees = new ArrayList();
    Carte [] tabCartes = new Carte [2];
    String nom;
    boolean prison;
    int compteurTourPrison;

    
    public boolean construire(Case casemaison, int nbmaisons) {
        if (casemaison.maison+nbmaisons<5) {
            casemaison.maison = casemaison.maison+nbmaisons;
            credits = credits-casemaison.prixMaison*nbmaisons;
            return true; 
        }
        else {
            return false;
        }
    }
    
    public boolean jouerlacarte() {
        for (int i=1; i>0; i--) {
            if(tabCartes[i] != null) {
                tabCartes[i] = null;
                prison = false;
                return true;
            }
        }
        return false;
    }
    
    public void recuperercarte(Carte cartearecuperer) {
        if (cartearecuperer.texte == ""){ //carte sortir de prison
            for (int i=0; i<2; i++) {
                if (tabCartes[i] == null){
                    tabCartes[i] = cartearecuperer;
                }
            }
        }
    }
}
