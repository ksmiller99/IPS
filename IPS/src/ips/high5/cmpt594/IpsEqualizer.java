package ips.high5.cmpt594;

import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;

/**
 *The description
 *of the class
 */
public class  IpsEqualizer{

    /**
     *One-sentence description ending with a period - one and only one period in description.
		 *Additional description information - as many lines as needed HTML tags OK
		 *@author Team High Five
		 *@author additional author, one line for each
		 *@custom.export N/A
		 *@custom.import N/A
		 *@custom.precondition N/A
		 *@custom.postcondition N/A
		 *@throws 
		 *@param parameterName parameter description
		 *@return return description
		 */
    public IpsEqualizer(){
    }


    public BufferedImage histogramEqualize(BufferedImage img){
    	
    	BufferedImage greyImage;
    	
    	HistogramEqualizer he = new HistogramEqualizer();
    	he.setInputImage(img);
    	he.loadRGBArrays(); //load RGB data from img into arrays for processing
    	he.makeGreyImage(); //create gray image data from RGB arrays
    	//greyImage = he.getGreyImage();
    	he.performEqualization();
    	
		return he.getEqualizedImage();
		
    }
    
    /**
     * @param img 
     * @return
     */
    public BufferedImage equalize(BufferedImage img) {
    	
    	int minGray = 255;
    	int maxGray = 0;
    	int wd = img.getWidth();
    	int ht = img.getHeight();
    	int p;			//packed rgb pixel RRGGBB
    	short rd;		//red part of p
    	
    	for(int r=0; r<ht; ++r){
    		for(int c=0; c<wd; ++c){
    			p=img.getRGB(c, r);
    			//for grayscale, red = blue = green, so any color can be used
    			rd = (short) ((p >> 16) & 0xFF);

    			if(rd>maxGray)
    				maxGray=rd;
    			if(rd<minGray)
    				minGray=rd;
    		}
    	}
    	
    	System.out.println("Max: "+maxGray);
    	System.out.println("Min: "+minGray);
    	
    	
    	
    	int offset = minGray;
    	double scale = 255/(maxGray-minGray);
    	
    	for(int r=0; r<ht; ++r){
    		for(int c=0; c<wd; ++c){
    			p=img.getRGB(c, r);
    			rd = (short) ((p >> 16) & 0xFF);
    			rd = (short) ((rd-offset)*scale);
    			if(rd>255) rd = 255;
    			p=(int)rd << 16;
    			p+=(int)rd << 8;
    			p+=(int)rd;
    			img.setRGB(c, r, p);
    		}
    	}
    	
    	return img;
        
        
    }

}