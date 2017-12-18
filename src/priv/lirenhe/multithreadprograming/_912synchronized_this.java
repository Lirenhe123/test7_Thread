package priv.lirenhe.multithreadprograming;

//区别使用和不使用同步代码块的区别：关键是运行效率（耗时）
//结论：我发现并没有出现什么不同，甚至使用syn代码块还要慢一点

//synchronized代码块之间具有同步性：当一个线程访问一个object的一个syn(this)方法时，其他线程不能访问该object的其他syn方法
//从而证明syn使用的“对象监视器”是一个,也就是当前对象this。

//syn方法锁定当前对象，syn块锁定当前对象：一个object的两个方法，一个syn块，一个syn方法，如果两个线程方法分别访问该两个方法
//仍然出现同步，则证明syn块锁的是当前对象（要想访问一个对象的syn方法，必须获得该对象的对象锁）
public class _912synchronized_this {


	public static void main(String[] args) {
		Task task = new Task();
		MyThread0111 thread01 = new MyThread0111(task);
		MyThread02 thread02 = new MyThread02(task);
		thread01.start();
		thread02.start();

		for(;;){
			if (!thread01.isAlive() && !thread02.isAlive()) {
				int beginTime = 0;
				int endTime = 0;
				if (CommonUtils.beginTime2 < CommonUtils.beginTime1) {
					beginTime = CommonUtils.beginTime2;
				} else {
					beginTime = CommonUtils.beginTime1;
				}
				if (CommonUtils.endTime2 > CommonUtils.endTime1) {
					endTime = CommonUtils.endTime2;
				} else {
					endTime = CommonUtils.endTime1;
				}
				
				System.out.println("耗时：" + (endTime - beginTime));
				break;
			}
		}

	}


	
}


class CommonUtils {
	public static int beginTime1;
	public static int beginTime2;
	public static int endTime1;
	public static int endTime2;
}


//多个线程+syn 执行时间


class Task {
	private String mydata01;
	private String mydata02;
	
	 synchronized public void doLongTask(){
		 //加了syn快更加耗时 

		try{

			System.out.println("task begin");
			Thread.sleep(4000);
			mydata01="长时间执行返回的值1 该线程是"+Thread.currentThread().getName();
			Thread.sleep(4000);
			mydata02="长时间执行返回的值2 该线程是"+Thread.currentThread().getName();
			System.out.println(mydata01);
			System.out.println(mydata02);
			System.out.println("task end");
		
			
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
}

class MyThread0111 extends Thread{
	Task task=new Task();
	public MyThread0111(Task task){
		this.task=task;
	}
	@Override
	public void run() {
		super.run();
		CommonUtils.beginTime1=(int) System.currentTimeMillis();
		task.doLongTask();
		CommonUtils.endTime1=(int) System.currentTimeMillis();
	}
	
}

class MyThread02 extends Thread{
	Task task=new Task();
	public MyThread02(Task task){
		this.task=task;
	}
	@Override
	public void run() {
		super.run();
		CommonUtils.beginTime2=(int) System.currentTimeMillis();
		task.doLongTask();
		CommonUtils.endTime2=(int) System.currentTimeMillis();
	}
	
}



