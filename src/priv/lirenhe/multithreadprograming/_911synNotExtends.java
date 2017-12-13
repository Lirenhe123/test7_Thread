package priv.lirenhe.multithreadprograming;

//同步不可继承

public class _911synNotExtends {
	public static void main(String[] args) {
		Son son=new Son();
		Thread01 thread01=new Thread01(son);
		thread01.setName("A");
		thread01.start();
		
		
		Thread02 thread02=new Thread02(son);
		thread02.setName("B");
		thread02.start();
	}
}




class Thread01 extends Thread{
	Son son;
	public Thread01(Son son){
		this.son=son;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		son.serviceMethodOfSon();
	}
}

class Thread02 extends Thread{
	Son son;
	public Thread02(Son son){
		this.son=son;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		son.serviceMethodOfSon();
	}
}





class Son extends Parent{
	 void serviceMethodOfSon(){//加上synchronized试试
		try{
			System.out.println(Thread.currentThread().getName()+"线程在"+"第一次运行 Son");
			super.serviceMethodOfParent();//B线程将在此等待A线程运行完Parent后才能获得Parent的锁
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName()+"线程在执行玩super后"+"第二次运行 Son");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
}



class Parent {
	synchronized void serviceMethodOfParent() {
		try {
			System.out.println(Thread.currentThread().getName()+"线程在"+"第一次运行 Parent");
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName()+"线程在"+"睡眠1秒后第二次运行 Parent");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
