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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
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
import javax.swing.JButton;
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
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.html.ImageView;

import com.sun.glass.events.KeyEvent;

@SuppressWarnings("serial")
public class High5IPS extends JFrame {
    //save path for file chooser
    protected File CurrentDirectory = null;
	
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
        undoStack = new Stack<BufferedImage>();
        redoStack = new Stack<BufferedImage>();
        
        //Creates a menubar for a JFrame
        JMenuBar  menuBar = new JMenuBar();
        
        //Add the menubar to the frame
        setJMenuBar(menuBar);
        
        // Define and add two drop down menu to the menubar
        JMenu fileMenu = new JMenu("File");		fileMenu.setMnemonic('F');
        JMenu editMenu = new JMenu("Edit");		editMenu.setMnemonic('E');
        JMenu helpMenu = new JMenu("Help");		helpMenu.setMnemonic('H');
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        
        // File Menu
        openAction = new JMenuItem("Open");					openAction.setMnemonic('O');
        saveAction = new JMenuItem("Save");					saveAction.setMnemonic('S');
        saveAsAction = new JMenuItem("Save as...");			saveAsAction.setMnemonic('a');
        recentAction = new JMenuItem("Recent...");			recentAction.setMnemonic('R');
        newMosaicAction = new JMenuItem("New Mosaic...");	newMosaicAction.setMnemonic('M');
        closeAction = new JMenuItem("Close");				closeAction.setMnemonic('C');
        propertiesAction = new JMenuItem("Properties...");	propertiesAction.setMnemonic('P');
        exitAction = new JMenuItem("Exit");					exitAction.setMnemonic('x');
        
