package astar;

import java.io.IOException;

public class MyThread extends Thread {

	static MyFrame m;
	static MyThread thread; 
	
	public void run() {
		try {
			while(true) {
				if(AStar.finish) {
					break;
				}
				
				AStar.update();
				sleep(100);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		thread = new MyThread();
		
		m = new MyFrame();

	}

}
