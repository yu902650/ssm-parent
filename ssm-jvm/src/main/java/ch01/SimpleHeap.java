package ch01;

/**
 * @author bo bo
 * @date 2019/6/29 11:48
 * @desc
 */
public class SimpleHeap {

    //成员变量
    private int id ;

    public SimpleHeap(int id) {
        super();
        this.id = id;
    }

    public void print(){

        System.out.println("My id is " + id);
    }

    public static void main(String[] args) {
        //s1 s2局部变量
        SimpleHeap s1 = new SimpleHeap(1);
        SimpleHeap s2 = new SimpleHeap(2);

        s1.print();
        s2.print();

    }

}
