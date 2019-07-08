package ch03.dispatch;


public class DynamicDispatch {
	static abstract class Human{
		protected abstract void sayHello();
	}
	static class Man extends Human{

		@Override
		protected void sayHello() {
			System.out.println("hello,gentleman！");

		}
	}
	static class Woman extends Human{

		@Override
		protected void sayHello() {
			System.out.println("hello,lady！");

		}
	}

	public static void main(String[]args){



	}
}
