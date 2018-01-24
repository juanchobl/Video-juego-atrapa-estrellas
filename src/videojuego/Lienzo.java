package videojuego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import javax.swing.JPanel;


public class Lienzo extends JPanel{
    
    private LinkedList<Cerdo> cerdos;
    private LinkedList<Personaje> assets;
    private LinkedList<Bomba> Bombas;
    private LinkedList<Estrella> estrellas;
    private LinkedList<Proyectil> pr;
    private String Puntaje,lives,mundo;
    private BufferedImage kirbyFace,estrellaHud;
    Boton exitButton,InitButton,botonAyuda,pausa;
    private imagen imagenInicio,imagenFinalPerder,instrucciones,imagenFinalGanar,imgFondo;
    private int anchoVentana, altoVentana;
    
    public Lienzo(LinkedList<Estrella> e, LinkedList<Cerdo> c, LinkedList<Bomba> b,LinkedList<Proyectil> pr, int anchoVentana, int altoVentana){
       this.anchoVentana=anchoVentana;
       this.altoVentana=altoVentana;
       assets = new LinkedList<>();
       Bombas= new LinkedList<>();
       this.pr=pr;
       estrellas = e;
       cerdos=c;
       Bombas=b;
       Puntaje="x 0";
       lives="x 20";
       mundo="World 1";
       //imgFondo = Util.leerImagen("FondoVideoJuegoFinal1.jpg");
       kirbyFace=Util.leerImagen("Face.png");
       estrellaHud=Util.leerImagen("Estrella.png");
       botonAyuda= new Boton(new Point2D.Double((anchoVentana)*0.85,(altoVentana)*0.82), Util.leerImagen("botonAyuda.png"),true,(int)(this.anchoVentana*0.15),(int)(this.altoVentana*0.15));
       exitButton= new Boton(new Point2D.Double((anchoVentana)*0.25,(altoVentana)*0.85),Util.leerImagen("Exit.png"),true,(int)(this.anchoVentana*0.15),(int)(this.altoVentana*0.15));
       InitButton= new Boton(new Point2D.Double((anchoVentana)*1.4,(altoVentana)*0.82),Util.leerImagen("botonInicio.png"),true,(int)(this.anchoVentana*0.15),(int)(this.altoVentana*0.15));
       pausa= new Boton(new Point2D.Double(520,300),Util.leerImagen("pausa.png"),false,(int)(this.anchoVentana*0.15),(int)(this.altoVentana*0.15));
       imagenInicio= new imagen(Util.leerImagen("FondoVideoJuegoFinal1.jpg"),this.anchoVentana,this.altoVentana, new Point2D.Double(0,0));
       imagenFinalPerder= new imagen(Util.leerImagen("final.jpg"),this.anchoVentana,this.altoVentana, new Point2D.Double(0,0));
       instrucciones= new imagen(Util.leerImagen("Instrucciones.jpg"),this.anchoVentana,this.altoVentana, new Point2D.Double(0,0));
       imagenFinalGanar = new imagen(Util.leerImagen("imagen final.jpg"),this.anchoVentana,this.altoVentana, new Point2D.Double(0,0));
       imgFondo=new imagen(Util.leerImagen("FondoVideoJuegoFinal1.jpg"),this.anchoVentana,this.altoVentana, new Point2D.Double(0,0));
    }

    public imagen getImagenFinalGanar() {
        return imagenFinalGanar;
    }

    public void setImagenFinalGanar(imagen imagenFinalGanar) {
        this.imagenFinalGanar = imagenFinalGanar;
    }

    public imagen getImagenFinal() {
        return imagenFinalPerder;
    }

    public void setImagenFinal(imagen imagenFinal) {
        this.imagenFinalPerder = imagenFinal;
    }
    
    public void agregar (Personaje personaje)
    {
        assets.add(personaje);
    }

   
    public void setMostrar1(String mostrar1) {
        this.Puntaje = mostrar1;
    }
    
    public void setMostrar2(String mostrar2) {
        this.lives = mostrar2;
    }

    public void setKirbyFace(BufferedImage kirbyFace) {
        this.kirbyFace = kirbyFace;
    }

    public void setMundo(String mundo) {
        this.mundo = mundo;
    }
 
    @Override
    //se dibuja el fondo y los personajes(estrella, bomba, cerdos)
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D)g;
        imgFondo.Dibujar(gd, this);
  
        for(Proyectil p: pr){
            p.dibujar(gd);
        }
        
        for (Personaje personaje : assets) {
            personaje.dibujar(gd);
        }
        
        for (Estrella estrella : estrellas) {
            estrella.dibujar(gd);
        }
        
          for (Personaje cerdo : cerdos) {
            cerdo.dibujar(gd);
        }
          
        for(Bomba b: Bombas){
            b.dibujar(gd);
        }
        
        //mostrar el puntaje
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.BOLD,30));
        g.drawString(Puntaje, 220, 40);
        //mostrar el mundo o nivel
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.drawString(mundo, 950, 40);
        //mostrando las vidas
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.BOLD,30));
        g.drawString(lives, 70, 40);
        gd.drawImage(kirbyFace, 10, 12, this);
        gd.drawImage(estrellaHud,180,12,this);
        
        imagenInicio.Dibujar(gd, this);
        imagenFinalPerder.Dibujar(gd, this);
        instrucciones.Dibujar(gd, this);
        imagenFinalGanar.Dibujar(gd, this);
        //gd.drawImage(imagenFinalPerder.getPortada(), 0,0, 820, 480, this);
        botonAyuda.Dibujar(gd, this);
        InitButton.Dibujar(gd, this);
        exitButton.Dibujar(gd, this);
        pausa.Dibujar(gd, this);
    }

    public imagen getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(imagen instrucciones) {
        this.instrucciones = instrucciones;
    }

    public Boton getPausa() {
        return pausa;
    }

    public void setPausa(Boton pausa) {
        this.pausa = pausa;
    }

    public imagen getImgFondo() {
        return imgFondo;
    }
    
    public void setImgFondo(imagen imgFondo) {
        this.imgFondo = imgFondo;
    }

    public imagen getImagenInicio() {
        return imagenInicio;
    }

    public void setImagenInicio(imagen imagenInicio) {
        this.imagenInicio = imagenInicio;
    }
    
    public static BufferedImage resize(BufferedImage bufferedImage, int newW, int newH) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage bufim = new BufferedImage(newW, newH, bufferedImage.getType());
        Graphics2D g = bufim.createGraphics();
        g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return bufim;
    }
}