package videojuego;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class imagen {
    
    private boolean visible;
    private BufferedImage portada;
    private int width, heigth;
    private Point2D.Double posicion;

    public imagen(BufferedImage portada, int width, int heigth, Point2D.Double posicion){
        this.visible = true;
        this.portada = portada;
        this.width = width;
        this.heigth = heigth;
        this.posicion=posicion;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public BufferedImage getPortada() {
        return portada;
    }

    public void setPortada(BufferedImage portada) {
        this.portada = portada;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public Point2D.Double getPosicion() {
        return posicion;
    }

    public void setPosicion(Point2D.Double posicion) {
        this.posicion = posicion;
    }
    
    
    
    public void Dibujar(Graphics2D lapiz,Lienzo lienzo){
        if(visible==true){
        lapiz.drawImage(portada, (int) posicion.x, (int) posicion.y,width, heigth,lienzo);
    }
    }
    
}
