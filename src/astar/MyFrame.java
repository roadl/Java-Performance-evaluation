package astar;

import java.awt.*;
import javax.swing.*;

import java.io.*;
import java.util.*;

public class MyFrame extends JFrame {

	Vector<String> map = new Vector<String>();
	
	int mapX, mapY;
	
	MyFrame() throws IOException {
		
		readMap();
		
		Container c = getContentPane();
		c.setLayout(new GridLayout(mapY, mapX));
		
		boolean isWall;
		
		NodeManager.nodeList = new Node[mapX][mapY];
		
		for(int i = 0; i < mapY; i++) {
			for(int j = 0; j < mapX; j++) {
				isWall = getIsWall(map.get(i).charAt(j));
				Node n = new Node(j, i, isWall);
				NodeManager.nodeList[j][i] = n;
				c.add(n.button);
			}
		}

		setTitle("AStar Example");
		setSize(mapX * 60, mapY * 65);
		setVisible(true);
		
	}
	
	boolean getIsWall(char c) {
		
		switch(c) {
		case '0': return false;
		case '1': return true;
		}
		
		return true;
	}
	
	void readMap() {
		
		try {
			
			BufferedReader r = new BufferedReader(new FileReader("./map.txt"));
		
			String tempStr;
			
			tempStr = "1" + r.readLine() + "1";
			
			String wall = new String(new char[tempStr.length()]).replace("\0", "1");
			
			map.add(wall);
			map.add(tempStr);
		
			while((tempStr = r.readLine()) != null) {
				map.add("1" + tempStr + "1");
				System.out.println(tempStr);
			}
			
			map.add(wall);
		
			mapX = map.get(0).length();
			mapY = map.size();
			
			NodeManager.width = mapX;
			NodeManager.height = mapY;
			
			r.close();
			
		} catch (IOException e) {
			e.getMessage();
		}
	}
}
