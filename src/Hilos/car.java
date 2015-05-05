package Hilos;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class car implements Runnable{
public float x1;
public Thread animatorThread;
int color;
boolean duerme;
int velocidad;


	public car(int color, int velocidad) {
		x1 = 0.0f;
		this.color = color;
		this.velocidad = velocidad;
		animatorThread = new Thread(this);
		duerme = false;
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep (100 - velocidad);
				if(x1 > 600)
					x1 = 0.0f;

				if(!duerme)
					x1 = x1 + 1.0f;
				else
					animatorThread.yield();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	public void startAnimation() {
        animatorThread = new Thread(this);
       animatorThread.start();

	}
	
	public void duerme()
	{
		  duerme = true;
	}
	public void noDuerme()
	{
		  duerme = false;
	}

}
