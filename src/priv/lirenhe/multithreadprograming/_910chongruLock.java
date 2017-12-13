package priv.lirenhe.multithreadprograming;

//一个线程调用一个对象，将锁这个对象，但是他仍然可以重新调用该对象的其他synchronized方法。:一个资源中有多个syn方法，
																//改资源内部的syn可相互调用，拥有改资源的线程可任意调用
//                                 调用该对象父类中的其他synchronized方法。

public class _910chongruLock {
	public static void main(String[] args) {
		Resource re01=new Resource();
		_910Thread02 thread01=new _910Thread02(re01);
		thread01.start();
	}
}

class _910Thread02 extends Thread{
	Resource re01=new Resource();
	public _910Thread02(Resource re01){
		this.re01=re01;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		this.re01.serviceMethod01();
		this.re01.serviceMethod02();
	}
}

class Resource{
	String resourceName="re01";
	
	synchronized public void serviceMethod01(){
		System.out.println(this.resourceName+"的serviceMethod01");
	}
	
	synchronized public void serviceMethod02(){
		System.out.println(this.resourceName+"的serviceMethod02");
		serviceMethod01();
	}
}