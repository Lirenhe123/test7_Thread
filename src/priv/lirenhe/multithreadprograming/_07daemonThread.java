package priv.lirenhe.multithreadprograming;

public class _07daemonThread {
	public static void main(String[] args) {
		try{
			MyThread03 thread=new MyThread03();
			thread.setDaemon(true);
			thread.start();
			Thread.sleep(9000);
			System.out.println("main线程停止了，他的守护线程也同时停止,thread 不会再次打印");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}

class MyThread03 extends Thread{
	private int i;
	@Override
	public void run() {
		super.run();
		try{
			while(true){
				System.out.println("i="+(i++));
				Thread.sleep(1000);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
