
package videojuego;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class Vida extends Personaje{
    
     double Vel;
     public Vida(LinkedList<BufferedImage> imagenes, Point2D.Double loc){
        super(imagenes,loc); 
        Vel=4*Math.random();
    }
     
     public void Caida(double a){
     Point2D.Double loc= getLoc();
     loc.y += Vel;
     Vel +=a;
     }
     
     public void Colision(Kirby k){
         
     }
     
    
}
