package priv.lirenhe.multithreadprograming;

//假死：线程处于waiting状态。
//notfiy唤醒的是应该是异类，如生产者唤醒消费者，这样才有意义，如果多线程都使用wait-notify导致notify唤醒的是同类，这就没有意义了。
//解决方法：即唤醒同类也唤醒异类，使用notifyAll

//生产者和消费者的n:m举例，不需要专注这些代码，关键是注意使用notifyAll防止假死现象
public class _918multi_wait_notify {
	public static void main(String[] args) {
		try {
			Object lock=new Object();
			_918Producer producer=new _918Producer(lock);		
			_918Consumer consumer=new _918Consumer(lock);
			
			_918Thread01[] pThreads = new _918Thread01[2];
			_918Thread02[] rThreads = new _918Thread02[2];
			
			//不是多个生产者和消费者的思维    而是一个生产者或者一个消费者多线程化
			//引出一个问题：多个生产者和一个生产者多线程的区别。（锁可以是一样的，成员变量绝不一样）——多线程化更好
			for(int t=0;t<2;t++){
				pThreads[t]=new _918Thread01(producer);
				pThreads[t].setName("生产者"+t+1);
				rThreads[t]=new _918Thread02(consumer);
				rThreads[t].setName("消费者"+t+1);
				pThreads[t].start();
				rThreads[t].start();
			}
			Thread.sleep(1000);
			
			Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
			Thread.currentThread().getThreadGroup().enumerate(threadArray);
			for(int i=0;i<threadArray.length;i++){
				System.out.println(threadArray[i].getName()+" "+threadArray[i].getState());
			}
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
}

class _918Thread01 extends Thread{
	//如何构造多线程-线程数组
	_918Producer producer;
	public _918Thread01(_918Producer producer){
		super();
		this.producer=producer;
	}
	@Override
	public void run() {
		super.run();
		while(true){
			producer.doProduce();
		}
	}
	
}
class _918Thread02 extends Thread{
	_918Consumer consumer;
	public _918Thread02(_918Consumer consumer){
		this.consumer=consumer;
	}
	@Override
	public void run() {
		super.run();
		while(true){
			consumer.doConsume();
		}
	}
}


//生产者
class _918Producer{
	Object lock;
	int produceNum=0;
	public _918Producer(Object lock){
		this.lock=lock;
	}
	public void doProduce(){
		try{
			synchronized (lock) {
				while(!_918ValueObject.value.equals("")){
					System.out.println(Thread.currentThread().getName()+"执行，此时物品非空，不用急于生产，暂停并释放锁——处于waiting");
					lock.wait();
				}
				_918ValueObject.value = "one product";
				System.out.println(Thread.currentThread().getName()+"生产物品" + ++produceNum + "次" + "--向消费者发出通知");
				lock.notify();
					
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}

//生产者
class _918Consumer{
	Object lock;
	int consumeNum=0;
	public _918Consumer(Object lock){
		this.lock=lock;
	}
	public void doConsume(){
		try{
			synchronized (lock) {
				while(_918ValueObject.value.equals("")){
					System.out.println(Thread.currentThread().getName()+"执行，此时物品空，无可使用，暂停并释放锁——处于waiting");
					lock.wait();
				}
				_918ValueObject.value = "";
				System.out.println(Thread.currentThread().getName()+"消费物品" + ++consumeNum + "次" + "--向生产者发出通知");
				lock.notify();
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}

//资源
class _918ValueObject{
	public static String value="";
}