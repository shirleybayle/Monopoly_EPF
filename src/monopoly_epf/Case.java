/*
 * BOCAHUT Manon - FEVRIER Titouan - CHAUVET Ylan - BAYLE Shirley
 */
package monopoly_epf;

/**
 *
 * @author shirl
 */
public class Case {
    String nom;
    int prixTerrain;
    String couleur;
    int maison;
    Joueur proprietaire;
    Pion occupant;
    int loyer;
    String action;
    int prixMaison;
    int idCase;
    
    public Case(int id) {
        
    }
    
    
    public int lirePrixTerrain(){
        return prixTerrain;
    }
    
    public int lireLoyer() {
        return loyer;
    }
    
    public void changerLoyer(int nouveauloyer) {
        loyer = nouveauloyer;
    }
    
    public int lirePrixMaison(){
        return prixMaison;
    }
    
    public boolean construireMaison(int nbMaison) {
        if (maison+nbMaison > 5){
            return false;
        }
        else {
            maison = maison+nbMaison;
            return true;
        }
    }
    
    public Joueur lireProprio(){
        return proprietaire;
    }
    
    public String lireAction(){
        return action;
    }
}
