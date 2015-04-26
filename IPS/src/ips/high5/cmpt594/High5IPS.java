package ips.high5.cmpt594;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import com.sun.glass.events.KeyEvent;


/**
 *The main class that controls the application.
 *The High-5 Image Processing System allows a user to select 
 *their own images and edit them them by adding different effects to the images.
 *This is an unfinished Alpha release. 
 *@author Team High Five
 *@version 0.1
 */
@SuppressWarnings("serial")
public class High5IPS extends JFrame {
	/**
	 * Directory file chooser opens in.
	 */
	protected File CurrentDirectory = null;
	
	/**
	 * Default title in application title bar.
	 */
	static String defaultTitle = "High 5 Image Processing System";
	
	/**
	 * Object that controls blending.
	 */
	protected IpsBlender ipsBlender;
	
	/**
	 * Used in blender for how the two images are aligned for blending.
	 */
	private int alignedValue;
	
	/**
	 * Used in blender to track opacity.
	 */
	private float opacityValue;
	
	/**
	 * Used by blender when user selects Reset.
	 */
	protected BufferedImage originalTop;
	
	/**
	 * Used by blender when user selects Reset.
	 */
	protected BufferedImage originalBottom;

	// menuitems exposed so they can be dis/enabled
	JMenuBar menuBar;
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
	protected static JPopupMenu blendPopup;

	/**
	 * The image currently being processed and displayed in the left pane.
	 */
	protected BufferedImage currentImage;

	/**
	 * The directory the file chooser opens to.
	 */
	protected String currentImageFilePath;

	/**
	 * Stack of BufferedImage used in undo & redo.
	 */
	private static Stack<BufferedImage> undoStack;

	/**
	 * Stack of BufferedImage used in undo & redo.
	 */
	private static Stack<BufferedImage> redoStack;

	/**
	 * Panel on left holding image being processed.
	 */
	protected static IpsImagePanel lPanel;

	/**
	 * Panel on right holding previous version of image being processed.
	 */
	protected static IpsImagePanel rPanel;



	/**
	 * The constructor is a top-level class that takes no arguments.
	 *
	 * @custom.export N/A
	 * @custom.precondition Java Runtime 1.7 installed
	 * @throws N/A
	 */
	public High5IPS() {
		setTitle(defaultTitle);

		// initialize
		undoStack = new Stack<BufferedImage>();
		redoStack = new Stack<BufferedImage>();

		menuBar = new JMenuBar();

		setJMenuBar(menuBar);

		// Define and add two drop down menu to the menubar
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic('E');
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);

		// File Menu
		openAction = new JMenuItem("Open");
		openAction.setMnemonic('O');
		
		saveAction = new JMenuItem("Save");
		saveAction.setMnemonic('S');
		
		saveAsAction = new JMenuItem("Save as...");
		saveAsAction.setMnemonic('a');
		
		recentAction = new JMenuItem("Recent...");
		recentAction.setMnemonic('R');
		
		newMosaicAction = new JMenuItem("New Mosaic...");
		newMosaicAction.setMnemonic('M');
		
		closeAction = new JMenuItem("Close");
		closeAction.setMnemonic('C');
		propertiesAction = new JMenuItem("Properties...");
		propertiesAction.setMnemonic('P');
		exitAction = new JMenuItem("Exit");
		exitAction.setMnemonic('x');

