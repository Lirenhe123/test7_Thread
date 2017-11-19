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
		System.out.println("存额money:"+money);
		money-=numble;
		System.out.println("取出numble:"+numble);
		System.out.println("现余额money:"+money);
		
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
		
		Thread t1 = new MoneyThread(bank); // 柜台
		
//		bank = new Bank();//两个账户不存在线程争夺资源问题。
		
		Thread t2 = new MoneyThread(bank); // 取款机
		
		t1.start();
		t2.start();
	}
}

class Bank
{
	private  int money = 1000;//static导致变量是静态的，所有对象共享该资源，导致非串行化问题
	
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
				Thread.sleep(1000);//在这里暂停1秒导致整个线程非串行化的执行更明显
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			money -= number;//第一个执行money：200，第二个执行money：-600
			//非串行化：在此时会暂停，那么两个线程执行打印，将导致两个-600
			System.out.println("left money: " + money); 
			//每个线程执行到这里结束，那么都将打印200
			
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
		System.out.println("真实取出"+bank.getMoney(800));
	}
}
*/





















