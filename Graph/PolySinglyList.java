package Graph;
 
public class PolySinglyList<T extends Comparable<? super T> & Addible<T>>
        extends SortedSinglyList<T>
{
    public PolySinglyList()                                 
    {
        super();                                            
    }
    public PolySinglyList(T terms[])                        
    {
        super(terms);
    }
    public PolySinglyList(PolySinglyList<T> list)           
    {
        super(list);                                        
    }
       
    public void addAll(PolySinglyList<T> list)              
    {
        Node<T> front=this.head, p=front.next;
        Node<T> q=list.head.next;
        while (p!=null && q!=null)
            if (p.data.compareTo(q.data)==0)                
            {
                p.data.add(q.data);                         
                if (p.data.removable())                     
                {                                           
                    front.next=p.next;                      
                    p=front.next;
                }
                else 
                {
                    front = p;                              
                    p = p.next;
                }
                q = q.next;
            }
            else if (p.data.compareTo(q.data)<0)
                 {
                     front = p;       
                     p = p.next;
                 }
                 else
                 {
                     front.next = new Node<T>(q.data, p);   
                     q = q.next;
                 }
        while (q!=null)                                     
        {
            front.next = new Node<T>(q.data, null);
            front = front.next;
            q = q.next;
        }
    }
}
 
 
 


