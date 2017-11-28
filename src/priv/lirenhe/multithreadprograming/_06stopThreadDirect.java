package priv.lirenhe.multithreadprograming;

public class _06stopThreadDirect {
	public static void main(String[] args) throws InterruptedException {
		MyThread01 thread=new MyThread01();
		System.out.println("启动");
		thread.start();
		Thread.sleep(2000);
		System.out.println("main 在睡眠2秒后使thread中断");
		thread.interrupt();
		System.out.println("同时，main也终止");
	}
}

class MyThread01 extends Thread{
	@Override
	public void run() {
		try {
			for(int i=0;i<50000;i++){
				if(this.interrupted()){
					System.out.println(this.getName()+"停止了");
					throw new InterruptedException();
				}
				Thread.sleep(100);
				System.out.println("i="+(i+1));
			}
			System.out.println("虽然线程已经停止了，但是仍然执行了for循环后面这条语句");
		} catch (InterruptedException e) {
			System.out.println("转为thread执行，如果interupted就——异常中断线程");
			e.printStackTrace();
		}
	}
}
