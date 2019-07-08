package ch02.stack;

/**
 * @author bo bo
 * @date 2019/7/7 16:42
 * @desc
 */
public class Stack {

    public Object[] elements;
    private int size = 0;

    private static final int Cap = 16;

    public Stack() {
        elements = new Object[Cap];

    }

    //入栈
    public void push(Object e) {
        elements[size] = e;
        size++;

    }


    //出栈
    public Object pop() {
        size = size - 1;

        //不置空 就会发生内存泄漏
        Object o = null;
        return o;
    }

}
