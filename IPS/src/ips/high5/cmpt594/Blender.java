package ips.high5.cmpt594;

import java.awt.image.BufferedImage;

/**
 *The description
 *of the class
 */
 public class Blender {

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
    public Blender() {
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
    private BufferedImage topImage;

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
    private BufferedImage bottomImage;

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
    private float opacity;


    /**
     * @return
     */
    public void imageExchage() {
        // TODO implement here
     	BufferedImage temp;
    	temp=topImage;
    	topImage=bottomImage;
    	bottomImage=temp;    
        
    }

    /**
     * @return
     */
    public void linearBlend() {
        // TODO implement here
    	 int[] top=new int[topImage.getWidth()*topImage.getHeight()];
         int[] bottom=new int[bottomImage.getWidth()*bottomImage.getHeight()];
         int[] temp=new int[topImage.getWidth()*topImage.getHeight()];
         top=topImage.getData().getPixels(0, 0, topImage.getWidth(), bottomImage.getHeight(), temp);
    }

    /**
     * @return
     */
    public void dissolveBlend() {
        // TODO implement here
        
    }

    /**
     * @return
     */
    public void multiplyBlend() {
        // TODO implement here
        
    }

    /**
     * @return
     */
    public void screenBlend() {
        // TODO implement here
        
    }

    /**
     * @return
     */
    public void overlayBlend() {
        // TODO implement here
        
    }

}