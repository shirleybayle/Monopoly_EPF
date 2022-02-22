/*
 * BOCAHUT Manon - FEVRIER Titouan - CHAUVET Ylan - BAYLE Shirley
 */
package monopoly_epf;

/**
 *
 * @author titou
 */
public class Plateau {
    Case [] plateaudejeu = new Case [40];
    
    public Case PresencePion(Pion piondelacellule) {
        return(piondelacellule.caseassociee);
    }
}
