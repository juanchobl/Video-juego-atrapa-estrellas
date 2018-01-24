package videojuego;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Anim extends JFrame implements ActionListener, KeyListener, MouseListener,MouseMotionListener {

    private Kirby kirby;
    private Estrella Estrella;
    private Cerdo cerdo;
    private Bomba bomba;
    private Proyectil proyectil;
    LinkedList<Proyectil> proyectiles;
    LinkedList<Estrella> MisEstrellas;  
    LinkedList<Cerdo> MisCerdos;
    LinkedList<Bomba> bombas;
    private Colision colision;
    private Lienzo lienzo;
    private Timer Update,bUpdate;
    private int lives, points;
    private int widthWindow, heigthWindow, mousex, mousey;
    final double gravedad=0.2;
    private boolean Paused, ayuda;
   
    
    public Anim(String title){
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        LinkedList<BufferedImage> imgKirby = new LinkedList<>();
        LinkedList<BufferedImage> estrella = new LinkedList<>();
        LinkedList<BufferedImage> proyectiles = new LinkedList<>();
        LinkedList<BufferedImage> Cerdo = new LinkedList<>();
        LinkedList<BufferedImage> boom = new LinkedList<>();
        LinkedList<BufferedImage> vidas= new LinkedList<>();
        
        MisEstrellas = new LinkedList<>();
        MisCerdos = new LinkedList<>();
        bombas = new LinkedList<>();
        this.proyectiles= new LinkedList<>();

        addMouseListener(this);
        addKeyListener(this);
        addMouseMotionListener(this);
        
        ayuda=false;
        lives = 20;
        points = 0;
        widthWindow=1080;
        heigthWindow=720;
        setSize(widthWindow, heigthWindow);
        
        for(int i=1;i<=3;i++){
        imgKirby.add(Util.leerImagen("Grandote"+i+".png"));
        }
        
        estrella.add(Util.leerImagen("MiEstrella1.png"));
        Cerdo.add(Util.leerImagen("Cerdoo.png"));
        boom.add(Util.leerImagen("Bomba.png"));
        proyectiles.add(Util.leerImagen("proyectil.png"));

        kirby = new Kirby(imgKirby, new Point2D.Double(0, 415), 10);

        for (int i = 0; i <= 1; i++) {
            bomba = new Bomba(boom, new Point2D.Double(Math.random() * 1000, 1));
            bombas.add(bomba);
        }
        for (int i = 0; i <= 10; i++) {
            Estrella = new Estrella(estrella, new Point2D.Double(Math.random() * 1000, 1));
            MisEstrellas.add(Estrella);
        }
        
        for(int i=0;i<=3;i++){
            proyectil= new Proyectil(proyectiles,new Point2D.Double(-50,Math.random()*350));
            this.proyectiles.add(proyectil);
        }
        for (int i = 0; i <= 6; i++) {
            cerdo = new Cerdo(Cerdo, new Point2D.Double(Math.random() * 1500, 1));
            MisCerdos.add(cerdo);
        }
        
        Update = new Timer(33, this);
        Update.setActionCommand("Caida");
        bUpdate = new Timer(30,this);
        bUpdate.setActionCommand("before game");
        
        lienzo = new Lienzo(MisEstrellas, MisCerdos, bombas,this.proyectiles,this.getWidth(), this.getHeight());
        lienzo.agregar(kirby);
        add(BorderLayout.CENTER,lienzo);

        colision = new Colision();
       //this.setResizable(false);
        lienzo.getInstrucciones().setVisible(false);
        lienzo.getImagenFinal().setVisible(false);
        lienzo.getImagenFinalGanar().setVisible(false);
        bUpdate.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Caida": 
                ActualizarResoluciones();
                actualizarResolucionBotones();
                if(this.Paused){
                    lienzo.getPausa().setVisible(true);
                    lienzo.repaint();
                 }
                
               if(!Paused){
                lienzo.getPausa().setVisible(false);
                lienzo.repaint();
                ActualizarMundo();
                actualizarCerdo();
                actualizarBomba();
                if(points>=450){
                MovimientoProyectil();
                   }
                  }
            break;
            case "before game":
             ActualizarResoluciones();
             actualizarResolucionBotones();
             System.out.println("x: "+lienzo.InitButton.getPosicion().x+"y: "+lienzo.InitButton.getPosicion().y);
             System.out.println("mousex: "+mousex+ "mouse y: "+mousey);
                break;
                    }
        }
 
    private void actualizarEstrella() {
        for (int i = 0; i < MisEstrellas.size(); i++) {
            Estrella E = MisEstrellas.get(i);
            E.Caida();
            MisEstrellas.get(i).vel += gravedad;
            lienzo.repaint();
            if (colision.colision(E, kirby)) {
                points += 5;
                E.active = false;
                MisEstrellas.remove(i);
                LinkedList<BufferedImage> estrella = new LinkedList<>();
                estrella.add(Util.leerImagen("MiEstrella1.png"));
                Estrella j = new Estrella(estrella, new Point2D.Double(Math.random() * getWidth(), 1));
                MisEstrellas.add(j);
                lienzo.setMostrar1("x " + points);
            }
            
            if (E.getLoc().getY() >= 450) {
                E.active = false;
                MisEstrellas.remove(i);//remueve la lista de estrellas.
                LinkedList<BufferedImage> estrella = new LinkedList<>();
                estrella.add(Util.leerImagen("MiEstrella1.png"));
                Estrella j = new Estrella(estrella, new Point2D.Double(Math.random() * getWidth(), 1));
                MisEstrellas.add(j);
            }
        }
    }

    public void MovimientoProyectil(){
        for(int i=0;i<=proyectiles.size()-1;i++){
        Proyectil pro= proyectiles.get(i);
        pro.MovimientoProyectil(gravedad);
        if(colision.colision(pro, kirby)){
            lives=lives-2;
            lienzo.setMostrar2("x "+lives);
            pro.active=false;
            proyectiles.remove(i);
            LinkedList<BufferedImage> losPros = new LinkedList<>();
                losPros.add(Util.leerImagen("proyectil.png"));
                Proyectil j = new Proyectil(losPros, new Point2D.Double(-50, Math.random()*(getHeight()/2)));
                proyectiles.add(j);
        }
        if(pro.getLoc().y>=450 || pro.getLoc().x>=1080){
            pro.active=false;
            proyectiles.remove(i);
           LinkedList<BufferedImage> losPros = new LinkedList<>();
                losPros.add(Util.leerImagen("proyectil.png"));
                Proyectil j = new Proyectil(losPros, new Point2D.Double(-50, Math.random()*(getHeight()/2)));
                proyectiles.add(j);
        } 
         if (lives <= 0) {
            kirby.active = false;
            lienzo.getImagenFinal().setVisible(true);
            lienzo.repaint();
        }   
        }
    }
    
    private void ActualizarMundo() {
        actualizarEstrella();
         
        lienzo.getImgFondo().setWidth(getWidth());
        lienzo.getImgFondo().setHeigth(getHeight());
        
        if (points >= 150 && points<300) {
                lienzo.setImgFondo(new imagen(Util.leerImagen("FondoVideoJuegoFinal2.jpg"), getWidth(), getHeight(),new Point2D.Double(0,0)));
                 lienzo.setMundo("World 2");
            }
           
            if(points>=300 && points<450){
                  lienzo.setImgFondo(new imagen(Util.leerImagen("FondoVideoJuegoFinal3.png"), getWidth(), getHeight(),new Point2D.Double(0,0)));
                  lienzo.setMundo("World 3");   
                }
              if(points>=450){  
                 lienzo.setImgFondo(new imagen(Util.leerImagen("FondoVideoJuegoFinal4.jpg"), getWidth(), getHeight(),new Point2D.Double(0,0))); 
                  lienzo.setMundo("World 4");
                        }   
        
        if (points >= 550) {
            lienzo.getImagenFinalGanar().setVisible(true);
            kirby.active = false;   
        }
    }

    private void actualizarCerdo() {
        for (int i = 0; i < MisCerdos.size(); i++) {
            Cerdo c = MisCerdos.get(i);
            c.Movimiento();
            lienzo.repaint();

            if (colision.colision(c, kirby)) {
                lives--;
                lienzo.setMostrar2("x "+lives);
                kirby.setLives(lives);
                c.active = false;
                MisCerdos.remove(i);
                LinkedList<BufferedImage> cerdos = new LinkedList<>();
                cerdos.add(Util.leerImagen("Cerdoo.png"));
                Cerdo j = new Cerdo(cerdos, new Point2D.Double(Math.random() * getWidth(), 1));
                MisCerdos.add(i, j);
            }
            
            if (c.getLoc().y >= 450) {
                c.active = false;
                MisCerdos.remove(i);
                LinkedList<BufferedImage> cerdos = new LinkedList<>();
                cerdos.add(Util.leerImagen("Cerdoo.png"));
                Cerdo j = new Cerdo(cerdos, new Point2D.Double(Math.random() * getWidth(), 1));
                MisCerdos.add(i, j);
            }
        }
        
        if (lives <= 0) {
            kirby.active = false;
            lienzo.getImagenFinal().setVisible(true);
        }
    }

    private void actualizarBomba() {
        for (int i = 0; i < bombas.size(); i++) {
            Bomba b = bombas.get(i);
            Point2D.Double loc = b.getLoc();
            loc.y +=bombas.get(i).Vel ;
            lienzo.repaint();

            if (colision.colision(b, kirby)) {
                lives = lives - 3;
                lienzo.setMostrar2("x "+lives);
                kirby.setLives(lives);
                b.active = false;
                bombas.remove(i);
                LinkedList<BufferedImage> bom = new LinkedList<>();
                bom.add(Util.leerImagen("Bomba.png"));
                Bomba g = new Bomba(bom, new Point2D.Double(Math.random() * 1080, 1));
                bombas.add(i, g);
            }
            if (b.getLoc().y >= 450) {
                b.active = false;
                bombas.remove(i);
                LinkedList<BufferedImage> bom = new LinkedList<>();
                bom.add(Util.leerImagen("Bomba.png"));
                Bomba q = new Bomba(bom, new Point2D.Double(Math.random() * 1080, 1));
                bombas.add(i, q);
            }
        }
        if(lives <= 0) {
           kirby.active = false;
           lienzo.getImagenFinal().setVisible(true);
        }
    }
    
    
    public void ActualizarResoluciones(){
        lienzo.getImagenFinalGanar().setHeigth(getHeight());
        lienzo.getImagenFinalGanar().setWidth(getWidth());
        lienzo.getImagenFinal().setHeigth(getHeight());
        lienzo.getImagenFinal().setWidth(getWidth());
        lienzo.getInstrucciones().setWidth(getWidth());
        lienzo.getInstrucciones().setHeigth(getHeight());
        lienzo.getImagenInicio().setWidth(getWidth());
        lienzo.getImagenInicio().setHeigth(getHeight());        
        lienzo.repaint();
        }
    
    public void actualizarResolucionBotones(){
        lienzo.InitButton.setWidth((int)(getWidth()*0.15));
        lienzo.InitButton.setHeight((int)(getHeight()*0.15));
        lienzo.botonAyuda.setWidth((int)(getWidth()*0.15));
        lienzo.botonAyuda.setHeight((int)(getHeight()*0.15));
        lienzo.exitButton.setWidth((int)(getWidth()*0.15));
        lienzo.exitButton.setHeight((int)(getHeight()*0.15));
        lienzo.InitButton.setPosicion(new Point2D.Double((getWidth()/2)*1.4,((getHeight())*0.5)-(lienzo.InitButton.getHeight()/2)));
        lienzo.exitButton.setPosicion(new Point2D.Double((getWidth()/2)*0.25,((getHeight())*0.5)-(lienzo.botonAyuda.getHeight()/2)));
        lienzo.botonAyuda.setPosicion(new Point2D.Double((getWidth()/2)*0.85,((getHeight())*0.5)-(lienzo.exitButton.getHeight()/2)));
    
        if(ayuda==true){
         lienzo.InitButton.setWidth((int)(getWidth()*0.15));
         lienzo.InitButton.setHeight((int)(getHeight()*0.15));
         lienzo.exitButton.setWidth((int)(getWidth()*0.15));
         lienzo.exitButton.setHeight((int)(getHeight()*0.15));
         lienzo.InitButton.setPosicion(new Point2D.Double(lienzo.InitButton.getPosicion().x,getHeight()*0.75));
         lienzo.exitButton.setPosicion(new Point2D.Double(lienzo.exitButton.getPosicion().x,getHeight()*0.75));
         lienzo.repaint();
        }
    }
        public boolean isIsPaused() {
        return Paused;
    }

    public void setIsPaused(boolean isPaused) {
        this.Paused = isPaused;
    }
    
    public void Pausar(){
        if(Paused){
        lienzo.getPausa().setVisible(true);
        }
        if(!Paused){
        lienzo.getPausa().setVisible(true);
        }
    }
    
     public void IniciarJuego(){
         Update.start();
         bUpdate.stop();
    }
    
     
    public static void main(String[] args) {
        Anim app = new Anim("Animacion con imagenes");
        app.setVisible(true);  
    }

    @Override
    public void keyTyped(KeyEvent e){ 
    }

    
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 39 && kirby.getLoc().x <= getWidth()-kirby.imagenActiva.getWidth()) {
            if(this.Paused==false){
           Point2D.Double loc = kirby.getLoc();
           LinkedList<BufferedImage> imgKirby = new LinkedList<>();
        for(int i=1;i<=3;i++){
        imgKirby.add(Util.leerImagen("Grandote"+i+".png"));
        }
            kirby.setImagenes(imgKirby);
            loc.x = kirby.getLoc().getX() + kirby.getVel();
            kirby.next();
            lienzo.repaint();
            }
        } else {
            if(this.Paused==false){
            if (e.getKeyCode() == 37 && kirby.getLoc().x >= 0) {
                Point2D.Double loc = kirby.getLoc();
           LinkedList<BufferedImage> imgKirby = new LinkedList<>();
           for(int i=1;i<=3;i++){
        imgKirby.add(Util.leerImagen("GrandoteI"+i+".png"));
        }
            kirby.setImagenes(imgKirby);
                loc.x = kirby.getLoc().getX() - kirby.getVel();
                kirby.next();
                lienzo.repaint();
            }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
         if(e.getKeyCode()==10){
        this.setIsPaused(!this.Paused);
       }
        
    }

    @Override
    public void mouseClicked(MouseEvent e){
      if(!Update.isRunning()){
     if(e.getX()>= lienzo.InitButton.getPosicion().x && e.getX()<=lienzo.InitButton.getPosicion().x+lienzo.InitButton.getWidth()){
      if(e.getY()>= lienzo.InitButton.getPosicion().y && e.getY()<=lienzo.InitButton.getPosicion().y+lienzo.InitButton.getHeight()){
         lienzo.InitButton.setVisible(false);
         lienzo.exitButton.setVisible(false);
         lienzo.botonAyuda.setVisible(false);
         lienzo.getImagenInicio().setVisible(false);
         lienzo.getInstrucciones().setVisible(false); 
         IniciarJuego();
         lienzo.repaint();
    }
     }
     
     if(e.getX()>= lienzo.botonAyuda.getPosicion().x && e.getX()<=lienzo.botonAyuda.getPosicion().x+lienzo.botonAyuda.getWidth()){
        if(e.getY() >= lienzo.botonAyuda.getPosicion().y+(lienzo.botonAyuda.getHeight()*0.2) && e.getY()<=(lienzo.botonAyuda.getPosicion().y + lienzo.botonAyuda.getHeight())+(lienzo.botonAyuda.getHeight()*0.2)){
         ayuda=true;
         lienzo.botonAyuda.setVisible(false);
         lienzo.getInstrucciones().setVisible(true);
         lienzo.repaint();
     }
     }
     
       if(e.getX()>= lienzo.exitButton.getPosicion().x && e.getX()<= lienzo.exitButton.getPosicion().x+lienzo.exitButton.getWidth()){
      if(e.getY()>= lienzo.exitButton.getPosicion().y+(lienzo.exitButton.getHeight()*0.26) && e.getY()<=lienzo.exitButton.getPosicion().y+lienzo.exitButton.getHeight()+(lienzo.exitButton.getHeight()*0.15)){
         System.exit(0);
    }
       }
        }
    }
    
    
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
       mousex= e.getX();
       mousey= e.getY();
    }
}