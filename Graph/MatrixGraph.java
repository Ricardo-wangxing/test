package Graph;

public class MatrixGraph<T> extends AbstractGraph<T>
{
    protected Matrix matrix;                                
    
     
    public MatrixGraph(int length)
    {
        super(length);                                    
        this.matrix = new Matrix(length);                 
    }
    public MatrixGraph()                                    
    {
        this(10);                                           
    }
    public MatrixGraph(T[] vertices)                        
    {
        this(vertices.length);                              
        for (int i=0; i<vertices.length; i++)
            this.insertVertex(vertices[i]);                 
    } 
    public MatrixGraph(T[] vertices, Triple[] edges)        
    {
        this(vertices);                                     
        for (int j=0; j<edges.length; j++)
            this.insertEdge(edges[j]);                      
    }

    public String toString()                                
    {
        String str = super.toString()+"邻接矩阵:  \n";
 
        int n = this.vertexCount();                         
        for (int i=0; i<n; i++)
        {
            for (int j=0; j<n; j++)
                if (this.matrix.get(i,j)==MAX_WEIGHT)
                    str += "     ∞";
                else  str += String.format("%6d", this.matrix.get(i,j));
            str+="\n";
        }
        return str;
    }
     

     
    public void insertEdge(int i, int j, int weight)        
    {
        if (i!=j)                                           
        {
            if (weight<=0 || weight>MAX_WEIGHT)             
                weight=MAX_WEIGHT;
            this.matrix.set(i,j,weight);                    
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
        if (i >= this.matrix.getRows())                     
            this.matrix.setRowsColumns(i+1,i+1);            
        for (int j=0; j<i; j++)                             
        {
            this.matrix.set(i,j,MAX_WEIGHT);
            this.matrix.set(j,i,MAX_WEIGHT);  
        }
        return i;                                           
    }     
    
     
    public void removeEdge(int i, int j)                    
    {
        if (i!=j)
            this.matrix.set(i, j, MAX_WEIGHT);              
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
            this.vertexlist.remove(i);                      
            for (int j=i+1; j<n; j++)                       
                for (int k=0; k<n; k++)
                    this.matrix.set(j-1, k, this.matrix.get(j,k));
            for (int j=0; j<n; j++)
                for (int k=i+1; k<n; k++)                   
                    this.matrix.set(j, k-1, this.matrix.get(j,k));
            this.matrix.setRowsColumns(n-1, n-1);           
        }
        else throw new IndexOutOfBoundsException("i="+i);   
    }
    
     
    public int weight(int i, int j)            
    {
        return this.matrix.get(i,j);              
    }
    
     
    protected int next(int i, int j)
    {
        int n=this.vertexCount();
        if (i>=0 && i<n && j>=-1 && j<n && i!=j) 
            for (int k=j+1; k<n; k++)                       
                if (this.matrix.get(i,k)>0 && this.matrix.get(i,k)<MAX_WEIGHT) 
                    return k;
        return -1;         
    }  

    public void removeVertex(T vertex)            
    {
        int i=this.vertexlist.search(vertex);     
        this.removeVertex(i);                     
    }

    
}
 
