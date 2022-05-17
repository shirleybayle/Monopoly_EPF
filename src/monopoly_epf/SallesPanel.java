/*
 *  BOCAHUT Manon - FEVRIER Titouan - CHAUVET Ylan - BAYLE Shirley
*/
package monopoly_epf;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author shirl
 */
public class SallesPanel extends JPanel{
    
    @Override
    public void paintComponent(Graphics g) {
        try {
            Image img = ImageIO.read(new File("src/image/Salles.png"));
            g.drawImage(img, 0, 0, 977, 723, this);
            Image pionC = ImageIO.read(new File("PionCalculatrice3_gauche"));
            Image pionD = ImageIO.read(new File("PionDiode3_gauche"));
            Image pionE = ImageIO.read(new File("PionErlenmeyer3_gauche"));
            Image pionO = ImageIO.read(new File("PionOlga3_gauche"));
            g.drawImage(pionC, 0, 0, 45, 45, this); // ça fonctionne pas ?
        } catch(IOException e){
            e.printStackTrace();
        }
        //g.fillOval(0,0,700,700); dessine un oval sur le plateau
    }
    
}
