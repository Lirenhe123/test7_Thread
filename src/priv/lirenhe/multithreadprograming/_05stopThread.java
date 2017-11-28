package priv.lirenhe.multithreadprograming;

/**
 * 使用异常法和return停止线程
 * 对于其它种情况以后考虑
 * @author User
 *
 */

public class _05stopThread {
	public static void main(String[] args) {
		try{
			MyThread thread=new MyThread();
			thread.start();
			Thread.sleep(2000);
			thread.interrupt();
		}catch(InterruptedException e){
			System.out.println("main catch");
			e.printStackTrace();
		}
	}
	
}

/**
 * 使用return和break的区别就在于for后面的打印语句
 * @author User
 *
 */
class MyThread extends Thread{
	@Override
	public void run() {
		super.run();
		for(int i=0;i<5000000;i++){
			if(this.interrupted()){
				System.out.println("该线程"+getName()+"已经停止");
//				break;
				return;
			}
			System.out.println("i="+(i+1));
		}
		System.out.println("虽然线程已经停止了，但是仍然执行了for循环后面这条语句");
	}
}