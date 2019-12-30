package Graph;

import java.util.Arrays;
import java.util.Comparator;

public class SeqList<T> extends Object {
    protected Object[] elements;  
    protected int n;  
    public SeqList(final int length){ 
        this.elements = new Object[length]; 
        this.n = 0; 
    }
    public SeqList(){
        this(64);
    } 
    public SeqList(final T[] values){ 
        this(values.length); 
        for(int i = 0;i<values.length;i++){ 
            this.elements[i] = values;
        }
        this.n = elements.length;
    }

    public boolean isEmpty(){
        return this.n == 0;
    } 

    public int size(){
        return this.n;
    } 

    public T get(final int i){ 
        if(i >= 0 && i<this.n){
            return (T)this.elements[i]; 
        }
        return null; 
    }


    public void set(final int i,final T x){ 
        if(x == null){ 
            throw  new NullPointerException("x == null");
        }
        if(i>=0 && i<this.n){ 
            this.elements[i] = x;
        }else{
            throw new IndexOutOfBoundsException(i+""); 
        }
    }

    public String toString(){
        String str = this.getClass().toString()+"("; 
        if(this.n > 0)
             
            str += this.elements[0].toString();
        for(int i = 1;i<this.n;i++){
            str += ","+this.elements[i].toString();
        }
        return str+")";
    }
    public int insert(int i,final T x){
        if (x == null){
            throw new NullPointerException("x == null");
        }
        if(i < 0) i = 0;
        if(i>this.n) i = this.n;
        final Object[] source = this.elements;
         
        if(this.n == elements.length){
            this.elements = new Object[source.length*2];
             
            for (int j = 0;j < i; j++)
                this.elements[j] = source[j];
        }
        for (int j = this.n - 1;j >= i;j--) 
            this.elements[j+1] = source[j];
        this.elements[i] = x; 
        this.n++; 
        return i;
    }

     
    public int insert(final T x){
        return this.insert(this.n,x);
    }

    public T remove(final int i){
         
        if (i>=0 && i<this.n && this.n>0){
            final T old = (T)this.elements[i];
            for (int j = i;j<this.n-1;j++){
                this.elements[j] = this.elements[j+1];
            }
            this.elements[this.n - 1] = null;
            this.n--;
            return old;
        }
        return null;
    }
    public void clear(){
        this.n = 0; 
    }

    public int search(final T key){
        for (int i  = 0 ;i<this.n;i++){
            if (key.equals(this.elements[i])) {
                return i;
            }
        }
        return -1;
    }
     
    public boolean contains(final T key){
        return this.search(key) != -1;
    }

    public int insterDifferent(final T x){
        final int judge = search(x);
        if(judge == -1){
            insert(x);
            return this.n;
        }
        else{
            return -1;
        }
    }

    public T remove(final T key){
        final int d = search(key);
        return remove(d);
    }

    public boolean equals(final Object obj){
        if (this == obj)
            return true;
        if(obj instanceof SeqList<?>){ 
             
            final SeqList<T> list = (SeqList<T>)obj;
            if(this.n == list.n){
                for (int i = 0;i<this.n;i++){
                    if(!(this.get(i).equals(list.get(i))))
                        return false;
                }
            }
        }
        return false;
    }

    public void sort(final Comparator<? super T> c) {
         
        Arrays.sort((T[]) elements, 0, n, c);

    }
}


