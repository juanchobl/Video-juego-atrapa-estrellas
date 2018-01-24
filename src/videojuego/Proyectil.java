

package videojuego;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class Proyectil extends Personaje{
        double vel;
        public Proyectil(LinkedList<BufferedImage> imagenes, Point2D.Double loc){
        super(imagenes,loc);
        vel=Math.random()*0.5;
    }
    //retorna un punto con el movimiento    
        public void MovimientoProyectil(double aceleracion){
           Point2D.Double loc= getLoc();
           loc.x +=15;
           loc.y +=vel; 
           vel=vel+aceleracion;
        }   
    }
    

