package priv.lirenhe.multithreadprograming;


//如果一个对象中有很多syn方法或syn块，但是这些同步方法中有一些方法之间不需要同步（可以异步），这时就需要锁非this对象来提高效率
//对象监视器：对这个对象加锁，其他线程使用这个对象中的同步方法时，检测这个对象是否有锁，如果.....
//那就可锁不同的对象，只对需要同步的方法之间锁同一个对象，从而可以减少都锁同一个对象导致的不必要的阻塞

//结论：只有不同的对象锁的多个线程时异步的，同一个对象锁，多个线程时同步的。（使用：将一个new对象放在成员变量而不是局部变量
//。成员变量在一个对象中是唯一的。）
public class _913synchronized_notthis {
	public static void main(String[] args) {
		_913Task task=new _913Task("objWithSyn1","objWithSyn2");
		_913Thread01 thread01=new _913Thread01(task);
		_913Thread02 thread02=new _913Thread02(task);
		
		
		thread01.start();
		thread02.start();
	}
}

class _913Task{
	private String objectWillSyb01;
	private String objectWillSyb02;
//	private String objectWillSyb03=new String("alsdl");
	
	
	public _913Task(String obj01,String obj02){
		this.objectWillSyb01=obj01;
		this.objectWillSyb02=obj02;
	}
	
	public void doTask(){
		try{
			synchronized (objectWillSyb01) {
				System.out.println("当前线程："+Thread.currentThread().getName()+"task begin");
				Thread.sleep(3000);
				System.out.println("当前线程："+Thread.currentThread().getName()+"task end");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void doTask02(){
		try{
			synchronized (objectWillSyb02) {
				System.out.println("当前线程："+Thread.currentThread().getName()+"task begin");
				Thread.sleep(3000);
				System.out.println("当前线程："+Thread.currentThread().getName()+"task end");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

class _913Thread01 extends Thread{
	_913Task task;
	public _913Thread01(_913Task task){
		super();
		this.task=task;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		task.doTask();
	}
}

class _913Thread02 extends Thread{
	_913Task task;
	public _913Thread02(_913Task task){
		super();
		this.task=task;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		task.doTask02();
	}
}























