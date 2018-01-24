
package videojuego;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Estrella extends Personaje{
    
    protected double vel;
 
    
    public Estrella(LinkedList<BufferedImage> imagenes, Point2D.Double loc){
        super(imagenes,loc);
        vel = 8*Math.random();    
    }
    
    public void Caida(){
        Point2D.Double loc = getLoc();
        loc.y +=vel;
        vel+=0.2;
    }


    
    
}
