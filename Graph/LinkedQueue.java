package Graph;

public class LinkedQueue<T>implements Queue<T> {
	private Node<T>front,rear;
	public LinkedQueue() {
		 
	this.front = this.rear = null;
	}
	
	@Override
	public boolean isEmpty() {
	return this.front == null && this.rear == null;
	}

	@Override
	public boolean add(T x) {
		if(x==null){
			return false;
		}
		Node<T> q = new Node<T>(x,null);
		if(this.front == null)
			this.front = q;
		else this.rear.next = q;
		this.rear = q;
		return true;
	}

	@Override
	public T peek() {
		return this.isEmpty()?null:this.front.data;
	}

	@Override
	public T poll() {
		if(isEmpty())
			return null;
		T x = this.front.data;
		this.front = this.front.next;
		if(this.front == null)
			this.rear = null;
		return x;
	}
	
	public void remove_all(){
		this.front = this.rear = null;
	}
	
	public String get_all(){
		String all = "";
		Node<T> temp = front;
		while(temp != null){
			all += (String)temp.data+" \n";
			temp = temp.next;
		}
		return all;
	}

}
