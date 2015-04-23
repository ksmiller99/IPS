package ips.high5.cmpt594;

import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

/**
 *The description
 *of the class
 */
 public class Blender implements java.awt.image.BufferedImageOp{

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
    private BufferedImage topImage;

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
    private BufferedImage bottomImage;

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
    
    public static final int LINER_BLEND=1;
    public static final int DISSOLVE_BLEND=2;
    public static final int MULTIPLY_BLEND=3;
    public static final int SCREEN_BLEND=4;
    public static final int OVERLAY_BLEND=5;

    private int mode;
    
    public Blender(BufferedImage topImage, BufferedImage bottomImage,
			float opacity, int mode) {
		super();
		this.topImage = topImage;
		this.bottomImage = bottomImage;
		this.opacity = opacity;
		this.mode = mode;
	}
    
    
    public BufferedImage getTopImage() {
		return topImage;
	}


	public void setTopImage(BufferedImage topImage) {
		this.topImage = topImage;
	}


	public BufferedImage getBottomImage() {
		return bottomImage;
	}


	public void setBottomImage(BufferedImage bottomImage) {
		this.bottomImage = bottomImage;
	}


	public float getOpacity() {
		return opacity;
	}


	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}


	public int getMode() {
		return mode;
	}


	public void setMode(int mode) {
		this.mode = mode;
	}


	/**
     * @return
     */
    public void imageExchage() {
        // TODO implement here
     	BufferedImage temp;
    	temp=topImage;
    	topImage=bottomImage;
    	bottomImage=temp;            
    }

    /**
     * @return
     */
    
    //linear blend mode
    private int linearBlendMode(int x, int y, float a)
    {
    	return (int) (a*x+(1-a)*y);
    	
    }
    
    //dissolve blend mode
    private int dissolveBlendMode(int x, int y)
    {
    	return y;
 
    }
    
    //Multiply Mode
    private int multiplyBlendMode(int x, int y)
    {
    	return x*y/255;
    }
    
    private int screenBlendMode(int x, int y)
    {
    	return 255-(255-x)*(255-y)/255;
    }
    
    private int overlayBlendMode(int x, int y)
    {
    	if (y<=127) return 2*x*y;
    	else return 255-2*(255-x)*(255-y)/255;
    }
    
    private int[] getBlendData(int tr1, int tg1, int tb1, int[] input, int row, int col){
    	int width=bottomImage.getWidth();
    	int height=bottomImage.getHeight();
    	if (col>=width||row>=height){
    		return new int[]{tr1, tg1, tb1};
    	}
    	int index=row*width+col;
    	int tr=(input[index]>>16)&0xff;
    	int tg=(input[index]>>8)&0xff;
    	int tb=input[index]&0xff;
    	int[] rgb=new int[3];
    	if(mode==LINER_BLEND)
    	{
    		rgb[0]=linearBlendMode(tr1, tr, opacity);
    		rgb[1]=linearBlendMode(tg1, tg, opacity);
    		rgb[2]=linearBlendMode(tb1, tb, opacity);
    	}
    	else if(mode==DISSOLVE_BLEND)
    	{
    		
    		rgb[0]=dissolveBlendMode(tr1, tr);
    		rgb[1]=dissolveBlendMode(tg1, tg);
    		rgb[2]=dissolveBlendMode(tb1, tb);
    	}
    	else if(mode==MULTIPLY_BLEND)
    	{
    		rgb[0]=multiplyBlendMode(tr1, tr);
    		rgb[1]=multiplyBlendMode(tg1, tg);
    		rgb[2]=multiplyBlendMode(tb1, tb);
    	}
    	else if(mode==SCREEN_BLEND)
    	{
    		rgb[0]=screenBlendMode(tr1, tr);
    		rgb[1]=screenBlendMode(tg1, tg);
    		rgb[2]=screenBlendMode(tb1, tb);
    	}
    	else if(mode==OVERLAY_BLEND)
    	{
    		rgb[0]=overlayBlendMode(tr1, tr);
    		rgb[1]=overlayBlendMode(tg1, tg);
    		rgb[2]=overlayBlendMode(tb1, tb);
    	}
    	return rgb;
    }
    public BufferedImage blend(BufferedImage dest){
        // TODO implement here
    	int width=topImage.getWidth();
    	int height=topImage.getHeight();
    //	BufferedImage dest;
    	if(dest==null)     	
    		dest=createCompatibleDestImage(topImage, null);
    	
    	 int[] top=new int[width*height];
         int[] bottom=new int[bottomImage.getWidth()*bottomImage.getHeight()];
         int[] outPixels=new int[width*height];
         topImage.getRGB(0,0,width,height,top,0,width);
         bottomImage.getRGB(0,0,bottomImage.getWidth(),bottomImage.getHeight(),bottom,0,bottomImage.getWidth());
         int index = 0;  
         int ta1 = 0, tr1 = 0, tg1 = 0, tb1 = 0;  
         for(int row=0; row<height; row++) {  
             for(int col=0; col<width; col++) {  
                 index = row * width + col;  
                 ta1 = (top[index] >> 24) & 0xff;  
                 tr1 = (top[index] >> 16) & 0xff;  
                 tg1 = (top[index] >> 8) & 0xff;  
                 tb1 = top[index] & 0xff;  
                 int[] rgb = getBlendData(tr1, tg1, tb1, bottom, row, col);  
                 outPixels[index] = (ta1 << 24) | (rgb[0] << 16) | (rgb[1] << 8) | rgb[2];                     
             }  
         }
         dest.setRGB(0, 0, width,height, outPixels, 0, width);
         return dest;
    }


	@Override
	public BufferedImage createCompatibleDestImage(BufferedImage arg0,
			ColorModel arg1) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public BufferedImage filter(BufferedImage arg0, BufferedImage arg1) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Rectangle2D getBounds2D(BufferedImage arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Point2D getPoint2D(Point2D arg0, Point2D arg1) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public RenderingHints getRenderingHints() {
		// TODO Auto-generated method stub
		return null;
	}
 }