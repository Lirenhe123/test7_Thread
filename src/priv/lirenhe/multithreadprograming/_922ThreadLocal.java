package priv.lirenhe.multithreadprograming;

import java.util.Date;

//ThreadLocal相当于所有线程的共有盒子，每个线程的数据放到是有数据放到这个盒子中
//那么在这个盒子中的每个线程的是有数据是否具有隔离性了？具有隔离性，他会通过当前线程判断来取值
//注意public static threadLocal
public class _922ThreadLocal {
	public static void main(String[] args) {
		_922Thread01 thread01 = new _922Thread01();
		_922Thread02 thread02 = new _922Thread02();
		thread01.setName("thread01");
		thread02.setName("thread02");
		thread01.start();
		thread02.start();
	}
}

class _922Thread01 extends Thread {
	@Override
	public void run() {
		super.run();
		if (TThreadLocal.threadLocal.get() == null) {
			for (int i = 1; i <= 20; i++) {
				TThreadLocal.threadLocal.set(Thread.currentThread().getName() + "set 后 get i=" + i);
				System.out.println(TThreadLocal.threadLocal.get());
			}
		}
	}
}

class _922Thread02 extends Thread {
	@Override
	public void run() {
		super.run();
		if (TThreadLocal.threadLocal.get() == null) {
			for (int a = 40; a <= 60; a++) {
				TThreadLocal.threadLocal.set(Thread.currentThread().getName() + "set 后 get a=" + a);
				System.out.println(TThreadLocal.threadLocal.get());
			}
		}
	}
}

class TThreadLocal {
	public static ThreadLocal threadLocal = new ThreadLocal();
}

/*
class TThreadLocal {
	public static ThreadLocal<Date> threadLocal = new ThreadLocal<Date>();
}
*/
/*
class TThreadLocal extends ThreadLocal{
	@Override
	protected Object initialValue() {
		return "初始值";
	}
	public static ThreadLocal threadLocal = new ThreadLocal();
}
*/