package com.shengsiyuan.thread;

import org.omg.Messaging.SyncScopeHelper;

public class FetchMoney{
	public static void main(String[] args) {
		Bank bank=new Bank(200);
		
		Thread th1=new Thread(new MoneyThread(bank));
		Thread th2=new Thread(new MoneyThread(bank));
		
		th1.start();
		th2.start();
	}
	
	
}
class Bank{
	private int numble;
	private int money=1000;
	Bank(int num){
		this.numble=num;
	}
	public synchronized int getMoney(){
		if(numble>money){
			System.out.println("numble<money");
			return -1;
		}
		if(numble<0){
			System.out.println("numble<0");
			return -2;
		}
		if(money<0){
			System.out.println("money<0");
			return -3;
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("���money:"+money);
		money-=numble;
		System.out.println("ȡ��numble:"+numble);
		System.out.println("�����money:"+money);
		
		return 1;
	}
}
class MoneyThread implements Runnable{
	Bank bank;
	MoneyThread(Bank bank){
		this.bank=bank;
	}
	@Override
	public void run() {
		bank.getMoney();
	}
	
}
/*
public class FetchMoney
{
	public static void main(String[] args)
	{
		Bank bank = new Bank();
		
		Thread t1 = new MoneyThread(bank); // ��̨
		
//		bank = new Bank();//�����˻��������߳�������Դ���⡣
		
		Thread t2 = new MoneyThread(bank); // ȡ���
		
		t1.start();
		t2.start();
	}
}

class Bank
{
	private  int money = 1000;//static���±����Ǿ�̬�ģ����ж��������Դ�����·Ǵ��л�����
	
	public synchronized int getMoney(int number)//synchronized
	{
		if(number < 0)
		{
			return -1;
		}
		else if(number > money)
		{
			return -2;
		}
		else if(money < 0)
		{
			return -3;
		}
		else
		{
			try
			{
				Thread.sleep(1000);//��������ͣ1�뵼�������̷߳Ǵ��л���ִ�и�����
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			money -= number;//��һ��ִ��money��200���ڶ���ִ��money��-600
			//�Ǵ��л����ڴ�ʱ����ͣ����ô�����߳�ִ�д�ӡ������������-600
			System.out.println("left money: " + money); 
			//ÿ���߳�ִ�е������������ô������ӡ200
			
			return number;
		}		
	}
}

class MoneyThread extends Thread
{
	private Bank bank;
	
	public MoneyThread(Bank bank)
	{
		this.bank = bank;
	}
	
	@Override
	public void run()
	{
		System.out.println("��ʵȡ��"+bank.getMoney(800));
	}
}
*/





















