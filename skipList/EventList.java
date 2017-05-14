package skipList;

//
// EVENTLIST.JAVA
// Skeleton code for your EventList collection type.
//
import java.util.*;
class EventList {
	
	Random randseq;
	Node head;
	Node tail;
	int maxHeight;
	//int Height;
	
    ////////////////////////////////////////////////////////////////////
    // Here's a suitable geometric random number generator for choosing
    // pillar heights.  We use Java's ability to generate random booleans
    // to simulate coin flips.
    ///////////////////////////////////////////////////////////////////

	int randomHeight()
	{
		int v = 1;
		while (randseq.nextBoolean()) { v++; }
		return v;
	}


	//
	// Constructor
	//
	public EventList()
	{
		randseq = new Random(58243);
		Event H = new Event(Integer.MIN_VALUE, "head");
		Event T = new Event(Integer.MAX_VALUE, "tail");
		tail = new Node(T, null, null);
		head = new Node(H, tail, null);
		maxHeight = 1;
		//Height = 1;
	}

	
	//Searching for a key
	public Node Find(int iyear)
	{
		int l = maxHeight - 1;
		Node x = head;
		while (l >= 0)
		{
			Node y = x.right;
			if (y.values.get(0).year == iyear)
			{
				return y;
			}
			else if (y.values.get(0).year < iyear)
			{
				x = y;
			}
			else
			{
				l--;
				x = x.down;
			}
		}
		return null;
	}

	//
	// Add an Event to the list.
	//
	public void insert(Event e)
	{
		int t = randomHeight();
		if (t > maxHeight)
		{
			//Height = t;
			while (maxHeight < t)
			{
				tail = tail.increase(null);
				head = head.increase(tail);
				maxHeight++;// = 2 * maxHeight;
			}

		}
        //allocate a pillar of height t for e.
		int l = maxHeight - 1;
		//if the year has existed, add the new description to the old one.
		if (Find (e.year) != null) 
		{
			Node exist = Find(e.year);
			//exist.values.get(0).description = exist.values.get(0).description + "\r\n" + e.year + " "+ e.description;
			exist.values.add(e);
			return;
		}

		//if not, creating the new node with new year and description.
		Node z = new Node(e, null, null);
		Node x = head;
		while (x.down != null)
		{
			Node y = x.right;
			if (y.values.get(0).year > e.year)
			{
				if (l < t)
				{
					y = z;
					z = x.right;
					z = z.down;
				}
				l--;
				x = x.down;
			}
			else
			{
				x = y;
			}
		}
		while (x.right.values.get(0).year < e.year)
		{
			x = x.right;
		}
		x.right = new Node(e, x.right, null);
	}
	
	
	

	//
	// Remove all Events in the list with the specified year.
	//
	public void remove(int year)
	{
		int l = maxHeight - 1;
		Node x = head;
		while (l >= 0) 
		{
			Node y = x.right;
			if (y.values.get(0).year == year)
			{
				x.right = y.right;
				l--;
				x = x.down;
			}
			else
			{ 
				if (y.values.get(0).year < year)
				{
					x = y;
				}
				else
				{
					l--;
					x = x.down;
				}
			}
		}
	}

	public Node findpred(int iyear)
	{
		int l = maxHeight - 1;
		Node x = head;
		while (l >= 0)
		{
			Node y = x.right;
			if (y.values.get(0).year == iyear)
			{
				return y;
			}
			else if (y.values.get(0).year < iyear)
			{
				x = y;
			}
			//if we can not find the year we want, then find the predecessor of such year.
			else if (x.down == null) 
			{
				if (x.values.get(0).year == head.values.get(0).year)
				{
					return null;
				}
				return x;
			}
			else
			{
				l--;
				x = x.down;
			}
		}
		return null;
	}

	
	public Node findsucc (int iyear)
	{
		int l = maxHeight - 1;
		Node x = head;
		while (l >= 0)
		{
			
			Node y = x.right;
			if (y.values.get(0).year == iyear)
			{
				return y;
			}
			else if (y.values.get(0).year < iyear)
			{
				x = y;
            }
			
			else if (x.down == null) 
			{
				//if (x.values.get(0).year == head.values.get(0).year)
				//{
					//return null;
				//}
				return y;
			}
			else
			{
				l--;
				x = x.down;
			}
		}
		return null;
	}
	
	
	//
	// Find all events with greatest year <= input year
	//
	public Event [] findMostRecent(int year)
	{
		ArrayList<Event> Recent = new ArrayList<Event>();
		if (findpred(year) != null)
		{
			Node recent = findpred(year);
			Recent.addAll(recent.values);
			int Events_num = Recent.size();
			Event Recentevents[] = new Event[Events_num];
			for (int i = 0; i < Events_num; i++)
			{
				Recentevents[i] = Recent.get(i);
			}
			return Recentevents;

		}
		return null;
	}


	//
	// Find all Events within the specific range of years (inclusive).
	//
	public Event [] findRange(int first, int last)
	{
		ArrayList<Event> Range = new ArrayList<Event>();
		if (findsucc(first) != null && findpred(last) != null)// && Find(first) != null && Find(last) != null)
		{
			Node range = findsucc(first);
			//int l = maxHeight - 1;
			while (range.down != null)
			{
				range = range.down;
			}

			while (range.values.get(0).year <= last) 
			{
				Range.addAll(range.values);
				range = range.right;
			}
			int Events_num = Range.size();
			Event Eventsrange[] = new Event[Events_num];
			for (int j = 0; j < Events_num; j++)
			{
				Eventsrange[j] = Range.get(j);
			}
			return Eventsrange;
		}
		return null;
	}
}