package ch01;

/**
 * @author bo bo
 * @date 2019/6/29 15:03
 * @desc
 *
 * stackLength =3247
 * java.lang.StackOverflowError
 *
 *
 */
public class StackOOM {

    private int stackLength = 1;

    private void diGui(int x, String y) {

        stackLength++;
        diGui(x,y);

    }

    public static void main(String[] args) {
        StackOOM stackOOM = new StackOOM();
        try {
            stackOOM.diGui(12,"bb");
        } catch (Throwable e) {
            System.out.println("stackLength =" + stackOOM.stackLength);
            e.printStackTrace();
        }
    }
}
