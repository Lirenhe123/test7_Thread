package priv.lirenhe.multithreadprograming;

/*
 * 共享数据和不共享数据
 */
public class _01shareData extends Thread{
	public static void main(String[] args) {
		/*
		 * 测试不共享数据:每个线程对象封装各自的数据，每个对象各自使用
		 */
		
		/*ThreadTest01 thread1=new ThreadTest01("A");
		ThreadTest01 thread2=new ThreadTest01("B");
		ThreadTest01 thread3=new ThreadTest01("C");*/
		
		
//		ThreadTest01 thread7=new ThreadTest01("G");
//		ThreadTest01 thread8=new ThreadTest01("H");
//		ThreadTest01 thread9=new ThreadTest01("I");
//		ThreadTest01 thread10=new ThreadTest01("J");
		
		
		/*thread1.start();
		thread2.start();
		thread3.start();*/
//		thread7.start();
//		thread8.start();
//		thread9.start();
//		thread10.start();
		
		
		/*
		 * 测试共享数据，产生线程不安全：一个线程封装成一个对象，这个对象被其他线程是使用
		 */
		ThreadTest02 theShareThread=new ThreadTest02("shareThread");
		
		Thread thread4=new Thread(theShareThread,"D");//源码：new Thread 调用的是theShareThread的run方法
		Thread thread5=new Thread(theShareThread,"E");
		Thread thread6=new Thread(theShareThread,"F");
		
		thread4.start();
		thread5.start();
		thread6.start();
		
		
	}
}
/**
 * 不共享数据：
 * @author User
 *
 */
class ThreadTest01 extends Thread{
private int count=5;
	
	public ThreadTest01(String name){
		super();
		this.setName(name);
	}
	
	@Override
	public void run() {
		super.run();
		while(count>0){
			count--;
			System.out.println(this.getName()+":"+count);
		}
	}
}

class ThreadTest02 extends Thread{
	private int count=5;
	
	public ThreadTest02(String name){
		super();
		this.setName(name);
	}
	
	@Override
	public void run() {
		super.run();
		count--;
		System.out.println(this.getName()+":"+count);
	}
}









