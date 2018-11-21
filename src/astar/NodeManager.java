package astar;

import java.util.Vector;

public class NodeManager {
	
	static int width, height;
	
	static Node[][] nodeList;
	static Vector<Node> openedNodeList = new Vector<Node>();
	static Vector<Node> closedNodeList = new Vector<Node>();
	static int clickCount = 0;
	
	public static void nodeDisable() {
		if(clickCount != 2)
			return;
		
		for(int i = 0; i < width; i++)
			for(int j = 0; j < height; j++)
				nodeList[i][j].button.setEnabled(false);
	}
}