		// Edit menu
		undoAction = new JMenuItem("Undo");
		undoAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		redoAction = new JMenuItem("Redo");
		redoAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
		zoominAction = new JMenuItem("Zoom in");
		zoominAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, InputEvent.CTRL_MASK));
		zoomoutAction = new JMenuItem("Zoom _out");
		zoomoutAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_MASK));
		zoomFitAction = new JMenuItem("Zoom _fit");
		zoomFitAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, InputEvent.CTRL_MASK));
		equalizeAction = new JMenuItem("Equalize");
		makeGSAction = new JMenuItem("Grayscale");
		makeRGBAction = new JMenuItem("Color...");
		houghAction = new JMenuItem("Hough Transform...");// lineDtcAction=new
															// JMenuItem("Line Detection");
															// edgeDtcAction=new
															// JMenuItem("Edge Detection");
		blendAction = new JMenuItem("Blend with...");
		sharpenAction = new JMenuItem("Sharpen...");

		// Help menu
		helpAboutAction = new JMenuItem("About...");

		// File Actions
		fileMenu.add(openAction);
		fileMenu.add(saveAction);
		fileMenu.add(saveAsAction);
		fileMenu.add(recentAction);
		fileMenu.add(newMosaicAction);
		fileMenu.add(closeAction);
		fileMenu.add(propertiesAction);
		fileMenu.addSeparator();
		fileMenu.add(exitAction);

		// Edit Actions
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

		// Help Actions
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
				if (undoStack.isEmpty()) {
					rPanel.img = null;
					rPanel.resetScale();
					undoAction.setEnabled(false);
				} else
					rPanel.img = undoStack.pop();

				// for blendWith
				originalBottom = lPanel.img;
				originalTop = rPanel.img;

				lPanel.repaint();
				rPanel.repaint();
			}
		});

		// Redo method
		redoAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rPanel.img != null)
					undoStack.push(rPanel.img);
				rPanel.img = lPanel.img;
				undoAction.setEnabled(true);
				lPanel.img = redoStack.pop();
				if (redoStack.isEmpty())
					redoAction.setEnabled(false);

				// for blendWith
				originalBottom = lPanel.img;
				originalTop = rPanel.img;

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
					e.printStackTrace();
				}
			}
		});

		// Make Grayscale method
		makeGSAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// put right panel image onto stack
				if (rPanel.img != null)
					undoStack.push(rPanel.img);
				undoAction.setEnabled(true);

				// copy left panel to the right side
				try {
					rPanel.setScale(lPanel.getScale());
				} catch (Exception e) {
					e.printStackTrace();
				}

				BufferedImage gsImg = new BufferedImage(lPanel.img.getWidth(), lPanel.img.getHeight(),
						BufferedImage.TYPE_BYTE_GRAY);
				Graphics g = gsImg.getGraphics();
				g.drawImage(lPanel.img, 0, 0, null);
				g.dispose();
				undoStack.push(rPanel.img);
				undoAction.setEnabled(true);
				rPanel.img = lPanel.img;
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
				// JOptionPane.showMessageDialog(null,
				// "Probably should be some sort of dialog box here for Hough options...");
				BufferedImage eImage = new IpsFeatureDetector().detectEdges(lPanel.img);
				undoStack.push(rPanel.img);
				undoAction.setEnabled(true);
				rPanel.img = lPanel.img;
				lPanel.img = eImage;
				rPanel.repaint();
				lPanel.repaint();
			}
		});

		// Sharpen method
		sharpenAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// BufferedImage sImage=new
				// BufferedImage(lPanel.img.getWidth(),lPanel.img.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
				BufferedImage sImage = new IpsSharpener().sharpen(lPanel.img);
				undoStack.push(rPanel.img);
				undoAction.setEnabled(true);
				rPanel.img = lPanel.img;
				lPanel.img = sImage;
				rPanel.repaint();
				lPanel.repaint();

			}
		});

		// Blend method
		blendAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					blendWith();
				} catch (Exception e) {
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

		// cc-start

		// set aligned method buttons
		JButton topLeftAligned = new JButton(new ImageIcon("images/LEFT_TOP.png"));
		JButton btmLeftAligned = new JButton(new ImageIcon("images/LEFT_BOTTOM.png"));
		JButton topRightAligned = new JButton(new ImageIcon("images/RIGHT_TOP.png"));
		JButton btmRightAligned = new JButton(new ImageIcon("images/RIGHT_BOTTOM.png"));
		JButton centerAligned = new JButton(new ImageIcon("images/CENTER.png"));

		topLeftAligned.setToolTipText("Top Left Aligned");
		btmLeftAligned.setToolTipText("Bottom Left Aligned");
		topRightAligned.setToolTipText("Top Right Aligned");
		btmRightAligned.setToolTipText("Bottom Right Aligned");
		centerAligned.setToolTipText("Centered");

		// opacity setting
		JSlider slider = new JSlider(0, 100);

		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(5);
		slider.setPaintLabels(true);
		
		// blending option buttons
		JButton linearBtn = new JButton("Linear");
		JButton multiplyBtn = new JButton("Multiply");
		JButton screenBtn = new JButton("Screen");
		JButton overlayBtn = new JButton("Overlay");

		JButton resetBtn = new JButton("Reset");

		JButton swapBtn = new JButton("Swap");

		linearBtn.setToolTipText("Linear Blending");
		multiplyBtn.setToolTipText("Multiply Blending");
		screenBtn.setToolTipText("Screen Blending");
		overlayBtn.setToolTipText("Overlay Blending");

		resetBtn.setToolTipText("Reset");
		swapBtn.setToolTipText("swap left and right");

		// add buttons to toolbar
		blendToolBar = new JToolBar("Blend Tool Kit");
		blendToolBar.addSeparator();
		blendToolBar.setFloatable(true);
		blendToolBar.add(topLeftAligned);
		blendToolBar.add(btmLeftAligned);
		blendToolBar.add(topRightAligned);
		blendToolBar.add(btmRightAligned);
		blendToolBar.add(centerAligned);

		blendToolBar.add(slider);

		blendToolBar.add(linearBtn);
		blendToolBar.add(multiplyBtn);
		blendToolBar.add(screenBtn);
		blendToolBar.add(overlayBtn);
		blendToolBar.add(resetBtn);
		blendToolBar.add(swapBtn);

		blendPopup = new JPopupMenu();
		blendPopup.add(blendToolBar);
		blendPopup.addPopupMenuListener(new PopupMenuListener() {

			@Override
			public void popupMenuCanceled(PopupMenuEvent arg0) {
				System.out.println("canceled");
				menuBar.setEnabled(true);
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				System.out.println("Invisible");
				menuBar.setEnabled(true);
			}

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				System.out.println("Visible");
				menuBar.setEnabled(false);
			}
		});

		topLeftAligned.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				alignedValue = IpsBlender.LEFT_TOP;
			}
		});

		topRightAligned.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				alignedValue = IpsBlender.RIGHT_TOP;
			}
		});

		btmLeftAligned.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				alignedValue = IpsBlender.LEFT_BOTTOM;
			}
		});

		btmRightAligned.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				alignedValue = IpsBlender.RIGHT_BOTTOM;
			}
		});

		centerAligned.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				alignedValue = IpsBlender.CENTER;
			}
		});
		
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				opacityValue = ((float) slider.getValue()) / 100;
				// System.out.println(opacityValue);

			}
		});

		linearBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ipsBlender.setMode(IpsBlender.LINEAR_BLEND);
				ipsBlender.setOpacity(opacityValue);
				ipsBlender.setAligned(alignedValue);
				lPanel.img = ipsBlender.blend();
				lPanel.repaint();
			}
		});
		multiplyBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ipsBlender.setMode(IpsBlender.MULTIPLY_BLEND);
				ipsBlender.setAligned(alignedValue);
				lPanel.img = ipsBlender.blend();
				lPanel.repaint();
			}
		});
		screenBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ipsBlender.setMode(IpsBlender.SCREEN_BLEND);
				ipsBlender.setAligned(alignedValue);
				lPanel.img = ipsBlender.blend();
				lPanel.repaint();
			}
		});
		overlayBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ipsBlender.setMode(IpsBlender.OVERLAY_BLEND);
				ipsBlender.setAligned(alignedValue);
				lPanel.img = ipsBlender.blend();
				lPanel.repaint();
			}
		});

		resetBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lPanel.img = originalBottom;
				rPanel.img = originalTop;
				lPanel.repaint();
				rPanel.repaint();
			}
		});

		swapBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BufferedImage temp = rPanel.img;
				rPanel.img = lPanel.img;
				lPanel.img = temp;

				temp = originalBottom;
				originalBottom = originalTop;
				originalTop = temp;

				lPanel.repaint();
				rPanel.repaint();
			}
		});

	}

	/**
	 * Performs Histogram Equalization on currentImage.
	 * @throws Exception
	 */
	protected static void equalize() throws Exception {
		IpsEqualizer eq = new IpsEqualizer();
		BufferedImage eqImg = eq.histogramEqualize(lPanel.img);
		if (rPanel.img != null)
			undoStack.push(rPanel.img);
		undoAction.setEnabled(true);
		rPanel.img = lPanel.img;
		rPanel.setScale(lPanel.getScale());
		lPanel.img = eqImg;
		rPanel.repaint();
		lPanel.repaint();
	}

	/**
	 * Places path in currentPath and loads currentImage from file selected by user.
	 */
	protected void openFile() {
		// Create a file chooser

		final JFileChooser fc = new IpsFileChooser(this.CurrentDirectory);
		File newFile;

		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			currentImageFilePath = fc.getSelectedFile().getPath();
			System.out.println("You chose to open this file: " + currentImageFilePath);

			try {
				newFile = new File(currentImageFilePath);
				lPanel.img = ImageIO.read(newFile);
				lPanel.resetScale();
				rPanel.img = null;
				rPanel.resetScale();
				this.setTitle(defaultTitle + " - " + newFile.getName());
				this.CurrentDirectory = fc.getCurrentDirectory();
				undoStack.clear();
				redoStack.clear();
			} catch (IOException e) {
				e.printStackTrace();
			}

			currentImage = lPanel.img;
			originalTop = lPanel.img;

			// enable edting menus
			makeGSAction.setEnabled(true);
			equalizeAction.setEnabled(true);
			blendAction.setEnabled(true);
			closeAction.setEnabled(true);
			zoominAction.setEnabled(true);
			zoomoutAction.setEnabled(true);
			zoomFitAction.setEnabled(true);
			sharpenAction.setEnabled(true);
			houghAction.setEnabled(true);
			this.repaint();

		}
	}

	/**
	 * Allows user to select an image in a file and blend with currentImage.
	 * @throws Exception
	 */
	protected void blendWith() throws Exception {
		// Open a second image to blend

		final JFileChooser fc = new IpsFileChooser(this.CurrentDirectory);

		// In response to a button click:
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			currentImageFilePath = fc.getSelectedFile().getPath();
			System.out.println("You chose to open this file: " + currentImageFilePath);

			try {
				blendPopup.show(this, 100, 100);
				if (rPanel.img != null)
					undoStack.push(rPanel.img);
				rPanel.img = lPanel.img;
				undoAction.setEnabled(true);
				originalTop = lPanel.img;
				rPanel.setScale(lPanel.getScale());
				lPanel.img = ImageIO.read(new File(currentImageFilePath));
				originalBottom = lPanel.img;
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}

			this.CurrentDirectory = fc.getCurrentDirectory();

			// enable edting menus
			this.repaint();
			blendToolBar.setVisible(true);

			// setup blender
			ipsBlender = new IpsBlender(originalTop, originalBottom, opacityValue, IpsBlender.LINEAR_BLEND,
					IpsBlender.LEFT_TOP);

		}
	}

	/**
	 * Initializes menu item visibility, called at start and file|close.
	 */
	private static void initializeMenuVisibility() {
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
	 * Allows user to save currentImage (unimplemented).
	 */
	protected void saveFile() {
		// TODO implement here
	}

	/**
	 * Allows user to save currentImage with a different name or type (unimplemented).
	 */
	protected void saveFileAs() {
		// TODO implement here
	}

	/**
	 * Allows user to open an image file that was opened recently (unimplemented).
	 */
	protected void openRecentFile() {
		// TODO implement here
	}

	/**
	 * Display properties of currentImage (Unimplemented).
	 */
	protected void showProperties() {
		// TODO implement here
	}

	/**
	 * Allows user to stitch images together where their edges match (unimplemented).
	 */
	protected void newMosaic() {
		// TODO implement here
	}

	/**
	 * Closes all current images, clears the undo & redo stacks, resets menus
	 * to initial state.
	 */
	protected void closeFile() {
		rPanel.img = null;
		rPanel.resetScale();
		lPanel.img = null;
		lPanel.resetScale();
		rPanel.repaint();
		lPanel.repaint();
		undoStack.clear();
		redoStack.clear();
		initializeMenuVisibility();
		this.setTitle(defaultTitle);
	}

	/**
	 * Main method for application.
	 * @param args None
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception evt) {
		}

		High5IPS me = new High5IPS();
		me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set fullscreen
		me.setExtendedState(JFrame.MAXIMIZED_BOTH);

		// set grid layout 1 row, 2 columns
		me.setLayout(new GridLayout(1, 2));

		// create left and right panels for holding images
		lPanel = new IpsImagePanel();
		lPanel.setPreferredSize(new Dimension(400, 600));
		lPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		lPanel.setLayout(new GridBagLayout());

		rPanel = new IpsImagePanel();
		rPanel.setPreferredSize(new Dimension(400, 600));
		rPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		rPanel.setLayout(new GridBagLayout());

		me.getContentPane().add(lPanel);
		me.getContentPane().add(rPanel);

		me.setVisible(true);
	}

}