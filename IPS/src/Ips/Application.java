package Ips;
/**
 *This is the main class that controls the application.
 *The Image Processing system allows a user to upload 
 *their own images and edit it them by adding different effects to the images. 
 *@author Team High Five
 */
public class Application {

    /**
     *The constructor is a top-level class that takes no arguments.
		 *@author Team High Five
		 *@custom.export N/A
		 *@custom.import N/A
		 *@custom.precondition N/A
		 *@custom.postcondition N/A
		 *@throws N/A
		 *@param parameterName parameter description
		 *@return return description
		 */
    public Application() {
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
    protected IpsImage currentImage;

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
    protected String currentImageFilePath;


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
    public UndoStack contains;

    /**
     * @return If successful, places path in current path and create currentImage
     */
    protected void openFile() {
        // TODO implement here
    }

    /**
     * @return
     */
    protected void saveFile() {
        // TODO implement here
    }

    /**
     * @return
     */
    protected void saveFileAs() {
        // TODO implement here
    }

    /**
     * @return
     */
    protected void openRecentFile() {
        // TODO implement here
    }

    /**
     * @return
     */
    protected void showProperties() {
        // TODO implement here
    }

    /**
     * @return
     */
    protected void newMosaic() {
        // TODO implement here
    }

    /**
     * @return
     */
    protected void closeFile() {
        // TODO implement here
    }

    /**
     * @return
     */
    protected void exitApplication() {
        // TODO implement here
    }

}