package priv.lirenhe.multithreadprograming;

/*
 * 测试currentThread方法,了解current和this的区别:谁调用的start方法，那个线程就是currentThread，
 * 而这个线程中的被执行的线程只是this线程
 * 
 */
public class _04currentThread {
	public static void main(String[] args) {
		/**
		 * 测试currentThread方法
		 */
		/*ThreadTest04 threadTest04=new ThreadTest04();
		threadTest04.start();
		*/
		
		
		/**
		 * 了解current和this的区别：start调用者便是current，run所在线程便是this
		 */
		/*ThreadTest05 threadTest05=new ThreadTest05();
		Thread thread=new Thread(threadTest05);
		System.out.println("thread isAlive:"+thread.isAlive());
		thread.setName("A");
		thread.start();
		System.out.println("thread isAlive:"+thread.isAlive());
		*/
		/**
		 * 为什么出现current和this的区别
		 */
		ThreadTest05 threadTest05_1=new ThreadTest05();
		System.out.println("threadTest05_1 isAlive:"+threadTest05_1.isAlive());
		threadTest05_1.start();
		System.out.println("threadTest05_1 isAlive:"+threadTest05_1.isAlive());
	}
}

class ThreadTest04 extends Thread{
	public ThreadTest04(){
		super();
		System.out.println("构造方法："+Thread.currentThread().getName());
	}
	
	@Override
	public void run() {
		super.run();
		System.out.println("run:"+Thread.currentThread().getName());
	}
}

class ThreadTest05 extends Thread{
	/**
	 * 比较主线程和this线程的区别
	 */
	public ThreadTest05(){
		System.out.println("main线程生成子线程时，main在执行run，整个过程时main线程run，current线程必是活的");
		System.out.println("main线程就是当前线程"+Thread.currentThread().getName()+"：isAlive:"+Thread.currentThread().isAlive());
		System.out.println("但是this线程指的是调用isAlive方法所属对象线程"+this.getName()+"：isAlive:"+this.isAlive()+"可能是活的，也可能是死的");
	}
	@Override
	public void run() {
		/**
		 * 比较this线程和当前线程的区别
		 */
		super.run();
		System.out.println("此时，正在执行子线程，那么当前线程就是该子线程，因为它在run，在run必为活的");
		System.out.println("所以，当前线程是"+Thread.currentThread().getName()+"：isAlive:"+Thread.currentThread().isAlive());
		System.out.println("而this线程是调用isAlive方法所属对象线程"+this.getName()+"：isAlive:"+this.isAlive());
	}
}