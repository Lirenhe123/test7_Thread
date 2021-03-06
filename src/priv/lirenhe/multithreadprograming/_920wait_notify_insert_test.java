package priv.lirenhe.multithreadprograming;



//交叉执行的核心思想：对this对象锁的获取，该参数prevIsA决定了对this锁的获取的优先权
//在重复思考该代码时想起将大量对象数据存入数据库时可能存在效率低问题：将要存入数据库的大量数据分割成多个对象，生成n个的线程，将这些
//数据对象分给各个线程，可以将它们用map映射起来，然后处理：线程对各自的数据对象枷锁。
public class _920wait_notify_insert_test {
	public static void main(String[] args) {
		DBTools dbTool = new DBTools();
		
		for(int i=0;i<20;i++){
			_920ThreadA threadBackupA=new _920ThreadA(dbTool);
			_920ThreadB threadBackupB=new _920ThreadB(dbTool);
			
			threadBackupA.start();
			threadBackupB.start();
		}
	}
}


class _920ThreadA extends Thread{
	DBTools dbTool=null;
	public _920ThreadA(DBTools dbTool){
		this.dbTool = dbTool;
	}
	
	@Override
	public void run() {
		super.run();
		dbTool.backupA();
	}
}

class _920ThreadB extends Thread{
	DBTools dbTool=null;
	public _920ThreadB(DBTools dbTool){
		this.dbTool = dbTool;
	}
	
	@Override
	public void run() {
		super.run();
		dbTool.backupB();
	}
}





class DBTools{
	private boolean prevIsA=false;
	public void backupA(){
		try{
			synchronized (this) {
				while(prevIsA == false){
					wait();
				}
				for(int a=0;a<5;a++){
					System.out.println("aaaaa");
				}
				prevIsA = false;
				notifyAll();
			}
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void backupB(){
		try{
			synchronized (this) {
				while(prevIsA == true){
					wait();
				}
				for(int b=0;b<5;b++){
					System.out.println("bbbbb");
				}
				prevIsA  = true;
				notifyAll();
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
}