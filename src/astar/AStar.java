package astar;

import java.awt.*;

public class AStar {
	
	static Node selectedNode;
	static Node startNode;
	static Node destination;
	
	static boolean searchAble = false;
	static boolean finish = false;
	
	public static void update() {
		if(searchAble) {
			setAroundNode(selectedNode);
			selectMinCostNode();
		}
		else {
			trackParent();
		}
	}
	
	public static void selectMinCostNode() {
		if(NodeManager.openedNodeList.size() == 0) {
			finish = true;
			return;
		}
			
		
		int min = NodeManager.openedNodeList.get(0).f;
		Node nextNode = NodeManager.openedNodeList.get(0);
		
		for(int i = 1; i < NodeManager.openedNodeList.size(); i++) {
			Node tempNode = NodeManager.openedNodeList.get(i);
			if(tempNode.f < min) {
				nextNode = tempNode;
				min = tempNode.f;
			}
		}
		
		selectedNode = nextNode;
		
		if(selectedNode.equals(destination))
			searchAble = false;
	}
	
	public static void setParentAround(Node node) {
		for(int i = node.x - 1; i <= node.x + 1; i++) {
			for(int j = node.y - 1; j <= node.y + 1; j++) {
				Node tempNode = NodeManager.nodeList[i][j];
				
				if(tempNode.equals(node) || tempNode.isWall || tempNode.equals(startNode))
					return;
				
				tempNode.parentNode = node;
			}
		}
	}
	
	public static void setAroundNode(Node node) {
		for(int i = node.x - 1; i <= node.x + 1; i++) {
			for(int j = node.y - 1; j <= node.y + 1; j++) {
				Node tempNode = NodeManager.nodeList[i][j];
				
				if(tempNode.equals(node) || tempNode.isWall || tempNode.equals(startNode))
					continue;
				
				if(!tempNode.isOpenAble) {
					if(scoreG(tempNode, selectedNode) < tempNode.g && NodeManager.openedNodeList.contains(tempNode)) {
						tempNode.parentNode = selectedNode;
						
						pathScoring(tempNode);
					}
					continue;
				}
				
				putInOpenedList(tempNode);
				
				tempNode.parentNode = node;
				
				pathScoring(tempNode);
			}
		}
		
		putInClosedList(node);
	}
	
	public static int scoreG(Node node, Node compareNode) {
		int x = Math.abs(compareNode.x - node.x);
		int y = Math.abs(compareNode.y - node.y);
		
		int min = Math.min(x, y);
		int max = Math.max(x, y);
		
		return min * 14 + (max - min) * 10 + compareNode.g;
	}
	
	public static void pathScoring(Node node) {
		
		int x = Math.abs(node.parentNode.x - node.x);
		int y = Math.abs(node.parentNode.y - node.y);
		
		int min = Math.min(x, y);
		int max = Math.max(x, y);
		
		node.g = min * 14 + (max - min) * 10 + node.parentNode.g;
		
		x = Math.abs(destination.x - node.x);
		y = Math.abs(destination.y - node.y);
		
		min = Math.min(x, y);
		max = Math.max(x, y);
		
		node.h = min * 14 + (max - min) * 10;
		
		node.f = node.g + node.h;
		
		node.button.setText(Integer.toString(node.f));
	}
	
	public static void putInOpenedList(Node node) {
		NodeManager.openedNodeList.add(node);
		node.isOpenAble = false;
		if(!node.equals(destination))
			node.setColor(Color.GREEN);
	}
	
	public static void putInClosedList(Node node) {
		NodeManager.openedNodeList.remove(node);
		NodeManager.closedNodeList.add(node);
		node.setColor(Color.LIGHT_GRAY);
	}
	
	public static void trackParent() {
		selectedNode.setColor(Color.BLUE);
		
		if(selectedNode.parentNode == null) {
			finish = true;
			return;
		}
		
		selectedNode = selectedNode.parentNode;
	}
	
}
