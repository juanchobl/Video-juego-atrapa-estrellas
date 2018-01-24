/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author KARINA GALLEGO, DANIELA MERA, JUAN JOSE BRICEÑO
 */

//SE crea clase util
public class Util 
{
    
    //metodo que permite  leer las imagenes
    public static BufferedImage leerImagen ( String nomArch)
    {
        BufferedImage im = null;
        try {
            im = ImageIO.read(new File(nomArch));
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return im;
    }
}
