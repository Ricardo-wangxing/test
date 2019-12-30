package Graph;

public class SortedSinglyList<T extends Comparable<? super T>> extends SinglyList<T>
{    
    public SortedSinglyList()                               
    {
        super();                                            
    }
    
    public SortedSinglyList(T[] values)                     
    {
        super();                                            
        for (int i=0;  i<values.length;  i++)               
            this.insert(values[i]);                         
    }

    public SortedSinglyList(SortedSinglyList<T> list)       
    {
        super(list);                                        
    }   

     
    public SortedSinglyList(SinglyList<T> list)
    {
        super();                                            
        for (Node<T> p=list.head.next;  p!=null;  p=p.next) 
            this.insert(p.data);                            
    }
    
     
    public void set(int i, T x) 
    {
        throw new UnsupportedOperationException("set(int i, T x)");
    }
    public Node<T> insert(int i, T x) 
    {
        throw new UnsupportedOperationException("insert(int i, T x)");
    }
    
     
     
    public Node<T> insert(T x)
    {
        Node<T> front=this.head, p=front.next;              
        while (p!=null && x.compareTo(p.data)>0)            
        {
            front = p;
            p = p.next;
        }
        front.next = new Node<T>(x, p);                     
        return front.next;                                  
    }
    
     
     
    public Node<T> search(T key) 
    {
        for (Node<T> p=this.head.next;  p!=null && key.compareTo(p.data)>=0;  p=p.next)
        {
 
            if (key.compareTo(p.data)==0)                   
                return p;
        }
 
        return null; 
    }
    
     
     
    public Node<T> insertDifferent(T x)
    {
        Node<T> front=this.head, p=front.next;              
        while (p!=null && x.compareTo(p.data)>0)            
        {
            front = p;
            p = p.next;
        }
        if (p!=null && x.compareTo(p.data)==0)
            return p;                                       
        return front.next = new Node<T>(x, p);              
    }

     
    public T remove(T key)
    {
        Node<T> front=this.head, p=front.next;              
        while (p!=null && key.compareTo(p.data)>0)          
        {
            front = p;
            p = p.next;
        }
        if (p!=null && key.compareTo(p.data)==0)            
        {
            front.next = p.next;                            
            return p.data;
        }
        return null;
    }
      
    public void concat(SinglyList<T> list)                  
    {
        throw new UnsupportedOperationException("concat(SinglyList<T> list)");
    }
     
     
    public void addAll(SinglyList<T> list)
    {
        for (Node<T> p=list.head.next;  p!=null;  p=p.next) 
            this.insert(p.data);                            
    }    
    
    public SortedSinglyList<T> union(SinglyList<T> list)
    {
        SortedSinglyList<T> result = new SortedSinglyList<T>(this);    
        result.addAll(list);
        return result;
    }
     
    public void merge(SortedSinglyList<T> list)             
    {
        Node<T> front=this.head, p=front.next;              
        Node<T> q=list.head.next;                           
        while (p!=null && q!=null)                          
            if ((p.data).compareTo(q.data)<0)               
            {
                front = p;
                p = p.next;
            }
            else
            {                                               
                front.next = q;
                q = q.next;
                front = front.next;
                front.next = p;
            }
        if (q!=null)                                        
            front.next=q;
        list.head.next=null;                                
    }
     
    public SortedSinglyList<T> mergeWith(SortedSinglyList<T> list)  
    {
        Node<T> p=this.head.next, q=list.head.next;         
        SortedSinglyList<T> result = new SortedSinglyList<T>();
        Node<T> rear = result.head;                         
        while (p!=null || q!=null)                          
            if (p!=null && (q!=null && (p.data).compareTo(q.data)<=0 || q==null))
            {                                               
                rear.next = new Node<T>(p.data,null);
                rear = rear.next;
                p = p.next;
            }
            else if (q!=null && (p!=null && (p.data).compareTo(q.data)>0 || p==null))
            {                                               
                rear.next = new Node<T>(q.data,null);
                rear = rear.next;
                q = q.next;
            }
        return result;
    }
}
 

