package priv.lirenhe.multithreadprograming;

public class _02synchronized {
	public static void main(String[] args) {
		ThreadTest03 threadTest03=new ThreadTest03("threadTest03");
		Thread thread01=new Thread(threadTest03);
		Thread thread02=new Thread(threadTest03);
		
		
		thread01.start();
		thread02.start();
	}
	
}


class ThreadTest03 extends Thread{
	private int count=5;
	private String threadName;
	
	public ThreadTest03(String name){
		super();
		this.threadName=name;
	}
	
	@Override
	synchronized public void run() {
		super.run();
		count--;
		System.out.println(this.threadName+":"+count);
		
		
	};
}