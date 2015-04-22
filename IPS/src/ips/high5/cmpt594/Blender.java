package ips.high5.cmpt594;
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
    private IpsImage topImage;

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
    private IpsImage bottomImage;

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
    	IpsImage temp=topImage;
    	topImage=bottomImage;
    	bottomImage=topImage;
        
    }

    /**
     * @return
     */
    public void linearBlend() {
        // TODO implement here
        
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