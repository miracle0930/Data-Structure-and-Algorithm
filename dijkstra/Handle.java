package dijkstra;

public class Handle<T> {
	public int key;
	public boolean exist;
	public int index;
	
	public Handle(int k,boolean tf,int i){
		key=k;
		exist=tf;
		index=i;		
	}
}