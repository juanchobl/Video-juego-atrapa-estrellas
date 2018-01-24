package videojuego;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author KARINA GALLEGO, DANIELA MERA, JUAN JOSE BRICEÑO
 */

public class Kirby extends Personaje{
    
    private int lives, vel;
    
    public Kirby(LinkedList<BufferedImage> imagenes, Point2D.Double loc,int lives){
        super(imagenes, loc);
        this.lives=lives;
        vel=15;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
 
     public void dibujar(Graphics2D g) {
        if (lives>=0 && active==true) {
            if (imagenActiva != null) {     
                g.drawImage(imagenActiva, (int) loc.x, (int) loc.y, null);
            }
        }
    }
         public int getVel() {
        return vel;
    }

    public void setVel(int vel) {
        this.vel = vel;
    } 
     
}
   