        //Edit menu
        undoAction = new JMenuItem("Undo");					
        undoAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.CTRL_MASK));
        redoAction = new JMenuItem("Redo");
        redoAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,InputEvent.CTRL_MASK));
        zoominAction = new JMenuItem("Zoom in");
        zoominAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS,InputEvent.CTRL_MASK));
        zoomoutAction = new JMenuItem("Zoom _out");
        zoomoutAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,InputEvent.CTRL_MASK));
        zoomFitAction = new JMenuItem("Zoom _fit");
        zoomFitAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH,InputEvent.CTRL_MASK));
        equalizeAction = new JMenuItem("Equalize");
        makeGSAction = new JMenuItem("Grayscale");
        makeRGBAction = new JMenuItem("Color...");
        houghAction = new JMenuItem("Hough Transform...");
        blendAction = new JMenuItem("Blend with...");
        sharpenAction = new JMenuItem("Sharpen...");
        
        //Help menu
         helpAboutAction = new JMenuItem("About...");
        
        //File Actions 
        fileMenu.add(openAction);
        fileMenu.add(saveAction);
        fileMenu.add(saveAsAction);
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
        editMenu.add(zoomFitAction);
        editMenu.addSeparator();
        editMenu.add(equalizeAction);
        editMenu.add(makeGSAction);
        editMenu.add(makeRGBAction);
        editMenu.add(houghAction);
        editMenu.add(sharpenAction);
        editMenu.add(blendAction);
        
        //Help Actions
        helpMenu.add(helpAboutAction);
        
        initializeMenuVisibility();
               
        // Open method
        openAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	openFile();
            }
        });
        
     // Undo method
        undoAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	redoStack.push(lPanel.img);
            	redoAction.setEnabled(true);
            	lPanel.img = rPanel.img;
            	if(undoStack.isEmpty()){
            		rPanel.img = null;
            		undoAction.setEnabled(false);
            	}
            	else
            		rPanel.img = undoStack.pop();
            	lPanel.repaint();
            	rPanel.repaint();
            }
        });
        
     // Redo method
        redoAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	if(rPanel.img != null)
            		undoStack.push(rPanel.img);
            	rPanel.img = lPanel.img;
            	undoAction.setEnabled(true);
            	lPanel.img = redoStack.pop();
            	if(redoStack.isEmpty())
            		redoAction.setEnabled(false);
            	lPanel.repaint();
            	rPanel.repaint();
            }
        });
        
     // Zoom In method
        zoominAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	lPanel.zoomIn();
            	lPanel.repaint();
            	rPanel.zoomIn();
            	rPanel.repaint();
            }
        });
        
        // Zoom Out method
        zoomoutAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	lPanel.zoomOut();
            	lPanel.repaint();
            	rPanel.zoomOut();
            	rPanel.repaint();
            }
        });
        
        // Zoom Fit method
        zoomFitAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	lPanel.scaleToFit();
            	lPanel.repaint();
            	rPanel.scaleToFit();
            	rPanel.repaint();
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
            	try {
					equalize();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}                
            }
        });
        
        // Make Grayscale method
        makeGSAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	//put right panel image onto stack
            	if(rPanel.img != null)
            		undoStack.push(rPanel.img);
            	undoAction.setEnabled(true);
            	
            	//copy left panel to the right side
            	rPanel.img = lPanel.img;
            	try {
					rPanel.setScale(lPanel.getScale());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
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
            	//JOptionPane.showMessageDialog(null, "Single File Picker will be here.");                
            	try {
					blendWith();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        // Help About method
        helpAboutAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	JOptionPane.showMessageDialog(null, "Desinged in Montclair by High Five");                
            }
        });
        
        //cc-start
        
      
      // set aligned method buttons
        JButton topLeftAligned=new JButton(new ImageIcon("images/LEFT_TOP.png"));
        JButton btmLeftAligned=new JButton(new ImageIcon("images/LEFT_BOTTOM.png"));
        JButton topRightAligned=new JButton(new ImageIcon("images/RIGHT_TOP.png"));
        JButton btmRightAligned=new JButton(new ImageIcon("images/RIGHT_BOTTOM.png"));
        JButton centerAligned=new JButton(new ImageIcon("images/CENTER.png"));
        
        topLeftAligned.setToolTipText("Top Left Aligned");
        btmLeftAligned.setToolTipText("Bottom Left Aligned");
        topRightAligned.setToolTipText("Top Right Aligned");
        btmRightAligned.setToolTipText("Bottom Right Aligned");
        centerAligned.setToolTipText("Centered");
       
        //opacity setting
        JSlider slider=new JSlider(0,100);
      //  Dimension d=slider.getPreferredSize();
      //  slider.setPreferredSize(new Dimension((int)d.getWidth()/2, (int)d.getHeight()));
        slider.setSize(new Dimension(100,20));
        slider.setPreferredSize(new Dimension(50,20));
       // System.out.println(slider.getWidth());
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
     //   slider.setLayout(new GridLayout(2,1));
     //    slider.setSnapToTicks(true); 
        slider.setPaintLabels(true);
        //
    /*    JPanel sliderPanel=new JPanel();
        GridBagConstraints gbc_sliderPanel = new GridBagConstraints();
        gbc_sliderPanel.gridx=0;
        gbc_sliderPanel.gridy=0;
        gbc_sliderPanel.weightx=50;
        gbc_sliderPanel.weighty=20;
        gbc_sliderPanel.fill = GridBagConstraints.BOTH;
        GridBagLayout.setConstraints(sliderPanel, gbc_sliderPanel);       
        sliderPanel.setPreferredSize(new Dimension(50,20));
        sliderPanel.add(slider);*/
        
        //blending option buttons
        JButton linearBtn=new JButton("Linear");
        JButton multiplyBtn=new JButton("Multiply");
        JButton screenBtn=new JButton("Screen");
        JButton overlayBtn=new JButton("Overlay");
        
        JButton resetBtn=new JButton("Reset");
        
        linearBtn.setToolTipText("Linear Blending");
        multiplyBtn.setToolTipText("Multiply Blending");
        screenBtn.setToolTipText("Screen Blending");
        overlayBtn.setToolTipText("Overlay Blending");
        
        resetBtn.setToolTipText("Reset");
        
        //add buttons to toolbar
        
        blendToolBar = new JToolBar("Blend Tool Kit");
        blendToolBar.addSeparator();
        blendToolBar.setFloatable(true);  
        blendToolBar.add(topLeftAligned);
        blendToolBar.add(btmLeftAligned);
        blendToolBar.add(topRightAligned);
        blendToolBar.add(btmRightAligned);
        blendToolBar.add(centerAligned);
        
        blendToolBar.add(slider);
       // blendToolBar.add(sliderPanel);
        
        blendToolBar.add(linearBtn);
        blendToolBar.add(multiplyBtn);
        blendToolBar.add(screenBtn);
        blendToolBar.add(overlayBtn);
        blendToolBar.add(resetBtn);
        
        
        //  setJMenuBar(blendMenuBar,);
        //JPanel p=new JPanel();
       menuBar.add(blendToolBar, BorderLayout.NORTH);
        
        // blendToolBar.setVisible(false);
       // blendToolBar.setEnabled(false);
       //alignedValue=IpsBlender.CENTER;
       //modeValue=IpsBlender.LINEAR_BLEND;
       //opacityValue=1;
      
        //set up aligned:start
        topLeftAligned.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				alignedValue=IpsBlender.LEFT_TOP;
			}
		});
        
        topRightAligned.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				alignedValue=IpsBlender.RIGHT_TOP;
			}
		});
        
        btmLeftAligned.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				alignedValue=IpsBlender.LEFT_BOTTOM;
			}
		});
        
        btmRightAligned.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				alignedValue=IpsBlender.RIGHT_BOTTOM;
			}
		});
        
        centerAligned.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				alignedValue=IpsBlender.CENTER;
			}
		});
        //sent up aligned: end
       
      slider.addChangeListener(new ChangeListener() {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			opacityValue=((float) slider.getValue())/100;
			//System.out.println(opacityValue);
			
		}
	});
      
     linearBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			ipsBlender.setMode(IpsBlender.LINEAR_BLEND);
			ipsBlender.setOpacity(opacityValue);
			ipsBlender.setAligned(alignedValue);
			lPanel.img=ipsBlender.blend();
			lPanel.repaint();
		}
	});
     multiplyBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			ipsBlender.setMode(IpsBlender.MULTIPLY_BLEND);
			ipsBlender.setAligned(alignedValue);
			lPanel.img=ipsBlender.blend();
			lPanel.repaint();
		}
	});
     screenBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			ipsBlender.setMode(IpsBlender.SCREEN_BLEND);
			ipsBlender.setAligned(alignedValue);
			lPanel.img=ipsBlender.blend();
			lPanel.repaint();
		}
	});
    overlayBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			ipsBlender.setMode(IpsBlender.OVERLAY_BLEND);
			ipsBlender.setAligned(alignedValue);
			lPanel.img=ipsBlender.blend();
			lPanel.repaint();
		}
	});
    resetBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			lPanel.img=originalBottom;
			rPanel.img=originalTop;
			lPanel.repaint();
			rPanel.repaint();
		}
	});
    
    
    }

    private int alignedValue;
  //  private int modeValue;
    private float opacityValue;
    protected IpsBlender ipsBlender;
    protected BufferedImage originalTop;
    protected BufferedImage originalBottom;
   
