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
			wait();//��������ǲ��е�,wait()����ŵ�while��,������notify��wait,��������
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
		while (0 != number)//ΪʲôҪ��ʹ��while����if���������������������
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

		notify();//wait��notify�ɶԳ���
	}

	public synchronized void decrease()
	{
		while (0 == number)//ΪʲôҪ��ʹ��while����if���������������������
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
