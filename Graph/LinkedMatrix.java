package Graph;
public class LinkedMatrix                                   
{
    int rows, columns;                              
    SeqList<SortedSinglyList<Triple>> rowlist;      
    public LinkedMatrix(int m, int n)             
    {
        if (m>0 && n>0)
        {
            this.rows = m;
            this.columns = n;
            this.rowlist = new SeqList<SortedSinglyList<Triple>>();   
 
            for (int i=0; i<m; i++)                                   
                this.rowlist.insert(new SortedSinglyList<Triple>());  
 
        }
        else throw new IllegalArgumentException("矩阵行列数不能≤0，m="+m+"，n="+n);
    }
    public LinkedMatrix(int m)                              
    {
        this(m, m);
    }
    
    public LinkedMatrix(int m, int n, Triple[] tris)        
    {
        this(m, n);
        for (int i=0; i<tris.length; i++)
            this.set(tris[i]);                              
    }

    public int getRows()                                    
    {
        return this.rows;        
    }
    public int getColumns()                                 
    {
        return this.columns;        
    }
       
     
     
    public int get(int i, int j)
    {
        if (i>=0 && i<this.rows && j>=0 && j<this.columns) 
        {
             
            Node<Triple> find=this.rowlist.get(i).search(new Triple(i,j,0));
            return (find!=null) ? find.data.value : 0;      
        }
        throw new IndexOutOfBoundsException("i="+i+"，j="+j);
    }
    
     
     
     
    public void set(int i, int j, int x)
    {
        if (i>=0 && i<this.rows && j>=0 && j<this.columns) 
        {
            SortedSinglyList<Triple> link = this.rowlist.get(i); 
            if (x==0) 
                link.remove(new Triple(i,j,0));   
            else
            {    
                Triple tri = new Triple(i,j,x);
                Node<Triple> find=link.search(tri);  
                if (find!=null)
                    find.data.value = x;                    
                else link.insert(tri);                      
            }
        }
        else throw new IndexOutOfBoundsException("i="+i+"，j="+j); 
    }
    
    public void set(Triple tri)                   
    {
        this.set(tri.row, tri.column, tri.value);
    }

     
    public String toString()                                
    {
        String str="";
        for (int i=0; i<this.rowlist.size(); i++)          
            str += i+" -> "+this.rowlist.get(i).toString()+"\n"; 
        return str;
    }
        
    public void printMatrix()                                
    {
        System.out.println("矩阵"+this.getClass().getName()+"（"+rows+"×"+columns+"）：");
 
        for (int i=0; i<this.rows; i++)
        {
            Node<Triple> p = this.rowlist.get(i).head.next; 
            for (int j=0; j<this.columns; j++)
               if (p!=null && j==p.data.column)             
               {
                   System.out.print(String.format("%4d", p.data.value));
                   p = p.next;
               }               
               else System.out.print(String.format("%4d", 0));
            System.out.println();
        }   
    }
    
     
    public void printMatrix2()                              
    {
        System.out.println("矩阵"+this.getClass().getName()+"（"+rows+"×"+columns+"）：");
        for (int i=0; i<this.rows; i++)
        {
            for (int j=0; j<this.columns; j++)
                System.out.print(String.format("%4d", this.get(i,j)));
            System.out.println();
        }
    } 

     
    public boolean equals(Object obj)                       
    {
        if (this==obj)
            return true;
        if (!(obj instanceof LinkedMatrix))
            return false;
        LinkedMatrix mat=(LinkedMatrix)obj;        
        return this.rows==mat.rows && this.columns==mat.columns && this.rowlist.equals(mat.rowlist);
                                                            
    }
    
     
     
     
    public void setRowsColumns(int m, int n)
    {
        if (m>0 && n>0)
        {
            if (m > this.rows)                              
                for (int i=this.rows; i<m; i++)
 
                  this.rowlist.insert(new PolySinglyList<Triple>()); 
            this.rows = m;
            this.columns = n;
        }
        else throw new IllegalArgumentException("矩阵行列数不能≤0，m="+m+"，n="+n);
    }

     
    public void addAll(LinkedMatrix mat)
    {
        if (this.rows==mat.rows && this.columns==mat.columns)
            for (int i=0; i<this.rows; i++)
                this.rowlist.get(i).addAll(mat.rowlist.get(i));         
        else throw new IllegalArgumentException("两个矩阵阶数不同，不能相加");
    }
     
    public LinkedMatrix(LinkedMatrix mat)
    {
        this(mat.rows, mat.columns);              
        for (int i=0; i<this.rows; i++)   
        {
            SortedSinglyList<Triple> link=new SortedSinglyList<Triple>(mat.rowlist.get(i));
 
                                        
                                        
            for (Node<Triple> p=link.head.next;  p!=null;  p=p.next) 
                p.data = new Triple(p.data);      
            this.rowlist.set(i, link);            
        }
    }
    
    public LinkedMatrix union(LinkedMatrix mat)   
    {
        LinkedMatrix matc=new LinkedMatrix(this); 
        matc.addAll(mat);
        return matc;                              
    }
    
    public LinkedMatrix transpose()                         
    {
        LinkedMatrix trans=new LinkedMatrix(columns, rows); 
         
        for (int i=0; i<this.rows; i++)
            for (Node<Triple> p=this.rowlist.get(i).head.next; p!=null; p=p.next)
                trans.set(p.data.toSymmetry());
        return trans;
    }    
}    
 
