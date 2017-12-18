package priv.lirenhe.multithreadprograming;

import javax.swing.plaf.synth.SynthSpinnerUI;

//_913 虽然使得不同线程能执行同一资源对象中的不同syn非this方法块，但是导致一个问题：那个资源对象的某些成员变量将有可能
//被多个线程修改读取，导致脏读。（因为他不是对当前对象加锁，这样成员变量就暴露出来了）

//即便锁整个对象，也导致脏读：在不锁一个对象中的某个成员变量，也将导致该成员变量脏读（锁对象同步只针对一次调用该对象，
//当循环调用该对象时，锁了又打开，导致在n次循环中有其他线程启动使用该对象）

//————从根本上解决同步问题：锁对象的某个变量，使用syn非this


//针对syn的同步效果，有三种方法，最彻底的是最后一种，我觉得也是最好的一种。加了syn的三种方式，是锁的粒度不同，锁整个this对象，同步化整个方法，
//锁整个this对象，同步化部分代码，锁某个公共资源对象，同步化部分代码。注意哦：非syn的方法获部分代码仍然异步
public class _914syn_notthis_dirtyread {
	public static void main(String[] args) {
		_914Task task=new _914Task("A", "B");
		_914Thread01 thread01=new _914Thread01(task);
		_914Thread02 thread02=new _914Thread02(task);
		thread01.start();
		thread02.start();
	}
	
	
}

class _914Task {
	private String objectWillSyb01;
	private String objectWillSyb02;
	private int publicResource;

	public _914Task(String obj01, String obj02) {
		this.objectWillSyb01 = obj01;
		this.objectWillSyb02 = obj02;
	}

	Integer i=new Integer(this.publicResource);//务必保证这是公共资源，将公共资源锁住是解决多线程问题的根本
	public void doTask01() {
		try {
			synchronized (i) {
				System.out.println(Thread.currentThread().getName() + "task begin"+"publicResource:"+publicResource);
				Thread.sleep(2000);
				this.publicResource++;
				System.out.println(Thread.currentThread().getName() + "task end"+"publicResource:"+publicResource);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void doTask02() {
		try {
			synchronized (i){
				System.out.println(Thread.currentThread().getName() + "task begin"+"publicResource:"+publicResource);
				Thread.sleep(2000);
				this.publicResource++;
				System.out.println(Thread.currentThread().getName() + "task end"+"publicResource:"+publicResource);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class _914Thread01 extends Thread {
	private _914Task task;

	public _914Thread01(_914Task task) {
		super();
		this.task = task;
	}

	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 10000; i++) {
			this.task.doTask01();
		}
	}
}
class _914Thread02 extends Thread {
	private _914Task task;

	public _914Thread02(_914Task task) {
		super();
		this.task = task;
	}

	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 10000; i++) {
			this.task.doTask02();
		}
	}
}