package priv.lirenhe.multithreadprograming;

/**
 * 多个线程都使用同一个变量和方法（static变量和方法：没有独自的被封装成对象供各自使用，使用时有没有上锁），在执行时没有对该变量和方法上锁就会导致线程不安全
 * @author User
 *
 */
public class _03threadSafe {
	
	public static void main(String[] args) {
		Alogin a=new Alogin("a","aa");
		Blogin b=new Blogin("b","bb");
		
		a.start();
		b.start();
	}

}

class Alogin extends Thread{
	private String username;
	private String password;
	public Alogin(String username,String password){
		this.username=username;
		this.password=password;
	}
	@Override
	public void run() {
		super.run();
		LoginServlet.doPost(this.username, this.password);
	}
}
class Blogin extends Thread{
	private String username;
	private String password;
	public Blogin(String username,String password){
		this.username=username;
		this.password=password;
	}
	@Override
	public void run() {
		super.run();
		LoginServlet.doPost(this.username, this.password);
	}
}



class LoginServlet{
	private static String usernameRef;
	private static String passwordRef;
	
	public static void doPost(String username,String password){
		try{
			usernameRef=username;
			if(username.equals("a")){
				Thread.sleep(5000);
			}
			passwordRef=password;
			System.out.println("username="+usernameRef+":"+"password="+passwordRef);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}