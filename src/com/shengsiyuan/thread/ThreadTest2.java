package com.shengsiyuan.thread;

public class ThreadTest2{
	public static void main(String[] args) {
		Thread3 th3=new Thread3("th3");
		new Thread(th3).start();
		
		Thread th4=new Thread(new Runnable(){
			@Override
			public void run(){
				for(int i=1;i<=100;i++){
					System.out.println("Thread4:"+i);
				}
			}
		});
		th4.start();
	}
}
class Thread3 implements Runnable{
	String name;
	Thread3(String name){
		this.name=name;
	}
	@Override
	public void run() {
		for(int i=1;i<=100;i++){
			System.out.println("Thread3:"+i);
			
		}
		
	}
	
}
/*
public class ThreadTest2
{
	public static void main(String[] args)
	{
//		Thread t1 = new Thread(new Runnable()//生成的是实现了Runnable接口的匿名类
//		{
//			@Override
//			public void run()
//			{
//				for(int i = 0; i < 100; i++)
//				{
//					System.out.println("hello :" + i);
//				}
//			}
//		});
//		
//		t1.start();
		
		Thread t1 = new Thread(new MyThread());
		
		t1.start();
		
		Thread t2 = new Thread(new MyThread2());
		
		t2.start();
		
	}
}

class MyThread implements Runnable
{
	@Override
	public void run()
	{
		for(int i = 0; i < 100; i++)
		{
			System.out.println("hello :" + i);
		}
	}
}

class MyThread2 implements Runnable
{
	@Override
	public void run()
	{
		for(int i = 0; i < 100; i++)
		{
			System.out.println("welcome: " + i);
		}
		
	}
}

*/








