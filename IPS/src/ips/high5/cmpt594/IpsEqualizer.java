package ips.high5.cmpt594;

import java.awt.image.BufferedImage;

/**
 * Performs histogram equalization of an image to enhance contrast.
 * @author Team High 5
 */
public class  IpsEqualizer{

    public IpsEqualizer(){
    }

    /**
     * Performs histogram equalization of an image to enhance contrast.
     * @param img Any type BufferedImage
     * @return Contract-enhanced grayscale BufferedImage
     */
    public BufferedImage histogramEqualize(BufferedImage img){
    	
    	HistogramEqualizer he = new HistogramEqualizer();
    	he.setInputImage(img);
    	he.loadRGBArrays(); //load RGB data from img into arrays for processing
    	he.makeGreyImage(); //create gray image data from RGB arrays
    	he.performEqualization();
    	
		return he.getEqualizedImage();
		
    }
    
}