package Graph;

public class SinglyList<T> extends Object implements java.lang.Iterable<T>   
 
{
    public Node<T> head;                                       
    public SinglyList()                                     
    {
        this.head = new Node<T>();                          
    }

    public SinglyList(T[] values)
    {
        this();                                             
        Node<T> rear=this.head;                             
        for (int i=0; i<values.length; i++)                 
            if (values[i]!=null)
            {
                rear.next=new Node<T>(values[i],null);      
                rear = rear.next;                           
            }
    }

    public boolean isEmpty()                                
    {
        return this.head.next==null;
    }
  
    public T get(int i)                                     
    {
        Node<T> p=this.head.next;
        for (int j=0; p!=null && j<i; j++)                  
            p = p.next;
        return (i>=0 && p!=null) ? p.data : null;           
    }
   
    public void set(int i, T x)
    {
        if (x==null)
            throw new NullPointerException("x==null");      
        Node<T> p=this.head.next;
        for (int j=0; p!=null && j<i; j++)                  
            p = p.next;
        if (i>=0 && p!=null)
            p.data = x;                                     
        else throw new IndexOutOfBoundsException(i+"");     
    }     
    
     
    public String toString()
    {
        String str=this.getClass().getName()+"(";           
 
        for (Node<T> p=this.head.next;  p!=null;  p=p.next) 
        {   str += p.data.toString();
            if (p.next!=null) 
                str += ",";                                 
        }
        return str+")";                                     
    }
 
     
    public int size()                                       
    {
        int i=0; 
        for (Node<T> p=this.head.next;  p!=null;  p=p.next)  
            i++;
        return i;
    }

    public Node<T> insert(int i, T x)
    {
        if (x==null)
            throw new NullPointerException("x==null");      
        Node<T> front=this.head;                            
        for (int j=0;  front.next!=null && j<i;  j++)       
            front = front.next;
        front.next = new Node<T>(x, front.next);            
        return front.next;                                  
    }
    public Node<T> insert(T x)                              
    {
        return insert(Integer.MAX_VALUE, x);
                              
 
    }
      
     
    public T remove(int i)     
    {
        Node<T> front=this.head;                            
        for (int j=0;  front.next!=null && j<i;  j++)       
            front = front.next;
        if (i>=0 && front.next!=null)                       
        {
            T old = front.next.data;                        
            front.next = front.next.next;                   
                                                            
            return old;
        }
        return null;                                        
 
    }

    public void clear()                                     
    {
        this.head.next=null;                                
    }


    public Node<T> search(T key) 
    {
        for (Node<T> p=this.head.next;  p!=null;  p=p.next)
            if (key.equals(p.data))                         
                return p;
        return null;
    }

    public boolean contains(T key)                
    {
        return this.search(key)!=null;                      
    }

    public Node<T> insertDifferent(T x)
    {
        Node<T> front=this.head, p=front.next;              
        while (p!=null && !p.data.equals(x))                
        {
            front = p; 
            p = p.next;
        }
        if (p!=null)                                        
        {
            System.out.println("x="+x+"，元素重复，未插入。");
            return p;
        }
        return front.next = new Node<T>(x,null);            
    }
    
    public T remove(T key)              
    {
        Node<T> front=this.head, p=front.next;
        while (p!=null && !key.equals(p.data))              
        {
            front = p;                                      
            p=p.next;
        }
        if (p!=null)                                        
        {
            front.next = p.next;                            
            return p.data;
        }
        return null;
    }
     

    public SinglyList(SinglyList<T> list)                   
    {
        this();                                             
        Node<T> rear=this.head;
        for (Node<T> p=list.head.next;  p!=null;  p=p.next)   
        {
            rear.next = new Node<T>(p.data, null);         
            rear = rear.next;                              
        }
    }
    
    public boolean equals(Object obj)             
    {
        if (obj == this)
            return true;
        if (!(obj instanceof SinglyList<?>))
            return false;
        Node<T> p=this.head.next;
        Node<T> q=((SinglyList<T>)obj).head.next;
        while (p!=null && q!=null && p.data.equals(q.data))
        {
            p=p.next;
            q=q.next;
        }
        return p==null && q==null;
    }   

    public Node<T> first()                                  
    {
        return this.head.next;                              
    }
    public Node<T> next(Node<T> p)                          
    {
        return (this.head.next==null || p==null) ? null : p.next;
    }
    public Node<T> previous(Node<T> p)                      
    {
        if (this.head.next==null || p==null || p==this.head.next)
            return null;
        Node<T> front=this.head.next;
        while (front.next!=p)
            front = front.next;
        return front;
    }
    public Node<T> last()                                   
    {
        Node<T> p=this.head.next;
        while (p!=null && p.next!=null)
            p = p.next;
        return p;                                           
    }  
  
    public void concat(SinglyList<T> list)
    {
        Node<T> rear=this.head;
        while (rear.next!=null)                             
            rear = rear.next;
        rear.next = list.head.next;                         
        list.head.next=null;                                
    }

    public void addAll(SinglyList<T> list)                  
    {
        this.concat(new SinglyList<T>(list));               
    }
     
    public SinglyList<T> union(SinglyList<T> list)
    {
        SinglyList<T> result = new SinglyList<T>(this);    
        result.addAll(list);
        return result;
    }

    public java.util.Iterator<T> iterator()       
    {
        return new SinglyIterator();
    }

    private class SinglyIterator implements java.util.Iterator<T>  
    {
        Node<T> current=SinglyList.this.head;     
        Node<T> front=null;                       

        public boolean hasNext()                  
        {
            return this.current!=null && this.current.next!=null;
        }

        public T next()                           
        {
            if (this.hasNext())
            {
                this.front = this.current;
                this.current = this.current.next;
                return this.current.data;
            }
            else throw new java.util.NoSuchElementException();   
        }

        public void remove()                      
        {
            if (this.front!=null)
            {
                this.front.next = this.current.next;  
                this.current = this.front;
                this.front=null;                      
            }
            else throw new java.lang.IllegalStateException(); 
 
        }
    } 
     
}
 
