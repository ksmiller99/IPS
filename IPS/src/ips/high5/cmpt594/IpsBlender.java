package ips.high5.cmpt594;

import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.Arrays;

/**
 *The description
 *of the class
 */
 public class IpsBlender implements java.awt.image.BufferedImageOp{

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
    
    public static final int LINEAR_BLEND=1;  
    public static final int MULTIPLY_BLEND=2;
    public static final int SCREEN_BLEND=3;
    public static final int OVERLAY_BLEND=4;
    
    public static final int LEFT_TOP=1;
    public static final int LEFT_BOTTOM=2;
    public static final int RIGHT_TOP=3;
    public static final int RIGHT_BOTTOM=4;
    public static final int CENTER=5;

    private int mode;
    private int aligned;
    
    public IpsBlender(BufferedImage topImage, BufferedImage bottomImage,
			float opacity, int mode, int aligned) {
		super();
		this.topImage = topImage;
		this.bottomImage = bottomImage;
		this.opacity = opacity;
		this.mode = mode;
		this.aligned=aligned;
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


	public int getAligned() {
		return aligned;
	}


	public void setAligned(int aligned) {
		this.aligned = aligned;
	}


	/**
     * @return
     */
  /*  public void imageExchage() {
        // TODO implement here
     	BufferedImage temp;
    	temp=topImage;
    	topImage=bottomImage;
    	bottomImage=temp;            
    }*/

    /**
     * @return
     */
    
    //linear blend mode
    private int linearBlendMode(int x, int y, float a)
    {
    	return (int) (a*x+(1-a)*y);
    	
    }
    
    //dissolve blend mode
    /*private int dissolveBlendMode(int x, int y)
    {
    	return y;
 
    }*/
    
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
    	if (y<=127) return 2*x*y/255;
    	else return 255-2*(255-x)*(255-y)/255;
    }
    
    private int[] getBlendData(int tr1, int tg1, int tb1, int[] input, int row, int col){
    	int[] topXY=new int[4];
    	int[] bottomXY=new int[4];
    	int[][] returnV=new int[2][4];
    	returnV=getStarEnd();
    	for(int i=0;i<4;i++)
    	{
    		topXY[i]=returnV[0][i];
    		bottomXY[i]=returnV[1][i];
    	}
    	int height=topXY[3]-topXY[1];
    	int width=topXY[2]-topXY[0];
    	
    	if (col>=width||row>=height){
    		return new int[]{tr1, tg1, tb1};
    	}
    	int index=row*width+col;
    	int tr=(input[index]>>16)&0xff;
    	int tg=(input[index]>>8)&0xff;
    	int tb=input[index]&0xff;
    	int[] rgb=new int[3];
    	if(mode==LINEAR_BLEND)
    	{
    		rgb[0]=linearBlendMode(tr1, tr, opacity);
    		rgb[1]=linearBlendMode(tg1, tg, opacity);
    		rgb[2]=linearBlendMode(tb1, tb, opacity);
    	}
    	/*else if(mode==DISSOLVE_BLEND)
    	{
    		
    		rgb[0]=dissolveBlendMode(tr1, tr);
    		rgb[1]=dissolveBlendMode(tg1, tg);
    		rgb[2]=dissolveBlendMode(tb1, tb);
    	}*/
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
    public int getMinInt(int x, int y)
    {
    	if (x<=y) return x;
    	else return y;
    }
    
    public int[][] getStarEnd()
    {
    	//index 0,1: startX, startY
    	//index 2,3: endX, endY
    	//scanwidth= index 2- index 0
    	int[] top=new int[4];
    	int[] bottom=new int[4];
    	
    	if(aligned==LEFT_TOP)
    	{
    		top[0]=bottom[0]=0;
			top[1]=bottom[1]=0;
    		top[2]=bottom[2]=getMinInt(topImage.getWidth(),bottomImage.getWidth());
    		top[3]=bottom[3]=getMinInt(topImage.getHeight(),bottomImage.getHeight());
    		//System.out.println(Arrays.toString(top));
    	}
    	else if (aligned==LEFT_BOTTOM)
    	{
    		top[0]=bottom[0]=0;
    		top[2]=bottom[2]=getMinInt(topImage.getWidth(),bottomImage.getWidth());
    		top[3]=topImage.getHeight();
    		bottom[3]=bottomImage.getHeight();
    		if(topImage.getHeight()<=bottomImage.getHeight())
    		{	
    			top[1]=0;
    			bottom[1]=bottomImage.getHeight()-topImage.getHeight();
    		}
    		else
    		{
    			top[1]=topImage.getHeight()-bottomImage.getHeight();
    			bottom[1]=0;   			
    		}
    	}
    	else if(aligned==RIGHT_TOP)
    	{
    		top[1]=bottom[1]=0;
    		top[3]=bottom[3]=getMinInt(topImage.getHeight(),bottomImage.getHeight());
    		top[2]=topImage.getWidth();
    		bottom[2]=bottomImage.getWidth();
    		if(topImage.getWidth()<=bottomImage.getWidth())
    		{
    			top[0]=0;
    			bottom[0]=bottomImage.getWidth()-topImage.getWidth();
    		}
    		else
    		{
    			top[0]=topImage.getWidth()-bottomImage.getWidth();
    			bottom[0]=0;
    		}
    	}
    	else if (aligned==RIGHT_BOTTOM)
    	{
    		top[2]=topImage.getWidth();
    		top[3]=topImage.getHeight();
    		bottom[2]=bottomImage.getWidth();
    		bottom[3]=bottomImage.getHeight();
    		if(topImage.getWidth()<=bottomImage.getWidth())
    		{
    			top[0]=0;
    			bottom[0]=bottomImage.getWidth()-topImage.getWidth();
    		}
    		else
    		{
    			top[0]=topImage.getWidth()-bottomImage.getWidth();
    			bottom[0]=0;
    		}
    		if(topImage.getHeight()<=bottomImage.getHeight())
    		{
    			top[1]=0;
    			bottom[1]=bottomImage.getHeight()-bottomImage.getHeight();
    		}
    		else
    		{
    			top[1]=topImage.getHeight()-bottomImage.getHeight();
    			bottom[1]=0;
    		}	
    	}
    	else if (aligned==CENTER)
    	{
    		if(topImage.getWidth()<=bottomImage.getWidth())
    		{
    			top[0]=0;
    			bottom[0]=(bottomImage.getWidth()-topImage.getWidth())/2;
    			top[2]=topImage.getWidth();
    			bottom[2]=bottomImage.getWidth()-(bottomImage.getWidth()-topImage.getWidth())/2;
    		}
    		else
    		{
    			bottom[0]=0;
    			top[0]=(topImage.getWidth()-bottomImage.getWidth())/2;
    			bottom[2]=bottomImage.getWidth();
    			top[2]=topImage.getWidth()-(topImage.getWidth()-bottomImage.getWidth())/2;
    		}
    		
    		if(topImage.getHeight()<=bottomImage.getHeight())
    		{
    			top[1]=0;
    			bottom[1]=(bottomImage.getHeight()-topImage.getHeight())/2;
    			top[3]=topImage.getHeight();
    			bottom[3]=bottomImage.getHeight()-(bottomImage.getHeight()-topImage.getHeight())/2;
    		}
    		else
    		{
    			bottom[1]=0;
    			top[1]=(topImage.getHeight()-bottomImage.getHeight())/2;
    			bottom[3]=bottomImage.getHeight();
    			top[3]=topImage.getHeight()-(topImage.getHeight()-bottomImage.getHeight())/2;
    		}
    		
    	}
    	int[][] returnV=new int[2][4];
    	//for(int i=0;i<2;i++)
    	for(int j=0;j<4;j++)
    	{
    		returnV[0][j]=top[j];
    		returnV[1][j]=bottom[j];
    	}
    	
    	return returnV;
    	
    }
    
    public BufferedImage blend(){
        // TODO implement here
    	
    	int[] topXY=new int[4];
    	int[] bottomXY=new int[4];
    	int[][] returnV=new int[2][4];
    	returnV=getStarEnd();
    	for(int i=0;i<4;i++)
    	{
    		topXY[i]=returnV[0][i];
    		bottomXY[i]=returnV[1][i];
    	}
    	int height=topXY[3]-topXY[1];
    	int width=topXY[2]-topXY[0];
    	
    	//test-start
    	//System.out.println(Arrays.toString(topXY));
    	//System.out.println(width);
    	//System.out.println(height);
    	//test-end
    	
    	BufferedImage dest=new BufferedImage(topImage.getWidth(), topImage.getHeight(), topImage.getType());
    	//dest=topImage;
    	
    	//BufferedImage dest=createCompatibleDestImage(topImage,null);
    	
    	//if(dest==null)     	
    	//dest=createCompatibleDestImage(topImage, null);  	
    	 int[] topPixels=new int[width*height];
         int[] bottomPixels=new int[width*height];
         int[] outPixels=new int[width*height];
         
         topImage.getRGB(topXY[0],topXY[1],width,height,topPixels,0,width);
         bottomImage.getRGB(bottomXY[0],bottomXY[1],width,height,bottomPixels,0,width);
         int index = 0;  
         int ta1 = 0, tr1 = 0, tg1 = 0, tb1 = 0;  
         for(int row=0; row<height; row++) {  
             for(int col=0; col<width; col++) {  
                 index = row * width + col;  
                 ta1 = (topPixels[index] >> 24) & 0xff;  
                 tr1 = (topPixels[index] >> 16) & 0xff;  
                 tg1 = (topPixels[index] >> 8) & 0xff;  
                 tb1 = topPixels[index] & 0xff;  
                 int[] rgb = getBlendData(tr1, tg1, tb1, bottomPixels, row, col);  
                 outPixels[index] = (ta1 << 24) | (rgb[0] << 16) | (rgb[1] << 8) | rgb[2];                     
             }  
         }
         dest.setRGB(topXY[0], topXY[1], width,height, outPixels, 0, width);
        // System.out.println(this.mode);
        // System.out.println(this.opacity);
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