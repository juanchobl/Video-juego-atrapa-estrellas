
package videojuego;

/**
 *
 * @author KARINA GALLEGO, DANIELA MERA, JUAN JOSE BRICEÑO
 */

//se crea la clase colision
public class Colision {
    
    //se crean dos personajes. (variables)
    

    //se crea el constructor de la clase colision.
    public Colision() {
      
    }
    
    //Metodo colision que recibe como parametro los dos personajes entre los que se va a realizar la colision.
    public boolean colision(Personaje uno, Personaje dos) {
        boolean colision = false;
        
        //condicion que permite que la colision no se haga si las dimensiones de uno de los personajes es mayor 
        //que las dimensiones del otro personaje.
        if((dos.getLoc().getY())>(uno.getLoc().getY()+(uno.alto/2))){
                     colision= false;
    }else{
            //se realiza la colision si las dimensiones del primer personaje mayor o igual a las dimens del otro personaje.
        if (((uno.getLoc().getY())>=(dos.getLoc().getY()))){
            if((uno.getLoc().x+uno.ancho>=dos.getLoc().x) && (uno.getLoc().x+uno.ancho<=dos.getLoc().x+dos.ancho)){
                colision = true;
            }
        }
        }
        return colision;
    }
}