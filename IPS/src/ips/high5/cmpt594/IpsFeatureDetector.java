package ips.high5.cmpt594;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * Modifies an image so that the edges are enhanced.
 * @author Cuicui Ruan - Team High 5
 */
public class  IpsFeatureDetector {

    public IpsFeatureDetector() {
    }

 	public void detectLines(BufferedImage src) {
    }
    
    /**
     * Detects and enhances edges in a BufferedImage.
     * @param src BufferedImage to be processed
     * @return BGR BufferedImage with enhanced edges
     */
    public BufferedImage detectEdges(BufferedImage src) {
        // TODO implement here
    	BufferedImage dest=new BufferedImage(src.getWidth(),src.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
    	float[] data={0.0f, -0.75f, 0.0f, -0.75f, 3.0f, -0.75f, 0.0f, -0.75f, 0.0f };
    	Kernel kernel=new Kernel(3,3,data);
        ConvolveOp co=new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP,null);
        co.filter(src, dest);
        return dest;
    }
    
    
    /**
     * Converts any BufferedImage to a grayscale type BufferedImage
     * @param src Any BufferedImage
     * @return Grayscale type BufferedImage
     */
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
    
    /**
     * Converts any BufferedImage to a binary type BufferedImage.
     * @param src any BufferedImage
     * @return binary type BufferedImage
     */
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