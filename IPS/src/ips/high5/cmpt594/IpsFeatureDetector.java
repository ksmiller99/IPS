package ips.high5.cmpt594;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

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
    public BufferedImage detectLines(BufferedImage src) {
        // TODO implement here
        BufferedImage dest=this.binaryImage(src);
        
        
        return dest;
    }

    /**
     * @return
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