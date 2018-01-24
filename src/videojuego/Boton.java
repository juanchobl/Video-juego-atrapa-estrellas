
package videojuego;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;

public class Boton {
    
    private Point2D.Double posicion;
    private BufferedImage boton;
    private boolean visible;
    private int width, height;

    public Boton(Double posicion, BufferedImage boton, Boolean visible, int width, int height) {
        this.posicion = posicion;
        this.boton = boton;
        this.visible = visible;
        this.width=width;
        this.height=height;
    }

    public Double getPosicion() {
        return posicion;
    }

    public void setPosicion(Double posicion) {
        this.posicion = posicion;
    }

    public BufferedImage getBoton() {
        return boton;
    }

    public void setBoton(BufferedImage boton) {
        this.boton = boton;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean Visible) {
        this.visible = Visible;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    
    public void Dibujar(Graphics2D gd, Lienzo lienzo){
        if(visible==true){
        gd.drawImage(boton,(int) posicion.x,(int) posicion.y, width,height ,lienzo);
    }  
   }
}