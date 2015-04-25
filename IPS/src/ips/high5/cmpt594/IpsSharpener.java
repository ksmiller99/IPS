package ips.high5.cmpt594;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 *The description
 *of the class
 */
public class  IpsSharpener {

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
    public IpsSharpener() {
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
    private int sharpenLevel;


    /**
     * @return
     */
    public BufferedImage sharpen(BufferedImage src) {
        // TODO implement here
    	BufferedImage dest=new BufferedImage(src.getWidth(),src.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
        float[] data={-1.0f, -1.0f, -1.0f, -1.0f, 10.0f, -1.0f, -1.0f, -1.0f, -1.0f };
        Kernel kernel=new Kernel(3,3,data);
        ConvolveOp co=new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP,null);
        co.filter(src, dest);
        return dest;
    }

}