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
    boolean droitdejouer = true;
    int compteurTourPrison;
    int nbMaisonJoueur=0;
    int nbHotelJoueur=0;

    
    public boolean construire(Case casemaison, int nbmaisons) {
        if (casemaison.maison+nbmaisons<=5) {
            if(casemaison.maison+nbmaisons == 5) {
                nbHotelJoueur = nbHotelJoueur + 1;
                nbMaisonJoueur = nbMaisonJoueur +nbmaisons-1;
            }
            else nbMaisonJoueur = nbMaisonJoueur+nbmaisons;
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
