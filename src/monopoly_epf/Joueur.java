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
    
    public boolean jouerlacarte(Carte cartejoueur) {
        
    }
    
    public void recuperercarte(Carte cartearecuperer) {
        
    }
}
