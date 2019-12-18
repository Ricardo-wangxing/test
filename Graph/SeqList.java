package Graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;

public class SeqList<T> extends Object {
    protected Object[] elements; //对象数组存储顺序表的数据元素，保护成员
    protected int n; //顺序表元素个数（长度）
    public SeqList(int length){//构造器，传入想要开辟的数组长度。
        this.elements = new Object[length];//将分配好的空间传给elements
        this.n = 0;//此时没有数据元素，n初始化为0
    }
    public SeqList(){
        this(64);
    }//调用本类已经声明的指定参数列表的构造方法。//本方法可以理解为创建默认大小（64）的顺序表
    public SeqList(T[] values){//构造器3：传入一个已有数组来构造顺序表
        this(values.length);//调用构造器1
        for(int i = 0;i<values.length;i++){//元素深拷贝
            this.elements[i] = values;
        }
        this.n = elements.length;
    }

    public boolean isEmpty(){
        return this.n == 0;
    }//判断n的大小

    public int size(){
        return this.n;
    }//返回线性表的大小

    public T get(int i){//取指定下标元素
        if(i >= 0 && i<this.n){
            return (T)this.elements[i];//如果参数合法，返回元素，并且将其强制转型（Object to Other）
        }
        return null;//否则返回空
    }



    public void set(int i,T x){//设置制定下标的值
        if(x == null){//如果要设置的值为空，操作不合法，抛出异常信息
            throw  new NullPointerException("x == null");
        }
        if(i>=0 && i<this.n){//如果i范围合法，那么直接覆盖
            this.elements[i] = x;
        }else{
            throw new IndexOutOfBoundsException(i+"");//否则抛出错误信息
        }
    }

    public String toString(){
        String str = this.getClass().toString()+"(";//返回类名
        if(this.n > 0)
            /*
            单独摘取0出来的原因：防止格式出现错误，也就是elements[0]的前边不会显示逗号
             */
            str += this.elements[0].toString();
        for(int i = 1;i<this.n;i++){
            str += ","+this.elements[i].toString();
        }
        return str+")";
    }

    //插入x作为第i个元素，x！=null，返回x的序号。若x==null，则抛出空对象异常O（n）
    //对序号i采取容错措施，若i<0，则插入x在最前；若i>n，则插入x在最后
    //方法的时间复杂度为O(n)
    public int insert(int i,T x){
        if (x == null){
            throw new NullPointerException("x == null");
        }
        if(i < 0) i = 0;
        if(i>this.n) i = this.n;
        Object[] source = this.elements;
        /*
        为什么此处需要再申请一个source的引用?
        加入我们判断到elements数组已经满了，接下来需要扩容，但是扩容我们要改变的是elements，并且不能丢失原来的elements的
        元素，所以我们就用source来暂时存储。
         */
        if(this.n == elements.length){
            this.elements = new Object[source.length*2];
            //先把i的的前半部分数组进行深拷贝
            for (int j = 0;j < i; j++)
                this.elements[j] = source[j];
        }
        for (int j = this.n - 1;j >= i;j--)//再把i的后半部分数组深拷贝
            this.elements[j+1] = source[j];
        this.elements[i] = x;//最后拷贝i
        this.n++;//增加1
        return i;
    }

    //顺序表尾部插入x元素，返回x序号。方法重载即可
    public int insert(T x){
        return this.insert(this.n,x);
    }

    public T remove(int i){
        //删除第i个元素，0<=i<n，返回被删除元素。若i越界，则返回null
        if (i>=0 && i<this.n && this.n>0){
            T old = (T)this.elements[i];
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
        this.n = 0;//GC会自动回收
    }

    public int search(T key){
        for (int i  = 0 ;i<this.n;i++){
            if (key.equals(this.elements[i])) {
                return i;
            }
        }
        return -1;
    }
    //检查是否包含
    public boolean contains(T key){
        return this.search(key) != -1;
    }

    public int insterDifferent(T x){
        int judge = search(x);
        if(judge == -1){
            insert(x);
            return this.n;
        }
        else{
            return -1;
        }
    }

    public T remove(T key){
        int d = search(key);
        return remove(d);
    }

    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if(obj instanceof SeqList<?>){//若obj引用顺序表实例。SeqList<?>是所有SeqList<T>的父类。
            /*
            instanceof 严格来说是Java中的一个双目运算符，用来测试一个对象是否为一个类的实例，用法为：
            boolean result = obj instanceof Class
　　        其中 obj 为一个对象，Class 表示一个类或者一个接口，当 obj 为 Class 的对象，或者是其直接或间接子类，或者是其接口的实现类，结果result 都返回 true，否则返回false。
　　        注意：编译器会检查 obj 是否能转换成右边的class类型，如果不能转换则直接报错，如果不能确定类型，则通过编译，具体看运行时定
             */
            SeqList<T> list = (SeqList<T>)obj;
            if(this.n == list.n){
                for (int i = 0;i<this.n;i++){
                    if(!(this.get(i).equals(list.get(i))))
                        return false;
                }
            }
        }
        return false;
    }

    public void sort(Comparator<? super T> c) {
        // 将排序交给Arrays去实现
        Arrays.sort((T[]) elements, 0, n, c);

    }
}
