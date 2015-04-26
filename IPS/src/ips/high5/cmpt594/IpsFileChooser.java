/**
 * 
 */
package ips.high5.cmpt594;

import java.io.File;

import javax.swing.JFileChooser;

/**
 * Allows user to select an image file from the host file system.
 * Saves the directory in main application's currentDirectory.
 * @author Team High 5
 *
 */
@SuppressWarnings("serial")
public class IpsFileChooser extends JFileChooser{
	
	IpsFileChooser(File dir){
		this.setAcceptAllFileFilterUsed(false);
        this.addChoosableFileFilter(new ImageFilter());
        this.setFileView(new ImageFileView());
        this.setAccessory(new ImagePreview(this));
        if (dir != null)
        	this.setCurrentDirectory(dir);
	}
	
	
}