//cc-end
    protected static void equalize() throws Exception {
		IpsEqualizer eq = new IpsEqualizer();
		//BufferedImage eqImg = eq.equalize(lPanel.img);
		BufferedImage eqImg= eq.histogramEqualize(lPanel.img);
		if(rPanel.img != null)
    		undoStack.push(rPanel.img);
    	undoAction.setEnabled(true);
    	rPanel.img = lPanel.img;
    	rPanel.setScale(lPanel.getScale());
		lPanel.img = eqImg;
		rPanel.repaint();
		lPanel.repaint();
	}
    
    //menuitems exposed so they can be dis/enabled
    protected static JMenuItem openAction;
    protected static JMenuItem saveAction;
    protected static JMenuItem saveAsAction;
    protected static JMenuItem recentAction;
    protected static JMenuItem newMosaicAction;
    protected static JMenuItem closeAction;
    protected static JMenuItem propertiesAction;
    protected static JMenuItem exitAction;
    protected static JMenuItem undoAction;
    protected static JMenuItem redoAction;
    protected static JMenuItem zoominAction;
    protected static JMenuItem zoomoutAction;
    protected static JMenuItem zoomFitAction;
    protected static JMenuItem equalizeAction;
    protected static JMenuItem makeGSAction;
    protected static JMenuItem makeRGBAction;
    protected static JMenuItem houghAction;
    protected static JMenuItem blendAction;
    protected static JMenuItem sharpenAction;
    protected static JMenuItem helpAboutAction;   
    
    protected static JToolBar blendToolBar;
    
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
    private static Stack<BufferedImage> undoStack;
    
    private static Stack<BufferedImage> redoStack;
    
    protected static IpsImagePanel lPanel ;
    
    protected static IpsImagePanel rPanel;
    
    /**
     * @return If successful, places path in current path and create currentImage
     */
    protected void openFile(){
    	//Create a file chooser
    	
    	final JFileChooser fc = new IpsFileChooser(this.CurrentDirectory);
    	 
    	int returnVal = fc.showOpenDialog(this);
    	if (returnVal == JFileChooser.APPROVE_OPTION){
    		currentImageFilePath = fc.getSelectedFile().getPath();
    		System.out.println("You chose to open this file: " + currentImageFilePath);
	       
    		try{
    			lPanel.img = ImageIO.read(new File(currentImageFilePath));
    			rPanel.img = null;
    		}catch(IOException e){
    			e.printStackTrace();
    		}
    		this.CurrentDirectory = fc.getCurrentDirectory();
    		
    		//enable edting menus
    		makeGSAction.setEnabled(true);
    		equalizeAction.setEnabled(true);
    		blendAction.setEnabled(true);
    		closeAction.setEnabled(true);
    		zoominAction.setEnabled(true);
    		zoomoutAction.setEnabled(true);
    		zoomFitAction.setEnabled(true);
    		this.repaint();
	           		
    	}
    }
    
    protected void blendWith() throws Exception{
    	//Open a second image to blend
    	
    	final JFileChooser fc = new IpsFileChooser(this.CurrentDirectory);
    	/*
    	final JFileChooser fc = new JFileChooser();
    	 
    	fc.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
    	fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setAcceptAllFileFilterUsed(false);
        */
    	
        //In response to a button click:
    	int returnVal = fc.showOpenDialog(this);
    	if (returnVal == JFileChooser.APPROVE_OPTION){
    		currentImageFilePath = fc.getSelectedFile().getPath();
    		System.out.println("You chose to open this file: " + currentImageFilePath);
	       
    		try{
    			rPanel.img = lPanel.img;
    			originalTop=lPanel.img;
    			rPanel.setScale(lPanel.getScale());
    			lPanel.img = ImageIO.read(new File(currentImageFilePath));
    			originalBottom=lPanel.img;
    		}catch(IOException e){
    			//TODO
    		}
    		
    		this.CurrentDirectory = fc.getCurrentDirectory();
  
    		//enable edting menus   		
    		this.repaint();
    		blendToolBar.setVisible(true);
    		
    		//setup blender
    		ipsBlender=new IpsBlender(originalTop, originalBottom, opacityValue, IpsBlender.LINEAR_BLEND, IpsBlender.LEFT_TOP);
    	
    	}
    }
    
    
    
    /**
     * Initializes menu item visibility, called at start and file|close
     */
    private static void initializeMenuVisibility(){
        closeAction.setEnabled(false);
        saveAction.setEnabled(false);
        saveAsAction.setEnabled(false);
        propertiesAction.setEnabled(false);
        undoAction.setEnabled(false);
        redoAction.setEnabled(false);
        zoominAction.setEnabled(false);
        zoomoutAction.setEnabled(false);
        zoomFitAction.setEnabled(false);
        equalizeAction.setEnabled(false);
        makeGSAction.setEnabled(false);
        makeRGBAction.setEnabled(false);
        newMosaicAction.setEnabled(false);
        houghAction.setEnabled(false);
        blendAction.setEnabled(false);
        sharpenAction.setEnabled(false);
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
        initializeMenuVisibility();
    }

    /**
     * @return
     */
    protected void exitApplication() {
        // TODO implement here
    }
    //CC 
   
    
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
        lPanel = new IpsImagePanel();
        lPanel.setPreferredSize(new Dimension (400,600));
        lPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        lPanel.setLayout(new GridBagLayout());
        
        rPanel = new IpsImagePanel();
        rPanel.setPreferredSize(new Dimension (400,600));
        rPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        rPanel.setLayout(new GridBagLayout());

		me.getContentPane().add(lPanel);
		me.getContentPane().add(rPanel);

        me.setVisible(true);
	}


}