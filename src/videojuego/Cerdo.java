
package videojuego;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class Cerdo extends Personaje {
    
    double Vel;
    
     public Cerdo(LinkedList<BufferedImage> imagenes, Point2D.Double loc){
        super(imagenes,loc);
        Vel=Math.random()*6;
    }
     
     public void Movimiento(){
     Point2D.Double loc = getLoc();
      loc.y += Vel;
      Vel +=0.2;
     }
     
     
     
}

