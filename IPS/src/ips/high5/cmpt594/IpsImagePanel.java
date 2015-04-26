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
	/**
	 * Image processed
	 */
	BufferedImage img;
	
	/**
	 * Scaled image to display
	 */
	BufferedImage scaledImg;
	
	/**
	 * "No Image Selected" label
	 */
	JLabel lbl = new JLabel(); 
	
	/**
	 * The amount image is scaled
	 */
	private double scale;
	
    @Override
    protected void paintComponent(Graphics g) {
    	/**
    	 * scaled height and width
    	 */
    	int sw, sh;
    	
    	if(img == null){
    		lbl.setText("No Image Selected");
    		this.add(lbl);
    	}
    	else{
    		sw = (int)(img.getWidth()*scale);
        	sh = (int)(img.getHeight()*scale);
        	scaledImg=new BufferedImage(sw, sh, BufferedImage.TYPE_INT_RGB);
    		//Graphics2D g2 = scaledImg.createGraphics();
    		Graphics gr = scaledImg.createGraphics();
    		gr.drawImage(img, 0, 0, sw, sh, null);
    		gr.dispose();
	        
    	}
		super.paintComponent(g);
		if (img != null) {
			this.removeAll();
				g.drawImage(scaledImg, 0, 0, null);
		}


    }

	IpsImagePanel(){
		super();
		lbl.setText("No image selected");
		this.add(lbl);
		scale=1.0;
		
	}
	
	
	public void scaleToFit(){
		if (img !=null){
			scale = Math.min((double)this.getWidth()/(double)img.getWidth(),(double)this.getHeight()/(double)img.getHeight());
			this.repaint();
		}
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
	
	public void resetScale(){
		scale = 1;
	}
	
	public double getScale(){
		return scale;
	}
}
