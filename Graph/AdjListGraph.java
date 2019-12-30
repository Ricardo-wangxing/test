package Graph;
public class AdjListGraph<T> extends AbstractGraph<T>
{
    protected LinkedMatrix adjlist;                         
    
     
    public AdjListGraph(int length)
    {
        super(length);                                      
        this.adjlist = new LinkedMatrix(length, length);    
    }
    
     
    public AdjListGraph()                                   
    {
        this(10);                                           
    }
    public AdjListGraph(T[] vertices)                       
    {
        this(vertices.length);                              
        for (int i=0; i<vertices.length; i++)
            this.insertVertex(vertices[i]);                 
    } 
    public AdjListGraph(T[] vertices, Triple[] edges)       
    {
        this(vertices);
        for (int j=0; j<edges.length; j++)
            this.insertEdge(edges[j]);                      
    }

    public String toString()                                
    {
        return super.toString()+"出边表：\n"+this.adjlist.toString();
    }

     
    public void insertEdge(int i, int j, int weight)        
    {
        if (i!=j)                                           
        {
            if (weight<0 || weight>=MAX_WEIGHT)             
                weight=0;
            this.adjlist.set(i,j,weight);                   
             
        }
        else throw new IllegalArgumentException("不能插入自身环，i="+i+"，j="+j);
    }
    public void insertEdge(Triple edge)                     
    {
        this.insertEdge(edge.row, edge.column, edge.value);
    }
    
     
    public int insertVertex(T x)                            
    {
        int i = this.vertexlist.insert(x);                  
        if (i >= this.adjlist.getRows())                    
            this.adjlist.setRowsColumns(i+1, i+1);          
        return i;                                           
    }
    
     
    public void removeEdge(int i, int j)                    
    {
        if (i!=j)
            this.adjlist.set(new Triple(i,j,0));            
    }
    public void removeEdge(Triple edge)                     
    {
        this.removeEdge(edge.row, edge.column);
    }

     
    public void removeVertex(int i)                         
    {
        int n=this.vertexCount();                           
        if (i>=0 && i<n)
        {
             
            SortedSinglyList<Triple> link = this.adjlist.rowlist.get(i);
            for (Node<Triple> p=link.head.next; p!=null; p=p.next)   
                this.removeEdge(p.data.toSymmetry());                

            n--;                                  
            this.adjlist.rowlist.remove(i);       
            this.adjlist.setRowsColumns(n, n);    

            for (int j=0; j<n; j++)                         
            {
                link = this.adjlist.rowlist.get(j);
                for (Node<Triple> p=link.head.next; p!=null; p=p.next) 
                {   if (p.data.row > i)
                        p.data.row--; 
                    if (p.data.column > i)
                        p.data.column--;
                }
            }
            this.vertexlist.remove(i);            
        }
        else throw new IndexOutOfBoundsException("i="+i); 
    }
    
     
    public int weight(int i, int j)                   
    {     
        if (i==j)
            return 0;
        int weight = this.adjlist.get(i,j);          
        return weight!=0 ? weight : MAX_WEIGHT;      
    }
    
     
    protected int next(int i, int j)
    {
        int n=this.vertexCount();
        if (i>=0 && i<n && j>=-1 && j<n && i!=j)
        {
            SortedSinglyList<Triple> link = this.adjlist.rowlist.get(i); 
            Node<Triple> find=link.head.next;           
            if (j==-1)
                return find!=null ? find.data.column : -1;  
            find = link.search(new Triple(i,j,0));      
            if (find!=null)                             
            {
                find = find.next;                       
                if (find!=null)                
                    return find.data.column;            
            }
        }
        return -1;
    }
}
 

