package priv.lirenhe.multithreadprograming;



//join():使线程排队运行，有点同步的效果。thread.join()使得thread正常运行，对其后的代码进行无限期的阻塞，等到thread销毁之后再继续执行后面的代码
//join的同步效果内部用wait方法（有释放当前锁的功能，释放的是当前线程的锁，切记此时的当前线程不是调用join线程），synchronized的同步使用对象监视器
//虽然是thread调用join（）方法，但是join不会阻塞thread，join在thread调用start调用之后调用，这意味则执行thread.join时的当前线程是主线程
public class _921join_thread {
	
}


