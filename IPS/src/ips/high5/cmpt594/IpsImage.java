package ips.high5.cmpt594;
import java.awt.event.ActionEvent;
/**
 *The description
 *of the class
 */
public abstract class IpsImage extends IpsColorImage {

    /**
     *One-sentence description ending with a period - one and only one period in description.
		 *Additional description information - as many lines as needed HTML tags OK
		 *@author Team High Five
		 *@author additional author, one line for each
		 *@custom.export N/A
		 *@custom.import N/A
		 *@custom.precondition N/A
		 *@custom.postcondition N/A
		 *@throws ipsException
		 *@param parameterName parameter description
		 *@return return description
		 */
    public IpsImage() {
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
    private int height;

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
    private int width;

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
    private boolean saved = false;

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
    private ImageType imageType;

    /**
     * @param string filePath
     */
    public void IpsImage(String filePath) {
        // TODO implement here
    }

    /**
     * @param IpsImage img
     */
    public void IpsImage(IpsImage img) {
        // TODO implement here
    }

    /**
     * @param x 
     * @param y
     */
    public void IpsImage(int x, int y) {
        // TODO implement here
    }

    /**
     * @param x 
     * @param y 
     * @return
     */
    public abstract int[] getPixel(int x, int y);

    /**
     * @param x 
     * @param y 
     * @return
     */
    public abstract IpsImage resize(int x, int y);

    /**
     * @param x 
     * @param y 
     * @param pixel 
     * @return
     */
    public abstract void setPixel(int x, int y, int[] pixel);

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
    public enum ImageType {
        grayscale,
        color
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
		 *@throws ipsException
		 *@param parameterName parameter description
		 *@return return description
		 */
    public class AssociationClass1 {

        /**
         * 
         */
        public AssociationClass1() {
        }

    }

}