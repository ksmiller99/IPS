/**
 * 
 */
package ips.high5.cmpt594;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Kevin
 *
 */
@SuppressWarnings("serial")
public class IpsImagePanel extends JPanel{
	BufferedImage img;
	JLabel lbl = new JLabel(); 
	
    @Override
    protected void paintComponent(Graphics g) {
    	if(img == null)
    		lbl.setText("No Image Selected");
    	else
    		lbl.setText("");
    	
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }

	IpsImagePanel(){
		this.setLayout(new GridBagLayout());
		this.add(lbl);
	}
}
