package com.shengsiyuan.thread;

public class ThreadTest{
	public static void main(String[] args) {
		Thread1 th1=new Thread1("thread1");
		th1.start();
		Thread2 th2=new Thread2("thread2");
		th2.start();
	}
}
class Thread1 extends Thread{
	public Thread1(String name){
		super(name);
	}
	
	public void run(){
		for(int i=1;i<=100;i++){
			System.out.println("Thread1:"+i);
		}
	}
}
class Thread2 extends Thread{
	public Thread2(String name){
		super(name);
	}
	public void run(){
		for(int i=1;i<=100;i++){
			System.out.println("Thread2:"+i);
		}
	}
}
/*
public class ThreadTest
{
	public static void main(String[] args)
	{
		Thread1 t1 = new Thread1();//"first thread"
		Thread2 t2 = new Thread2("second thread");
		
		System.out.println(t1.getName());
		System.out.println(t2.getName());
		
		t1.start();
		t2.start();
	}
}

class Thread1 extends Thread
{
	public Thread1(String name)
	{
		super(name);
	}
	
	@Override
	public void run()
	{
		for(int i = 0; i < 100; i++)
		{
			System.out.println("hello world: " + i);
		}
	}
}

class Thread2 extends Thread
{
	public Thread2(String name)
	{
		super(name);
	}
	
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















