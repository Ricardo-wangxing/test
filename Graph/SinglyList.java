package Graph;

public class SinglyList<T> {
    public Node<T>  head;

    public  SinglyList(){
        this.head = new Node<T>();
    }

    public SinglyList(T[] values){
        this();
        Node<T> rear = this.head;
        for (int i = 0;i<values.length;i++){
        	rear.next = new Node<T>(values[i],null);
        	rear = rear.next;
        }
    }
    
    public boolean isEmpty(){
    	return this.head.next == null;
    }
    
    public T get(int i){
    	Node<T> p = this.head.next;
    	for(int j = 0;p!=null && j<i;j++){
    		p = p.next;
    	}
    	return (i>=0 && p!= null)?p.data:null;
    }
    public void set(int i,T x){
    	Node<T> p = this.head.next;
    	for(int j = 0;p!=null && j<i;j++){
    		p = p.next;
    	}
    	if (i>=0 && p!= null) {
		p.data = x;	
		}
    }
    public int size(){
    	Node<T> p = this.head.next;
    	int j = 0;
    	for(;p!=null;j++){
    		p = p.next;
    	}
    	return j+1;
    }
    
    public Node<T> insert(int i,T x){
    	if(x==null){
    		throw new NullPointerException("x==null");
    	}
    	Node<T> front = this.head;
    	for(int j = 0;front.next!=null && j<i;j++){
    		front = front.next;
    	}
    	front.next = new Node<T>(x,front.next);
    	return front.next;
    }
    
    public Node<T> insert(T x){
    	return insert(size()-1,x);
    }
    
    public T remove(int i){
    	Node<T> front = this.head;
    	for(int j = 0;front.next!=null && j<i;j++){
    		front = front.next;
    	}
    	if(i>=0 && front.next!=null){
    		T old = front.next.data;
    		front.next = front.next.next;
    		
    		return old;
    	}
    	return null;
    }
    
    public void clear(){
    	this.head.next = null;
    }
}
