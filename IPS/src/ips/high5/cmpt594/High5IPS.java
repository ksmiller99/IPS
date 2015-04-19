package ips.high5.cmpt594;
/**
 *This is the main class that controls the application.
 *The Image Processing system allows a user to upload 
 *their own images and edit it them by adding different effects to the images. 
 *@author Team High Five
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class High5IPS extends JFrame {

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
    public High5IPS(){
        setTitle("High 5 Image Processing System");
        //setSize(640, 480);
        
        // Creates a menubar for a JFrame
        JMenuBar  menuBar = new JMenuBar();
        
        // Add the menubar to the frame
        setJMenuBar(menuBar);
        
        // Define and add two drop down menu to the menubar
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        
        // File Menu
        JMenuItem openAction = new JMenuItem("Open");
        JMenuItem saveAction = new JMenuItem("Save");
        JMenu saveAsMenu = new JMenu("Save as...");
        JMenuItem recentAction = new JMenuItem("Recent...");
        JMenuItem saveasGSAction = new JMenuItem("Grayscale");
        JMenuItem saveasRGBAction = new JMenuItem("Color...");
        JMenuItem newMosaicAction = new JMenuItem("New Mosaic...");
        JMenuItem closeAction = new JMenuItem("Close");
        JMenuItem propertiesAction = new JMenuItem("Properties...");
        JMenuItem exitAction = new JMenuItem("Exit");
        
        //Edit menu
        JMenuItem undoAction = new JMenuItem("Undo");
        JMenuItem redoAction = new JMenuItem("Redo");
        JMenuItem zoominAction = new JMenuItem("Zoom in");
        JMenuItem zoomoutAction = new JMenuItem("Zoom out");
        JMenuItem equalizeAction = new JMenuItem("Equalize");
        JMenuItem houghAction = new JMenuItem("Hough Transform...");
        JMenuItem blendAction = new JMenuItem("Blend with...");
        JMenuItem sharpenAction = new JMenuItem("Sharpen...");
        
        //Help menu
        JMenuItem helpAboutAction = new JMenuItem("About...");
        
        //File Actions 
        fileMenu.add(openAction);
        fileMenu.add(saveAction);
        fileMenu.add(saveAsMenu);
        saveAsMenu.add(saveasGSAction);
        saveAsMenu.add(saveasRGBAction);
        fileMenu.add(recentAction);
        fileMenu.add(newMosaicAction);
        fileMenu.add(closeAction);
        fileMenu.add(propertiesAction);
        fileMenu.addSeparator();
        fileMenu.add(exitAction);
        
        //Edit Actions
        editMenu.add(undoAction);
        editMenu.add(redoAction);
        editMenu.addSeparator();
        editMenu.add(zoominAction);
        editMenu.add(zoomoutAction);
        editMenu.addSeparator();
        editMenu.add(equalizeAction);
        editMenu.add(houghAction);
        editMenu.add(sharpenAction);
        editMenu.add(blendAction);
        
        //Help Actions
        helpMenu.add(helpAboutAction);
               
        // Open method
        openAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	openFile();
            }
        });
        
        // Save as RGB method
        saveasRGBAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	JOptionPane.showMessageDialog(null, "Color Picker will be here");                
            }
        });
        
        // Recent method
        recentAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	JOptionPane.showMessageDialog(null, "Recent file list will be here.");                
            }
        });
        
     // New Mosaic method
        newMosaicAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	JOptionPane.showMessageDialog(null, "Multi File Picker will be here");                
            }
        });
        
     // New Mosaic method
        closeAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	closeFile();                
            }
        });
        
        // Properties method
        propertiesAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	JOptionPane.showMessageDialog(null, "Properties dialog will be here");                
            }
        });
        
        // Exit method
        exitAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	System.exit(0);                
            }
        });
        
        // Hough method
        houghAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	JOptionPane.showMessageDialog(null, "Probably should be some sort of dialog box here for Hough options...");                
            }
        });
        
        // Sharpen method
        sharpenAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	JOptionPane.showMessageDialog(null, "Probably should be some sort of dialog box here for sharpen options...");                
            }
        });
        
        // Blend method
        blendAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	JOptionPane.showMessageDialog(null, "Single File Picker will be here.");                
            }
        });
        
        // Help About method
        helpAboutAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	JOptionPane.showMessageDialog(null, "Desinged in Montclair by High Five");                
            }
        });
        
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
    protected BufferedImage currentImage;

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
    protected BufferedImage[] UndoStack;
    
    protected static IPSImagePanel lPanel ;
    
    protected static IPSImagePanel rPanel;
    
    /**
     * @return If successful, places path in current path and create currentImage
     */
    protected void openFile(){
    	//Create a file chooser
    	
    	final JFileChooser fc = new JFileChooser();
    	 
    	fc.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
    	fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setAcceptAllFileFilterUsed(false);
        
        //In response to a button click:
    	int returnVal = fc.showOpenDialog(this);
    	if (returnVal == JFileChooser.APPROVE_OPTION){
    		currentImageFilePath = fc.getSelectedFile().getPath();
    		System.out.println("You chose to open this file: " + currentImageFilePath);
	       
    		try{
    			lPanel.img = ImageIO.read(new File(currentImageFilePath));
    		}catch(IOException e){
    			//TODO
    		}
    		
	        this.repaint();
	        
    		/*currentImage = Toolkit.getDefaultToolkit().getImage(currentImageFilePath);
    		MediaTracker md = new MediaTracker(this);
    		md.addImage(currentImage, 1);
    		try{
    			md.waitForAll();
    		}catch(Exception e){
    			
    		}*/
    		
    		//lImg.removeAll();
    		//lImg.setIcon(new ImageIcon(currentImage));
    	}
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
    protected static  void closeFile() {
        rPanel.img = null;
        lPanel.img = null;
        rPanel.repaint();
        lPanel.repaint();
    }

    /**
     * @return
     */
    protected void exitApplication() {
        // TODO implement here
    }
    
	public static void main(String[] args) {
	    try {
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	    } catch (Exception evt) {}

		High5IPS me = new High5IPS();
        me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //set fullscreen
        me.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        //set grid layout 1 row, 2 columns
        me.setLayout(new GridLayout(1,2));
        
        //create left and right panels for holding images
        lPanel = new IPSImagePanel();
        lPanel.setPreferredSize(new Dimension (400,600));
        lPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        lPanel.setLayout(new GridBagLayout());
        
        rPanel = new IPSImagePanel();
        rPanel.setPreferredSize(new Dimension (400,600));
        rPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        rPanel.setLayout(new GridBagLayout());

		me.getContentPane().add(lPanel);
		me.getContentPane().add(rPanel);

        me.setVisible(true);
	}


}