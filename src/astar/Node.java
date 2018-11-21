package astar;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.*;

public class Node implements ActionListener{
	
	Node parentNode;
	
	boolean isDestination = false;
	boolean isWall;
	boolean isOpenAble;
	
	int f = 0, g = 0, h = 0;
	int x, y;
	
	public JButton button = new JButton();
	
	Node(int x, int y, boolean isWall){
		this.x = x;
		this.y = y;
		this.isWall = isWall;
		this.isOpenAble = !isWall;
		
		if(isWall)
			button.setBackground(Color.BLACK);
		else {
			button.addActionListener(this);
			button.setBackground(Color.WHITE);
		}

		button.setSize(20, 20);
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println(x + " " + y);
		NodeManager.clickCount++;
		if(NodeManager.clickCount == 1) {
			AStar.putInOpenedList(this);
			AStar.selectedNode = this;
			AStar.startNode = this;
			isOpenAble = false;
			System.out.println("F:" + this.f
					+ " G:" + this.g + " H:" + this.h);
			System.out.println("X:" + this.x + " Y:" + this.y);
			button.setText("S");
		}
		else {
			button.setBackground(Color.YELLOW);
			isDestination = true;
			AStar.searchAble = true;
			AStar.destination = this;

			MyThread.thread.start();
			button.setText("F");
		}
		
		NodeManager.nodeDisable();
	}
	
	public void setColor(Color color) {
		button.setBackground(color);
	}
	
}
