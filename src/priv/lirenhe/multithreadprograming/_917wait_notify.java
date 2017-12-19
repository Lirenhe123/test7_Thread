package priv.lirenhe.multithreadprograming;

//wait和notify的知识暂时只学到生产者和消费者的实现，其他细节问题，等到具体操作线程遇到问题时再学习

//关键是syn+while==》wait+notify
//producer:物品非空，不用急于生产，暂停并释放锁(wait)
//consumer:物品空，无可使用，暂停并释放锁(wait)
//发送通知的（调用wait和notify）的是锁对象

//illegalMonitor异常是因为调用的wait和notify不是锁对象
public class _917wait_notify {
	public static void main(String[] args) {
		Object lock=new Object();
		Producer producer = new Producer(lock);
		Consumer consumer = new Consumer(lock);

		_917Thread01 thread01 = new _917Thread01(producer);
		_917Thread02 thread02 = new _917Thread02(consumer);
		thread01.start();
		thread02.start();
	}
}

// 资源类
class ValueObject {
	public static String value = "";
}

// 生产者对象
class Producer {
	Object lock;
	int produceNum = 0;

	public Producer(Object obj) {
		this.lock = obj;
	}

	public void doProduce() {
		try {
			synchronized (lock) {
				while (!ValueObject.value.equals("")) {
					System.out.println("物品非空，不用急于生产，暂停并释放锁");
					lock.wait();
				}
				ValueObject.value = "生产物品" + ++produceNum + "次";
				System.out.println(ValueObject.value + "--向消费者发出通知");
				lock.notify();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

// 消费者对象

class Consumer {
	Object lock;
	int consumeNum = 0;

	public Consumer(Object obj) {
		this.lock = obj;
	}

	public void doConsume() {
		try {
			synchronized (lock) {
				while (ValueObject.value.equals("")) {
					System.out.println("物品空，无可使用，暂停并释放锁");
					lock.wait();
				}
				ValueObject.value = "";
				System.out.println("消费物品" + ++consumeNum + "次" + "--向生产者发出通知");
				lock.notify();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class _917Thread01 extends Thread {
	Producer producer;

	public _917Thread01(Producer producer) {
		super();
		this.producer = producer;
	}

	@Override
	public void run() {
		super.run();
		while(true){
			producer.doProduce();
		}
	}
}

class _917Thread02 extends Thread {
	Consumer consumer;

	public _917Thread02(Consumer consumer) {
		super();
		this.consumer = consumer;
	}

	@Override
	public void run() {
		super.run();
		while(true){
			consumer.doConsume();
		}
	}
}
