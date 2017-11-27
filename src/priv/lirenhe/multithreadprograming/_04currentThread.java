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
		System.out.println("thread isAlive:"+threadTest05_1.isAlive());
		threadTest05_1.start();
		System.out.println("thread isAlive:"+threadTest05_1.isAlive());
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
		System.out.println("主线程："+Thread.currentThread().getName()+"isAlive:"+Thread.currentThread().isAlive());
		System.out.println("this线程："+this.getName()+"isAlive:"+this.isAlive());
	}
	@Override
	public void run() {
		/**
		 * 比较this线程和当前线程的区别
		 */
		super.run();
		System.out.println("this线程"+this.getName()+"isAlive:"+Thread.currentThread().isAlive());
		System.out.println("当前线程："+Thread.currentThread().getName()+"isAlive:"+Thread.currentThread().isAlive());
	}
}