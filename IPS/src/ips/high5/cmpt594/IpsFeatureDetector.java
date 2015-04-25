package ips.high5.cmpt594;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.Vector;

/**
 *The description
 *of the class
 */
public class  IpsFeatureDetector {

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
    public IpsFeatureDetector() {
    }

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
    public IpsBinaryImage featuresImage;


	/**
     * @return
     */
    public void detectLines(BufferedImage src) {
        // TODO implement here
        //BufferedImage dest=this.binaryImage(src);
        
        /*HoughTransform(src.getWidth(), src.getHeight()); 
        
        // add the points from the image (or call the addPoint method separately if your points are not in an image 
        addPoints(src); 
 
        // get the lines out 
        Vector<HoughLine> lines = getLines(30); 
 
        // draw the lines back onto the image 
        for (int j = 0; j < lines.size(); j++) { 
            HoughLine line = lines.elementAt(j); 
            line.draw(src, Color.RED.getRGB()); 
        } 
        //return dest;
*/    }
    
    

    /**
     * @return
     */
    public BufferedImage detectEdges(BufferedImage src) {
        // TODO implement here
    	BufferedImage dest=new BufferedImage(src.getWidth(),src.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
        // GaussLaplacian
    	float[] data={-2, -4, -4, -4, -2,-4, 0, 8, 0, -4,-4, 8, 24, 8, -4,-4, 0, 8, 0, -4,-2, -4, -4, -4, -2 };
        Kernel kernel=new Kernel(5,5,data);
        ConvolveOp co=new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP,null);
        co.filter(src, dest);
        return dest;
    }
    
    

    public BufferedImage greyImage(BufferedImage src)
    {
    	BufferedImage dest=new BufferedImage(src.getWidth(), src.getHeight(),BufferedImage.TYPE_BYTE_GRAY);
    	for (int i=0; i<src.getWidth();i++)
    		for (int j=0; j<src.getHeight();j++)
    		{
    			int rgb=src.getRGB(i, j);
    			dest.setRGB(i, j, rgb);
    		}
    	return dest;
    }
    public BufferedImage binaryImage(BufferedImage src)
    {
    	BufferedImage dest=new BufferedImage(src.getWidth(),src.getHeight(),BufferedImage.TYPE_BYTE_BINARY);
    	for(int i=0;i<src.getWidth();i++)
    		for (int j=0;j<src.getHeight();j++)
    		{
    			int rgb=src.getRGB(i, j);
    			dest.setRGB(i, j, rgb);
    		}
    	return dest;
    }
}