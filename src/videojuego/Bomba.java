
package videojuego;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class Bomba extends Personaje {
   
    
    double Vel;
    
    public Bomba(LinkedList<BufferedImage> imagenes, Point2D.Double loc){
        super(imagenes,loc); 
        Vel=(7*Math.random())+0.1;
    }

    public double getVel() {
        return Vel;
    }

    public void setVel(double Vel) {
        this.Vel = Vel;
    }
}
