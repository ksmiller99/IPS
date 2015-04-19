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
import java.awt.Graphics;
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
import java.util.Stack;

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
        
        //initialize 
        undoStack = new Stack();
        
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
        openAction = new JMenuItem("Open");
        saveAction = new JMenuItem("Save");
        saveAction.setEnabled(false);
        JMenu saveAsMenu = new JMenu("Save as...");
        saveAsMenu.setEnabled(false);
        recentAction = new JMenuItem("Recent...");
        recentAction.setEnabled(false);
        newMosaicAction = new JMenuItem("New Mosaic...");
        newMosaicAction.setEnabled(false);
        closeAction = new JMenuItem("Close");
        closeAction.setEnabled(false);
        propertiesAction = new JMenuItem("Properties...");
        propertiesAction.setEnabled(false);
        exitAction = new JMenuItem("Exit");
        
        //Edit menu
        undoAction = new JMenuItem("Undo");
        undoAction.setEnabled(false);
        redoAction = new JMenuItem("Redo");
        redoAction.setEnabled(false);
        zoominAction = new JMenuItem("Zoom in");
        zoominAction.setEnabled(false);
        zoomoutAction = new JMenuItem("Zoom out");
        zoomoutAction.setEnabled(false);
        equalizeAction = new JMenuItem("Equalize");
        equalizeAction.setEnabled(false);
        makeGSAction = new JMenuItem("Grayscale");
        makeGSAction.setEnabled(false);
        makeRGBAction = new JMenuItem("Color...");
        makeRGBAction.setEnabled(false);
        houghAction = new JMenuItem("Hough Transform...");
        houghAction.setEnabled(false);
        blendAction = new JMenuItem("Blend with...");
        blendAction.setEnabled(false);
        sharpenAction = new JMenuItem("Sharpen...");
        sharpenAction.setEnabled(false);
        
        //Help menu
         helpAboutAction = new JMenuItem("About...");
        
        //File Actions 
        fileMenu.add(openAction);
        fileMenu.add(saveAction);
        fileMenu.add(saveAsMenu);
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
        editMenu.add(makeGSAction);
        editMenu.add(makeRGBAction);
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
        
     // File|Close method
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
        
        // Edit|Equalize method
        equalizeAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	equalize();                
            }
        });
        
        // Make Grayscale method
        makeGSAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	//put right panel image onto stack
            	if(rPanel.img != null)
            		undoStack.push(rPanel.img);
            	
            	//copy left panel to the right side
            	rPanel.img = lPanel.img;
            	
            	BufferedImage gsImg = new BufferedImage(lPanel.img.getWidth(),lPanel.img.getHeight(),BufferedImage.TYPE_BYTE_GRAY);
            	Graphics g = gsImg.getGraphics();
            	g.drawImage(lPanel.img, 0, 0, null);  
            	g.dispose();
            	lPanel.img = gsImg;
            	rPanel.repaint();
            	lPanel.repaint();
            }
        });
        
        
        
     // Make RGB method
        makeRGBAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	JOptionPane.showMessageDialog(null, "Color Picker will be here");                
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

    protected static void equalize() {
		Equalizer eq = new Equalizer();
		
		
	}
    
    //menuitems exposed so they can be dis/enabled
    protected JMenuItem openAction;
    protected JMenuItem saveAction;
    protected JMenuItem recentAction;
    protected JMenuItem newMosaicAction;
    protected JMenuItem closeAction;
    protected JMenuItem propertiesAction;
    protected JMenuItem exitAction;
    protected JMenuItem undoAction;
    protected JMenuItem redoAction;
    protected JMenuItem zoominAction;
    protected JMenuItem zoomoutAction;
    protected JMenuItem equalizeAction;
    protected static JMenuItem makeGSAction;
    protected JMenuItem makeRGBAction;
    protected JMenuItem houghAction;
    protected JMenuItem blendAction;
    protected JMenuItem sharpenAction;
    protected JMenuItem helpAboutAction;   
    
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
    private Stack<BufferedImage> undoStack;
    
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
    			rPanel.img = null;
    		}catch(IOException e){
    			//TODO
    		}
    		
    		//enable edting menus
    		makeGSAction.setEnabled(true);
	        
    		this.repaint();
	           		
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
        makeGSAction.setEnabled(false);
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