package skipList;

import java.util.ArrayList;

public class Node 
{
	public ArrayList<Event> values;
	public Node right;
	public Node down;
	//public Node left;

	public Node(Event description, Node iright, Node idown)
	{
		values = new ArrayList<Event>();
		this.values.add(description);
		right = iright;
		down = idown;
		//left = ileft;
	}
	public Node increase(Node idown)
	{
		return new Node(values.get(0), idown, this);
	}
}