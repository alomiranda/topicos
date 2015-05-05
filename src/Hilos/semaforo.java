package Hilos;

import java.util.Random;

public class semaforo implements Runnable{

	boolean color;
	public Thread animatorThread;
	Random r;
	public semaforo(boolean color) {
		this.color = color;
		animatorThread = new Thread(this);
		r = new Random();
	}

	@Override
	public void run() {
		while(true){
			try {
				if(r.nextInt(5) > 3){
				   color = false;
				}
				else
					color = true;
				
				Thread.sleep ((r.nextInt(5)+1) * 100);
				
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

}
