
package videojuego;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author KARINA GALLEGO, DANIELA MERA, JUAN JOSE BRICEÑO
 */

//se crea la clase personaje
public class Personaje {

    //se definen y se crean las variables. se crea una lista de imagenes, el iterator.
    protected LinkedList<BufferedImage> imagenes;
    protected BufferedImage imagenActiva;
    protected Iterator<BufferedImage> it;
    protected Point2D.Double loc;
    protected boolean active;
    protected int ancho, alto;
    
    public Personaje() {
    }
   
    //constructor de personaje. que tiene como parametro la lista de imagenes y point2D
    public Personaje(LinkedList<BufferedImage> imagenes, Point2D.Double loc) {
        this.imagenes = imagenes;
        this.loc = loc;
        //dimensiones de los personajes.
        ancho= imagenes.get(0).getWidth();
        alto= imagenes.get(0).getHeight();
        it = imagenes.iterator();
        imagenActiva = it.next();
        active=true;//esto inicializa el active para todos los objetos de esta clase
    }

    //metodos get y set de imagenes
    public LinkedList<BufferedImage> getImagenes() {
        return imagenes;
    }

    public void setImagenes(LinkedList<BufferedImage> imagenes) {
        this.imagenes = imagenes;
    }

    
    //metodos get y set de loc. 
    public Double getLoc() {
        return loc;
    }

    public void setLoc(Double loc) {
        this.loc = loc;
    }

    //metodo get de imagen activa.
    public BufferedImage getImagenActiva() {
        return imagenActiva;
    }

    //constructor obtener largo
    public double obtenerLargo() {
        return imagenActiva.getHeight();
    }

    public double obtenerAncho() {
        return imagenActiva.getWidth();
    }

    //coge la siguiente imagen.
    public void next() {
        imagenActiva = it.next();
        if (!it.hasNext()) {
            it = imagenes.iterator();
        }
    }

   

    public void dibujar(Graphics2D g) {
        if (active==true) {
            if (imagenActiva != null) {    
                g.drawImage(imagenActiva, (int) loc.x, (int) loc.y, null);// dibuja la imagen en la respectiva locacion asignada
            }
        }

    }
    
    public void RotarPersonaje(Graphics2D g,double angulo){
       AffineTransform rotacion = new AffineTransform();
       rotacion.rotate(Math.PI/4);
       g.drawImage(imagenActiva, rotacion, null);
    }
}
