/**
 * 
 */
package ips.high5.cmpt594;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Kevin
 *
 */
public class IpsFileChooser extends JFileChooser{
	
	IpsFileChooser(File dir){
		this.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
    	this.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.setAcceptAllFileFilterUsed(false);
        this.addChoosableFileFilter(new ImageFilter());
        this.setFileView(new ImageFileView());
        this.setAccessory(new ImagePreview(this));
        if (dir != null)
        	this.setCurrentDirectory(dir);
	}
	
	
}
