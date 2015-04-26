package ips.high5.cmpt594;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * Sharpens an image by enhancing contrast of edges within the image.
 * @author Cuicui Ruan- Team High 5
 */
public class  IpsSharpener {

    public IpsSharpener() {
    }

    /**
     * Sharpens an image by enhancing contrast of edges within the image.
     * @param src
     * @return Sharpened BufferedImage
     */
    public BufferedImage sharpen(BufferedImage src) {
        BufferedImage dest=new BufferedImage(src.getWidth(),src.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
        float[] data={-1.0f, -1.0f, -1.0f, -1.0f, 10.0f, -1.0f, -1.0f, -1.0f, -1.0f };
        Kernel kernel=new Kernel(3,3,data);
        ConvolveOp co=new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP,null);
        co.filter(src, dest);
        return dest;
    }

}