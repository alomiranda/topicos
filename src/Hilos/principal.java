package Hilos;

import java.awt.AlphaComposite;
import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath; 
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame; 

/** 
* 
* @author alonso 
*/ 
public class principal extends JFrame implements Runnable{ 
//establecer la barra de titulo 

car car1;
car car2;

Ellipse2D.Double sem1;
Ellipse2D.Double sem2;

semaforo semaforo1;
semaforo semaforo2;

JButton boton1;
JButton boton2;

public Thread animatorThread;

public principal() { 
super("Dibujo en 2D");
boton1=new JButton("Carro 1");
boton1.setBounds(100,120,100,30);

boton2=new JButton("Carro 2");
boton2.setBounds(400,120,10,10);


car1 = new car(1,85);
car1.startAnimation();
car2 = new car(2,99);
car2.startAnimation();
semaforo1 = new semaforo(true);
semaforo2 = new semaforo(false);
semaforo1.startAnimation();
semaforo2.startAnimation();

getContentPane().setBackground(Color.WHITE);
setSize(600,200); 
setVisible(true); 
animatorThread = new Thread(this);


} 
//dubujar rutas generales 
public void paint (Graphics g) {
	 Graphics2D g2 = (Graphics2D)g;
	 AlphaComposite ac =
				AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
	 g2.setColor(Color.BLUE);
	 Rectangle2D r1 = new Rectangle2D.Float(car1.x1,160.0f,50.0f,20.0f);
	 g2.fill(r1);
	 
	 g2.setColor(Color.BLACK);
	 g2.setComposite(ac);
	 Rectangle2D r2 = new Rectangle2D.Float(car2.x1,160.0f,50.0f,20.0f);
	 g2.fill(r2);
	 
	 sem1 = new Ellipse2D.Double(100, 100, 50, 50);
	 
	 if(semaforo1.color)
		 g2.setColor(Color.RED);
	 else
		 g2.setColor(Color.GREEN);
	  g2.fill(sem1);
	   
	  sem2 = new Ellipse2D.Double(400, 100, 50, 50);
	  if(semaforo2.color)
		 g2.setColor(Color.RED);
	  else
		 g2.setColor(Color.GREEN);
	  g2.fill(sem2);
	  
	 
	 
	}


//ejecutar aplciacion 
public static void main(String[] args) { 
principal aplicacion = new principal(); 
aplicacion.startAnimation();


}
@Override
public void run() {
	 while(true){
		 try {
			 animatorThread.sleep (30);
				
				if(semaforo1.color){
					if(car1.x1 > 95 && car1.x1 < 120){
						System.out.println("Stop semaforo 111 carro 1");
						car1.duerme();
					}
					
					if(car2.x1 > 95 && car2.x1 < 120){
						System.out.println("Stop semaforo 111 carro 2");
						car2.duerme();
					}
				}
				else
				{
					car1.noDuerme();
					car2.noDuerme();
				}
				
				if(semaforo2.color){
					if(car1.x1 > 380 && car1.x1 < 410){
						System.out.println("Stop semaforo 222  carro 1");
						car1.duerme();
					}
					
					if(car2.x1 > 380 && car2.x1 < 410){
						System.out.println("Stop semaforo 222 carro 2");
						car2.duerme();
					}
				}
				else
				{
					car1.noDuerme();
					car2.noDuerme();
				}
				setSize(600,201); 
				repaint();
				setSize(600,200); 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				repaint();
			}
 }


	
}
public void startAnimation() {
    animatorThread = new Thread(this);
   animatorThread.start();

}



}