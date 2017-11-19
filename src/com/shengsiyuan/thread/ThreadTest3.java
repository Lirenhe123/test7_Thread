package com.shengsiyuan.thread;

import java.util.ArrayList;

public class ThreadTest3{
	public static void main(String[] args) {
		Resource resource=new Resource();
//		Runnable th1=new HelloThread(resource,"th1");
//
//		Runnable th2=new HelloThread(resource,"th2");
//
//		Runnable th3=new HelloThread(resource,"th3");
		
		for(int i=1;i<=50;i++){
//			new Thread(th1).start();
//			new Thread(th2).start();
//			new Thread(th3).start();
		}
		/*for(int i=1;i<=50;i++){	
			new Thread(th1).start();
	//		Thread th11=new Thread(th1);
			
			new Thread(th2).start();
	//		Thread th22=new Thread(th2);
			
			new Thread(th3).start();
	//		Thread th33=new Thread(th3);
		}*/
	}
}
class HelloThread implements Runnable{
	String name;
	Resources resources;
	HelloThread(Resources resources,String name){
		this.resources=resources;
		this.name=name;
	}
	@Override
	public void run() {
		this.resources.onlyOne(1,name);
	}
}

class Resources{
	ArrayList arrayList=new ArrayList();
	Resources(int i){
		for(int x=1;x<=i;x++){
			Resource resource=new Resource();
			arrayList.add(resource);
		}
	}
	public synchronized void onlyOne(int i,String name){
		while(true){
			((Resource) arrayList.get(i)).useResource(name);
			break;
		}
	}
}
class Resource{
	static int x=1;
	
	public void  useResource(String na){
		String name=na;
		int i=this.x;
		while(true){
			System.out.println("共享变量被"+name+"增加到:"+i);
			this.x++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	

}





/*
public class ThreadTest3
{
	public static void main(String[] args)
	{
		Runnable r = new HelloThread();
		
		Thread t1 = new Thread(r);
		
		//r = new HelloThread();
		
		Thread t2 = new Thread(r);
		
		t1.start();
		t2.start();
	}
}

class HelloThread implements Runnable
{
	int i=0;//所有线程共享同一个成员变量
	
	@Override
	public void run()
	{
		//int i = 0;//局部变量，每个线程单独一个
		
		while(true)
		{
			System.out.println("number: " + this.i++);
			
			try
			{
				Thread.sleep((long)(Math.random() * 1000));
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			if(50 == this.i)
			{
				break;
			}
		}
	}
}
*/