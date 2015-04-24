/**
 * 
 */
package ips.high5.cmpt594;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
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
	
	/*
	 * "No Image Selected" label
	 */
	JLabel lbl = new JLabel(); 
	
	/**
	 * The amount image is scaled
	 */
	private double scale;
	
    @Override
    protected void paintComponent(Graphics g) {
    	/*
    	 * scaled height and width
    	 */
    	int sw, sh;
    	
    	if(img == null)
    		lbl.setText("No Image Selected");
    	else{
    		sw = (int)(img.getWidth()*scale);
        	sh = (int)(img.getHeight()*scale);
        	lbl.setText("");
    		BufferedImage scaledImg = new BufferedImage(sw, sh, BufferedImage.TRANSLUCENT);
	        Graphics2D g2 = scaledImg.createGraphics();
	        super.paintComponent(g);
    		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g2.drawImage(img, 0, 0, sw, sh, null);
	        g.drawImage(scaledImg, 0, 0, null);
	        g2.dispose();
    	}
        

    }

	IpsImagePanel(){
		this.setLayout(new GridBagLayout());
		this.add(lbl);
		scale=1.0;
		
	}
	
	
	public void scaleToFit(){
		scale = Math.min((double)this.getWidth()/(double)img.getWidth(),(double)this.getHeight()/(double)img.getHeight());
		this.repaint();
	}
	
	public void zoomIn(){
		scale*=1.1;
		this.repaint();
	}
	
	public void zoomOut(){
		scale*=.9;
		this.repaint();
	}
	
	public void setScale(double s) throws Exception{
		if (s>0){
			scale = s;
		}else
			throw new Exception("Scale must be > 0");
	}
	
	public double getScale(){
		return scale;
	}
}
