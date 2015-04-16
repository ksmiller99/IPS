package Ips;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class HelloGit extends JFrame{
	public HelloGit() {
        
        setTitle("Image Processing System");
        setSize(640, 480);
        
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
            	JOptionPane.showMessageDialog(null, "You have clicked on the Open action");
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
	
	public static void main(String[] args) {
		
		System.out.println("Kevin's laptop here!");
		System.out.println("Kevin's work PC here!");
		System.out.println("Cici is here");
		System.out.println("Andrew too.");
		System.out.println("ZUP!!");
		System.out.println("Grrrrr!!");		
		
		HelloGit me = new HelloGit();
        me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        me.setVisible(true);
       

	}

}
