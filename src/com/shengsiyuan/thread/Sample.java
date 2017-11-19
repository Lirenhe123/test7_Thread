package com.shengsiyuan.thread;
/*
public class Sample{
	private int number=0;
	
	public synchronized void increase(){
		while(number<=0){
			number++;
			System.out.println(number);
			notify();
		}
		try {
			wait();//这种情况是不行的,wait()必须放到while中,否则导致notify后还wait,导致死锁
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	public synchronized void decrease(){
		while(number>0){
			number--;
			System.out.println(number);
			notify();
		}
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
*/

public class Sample
{
	private int number;

	public synchronized void increase()
	{
		while (0 != number)//为什么要是使用while不用if，两个增加两个减少情况
		{
			try
			{
				wait();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		number++;

		System.out.println(number);

		notify();//wait和notify成对出现
	}

	public synchronized void decrease()
	{
		while (0 == number)//为什么要是使用while不用if，两个增加两个减少情况
		{
			try
			{
				wait();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		number--;

		System.out.println(number);

		notify();
	}
}
