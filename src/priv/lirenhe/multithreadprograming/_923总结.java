package priv.lirenhe.multithreadprograming;

/**
 * 1, 凡是“线程以内”[线程的变量+run内对象方法的变量]的所有方法，对象务必保证变量的共享问题（run中的对象必须考虑变量共享）
 * 2, 是将线程类生成多个不同的线程对象（线程安全），还是将一个对象生成多个线程（线程不安全）
 * 3, 必须使用synchronized保证同步，但是尽量使用syn(this)或者syn(object)
 * 4, _04currentThread.java执行的打印
 * 5, 线程运行起来后必须停止，使用for循环保证线程一直执行，同时判断interrupted标志，他的父线程调用interrupt方法，当达到一定
 * 		条件后就终止子线程的执行，但仍然执行for后面的语句，说明它只是停止了for的执行，如果不想执行for后的语句，就直接抛出异常。
 * 		这就是异常法，另一种合理的方法是return，但建议使用异常法
 * 6, 严格区分interrupted和isInterrupted的区别：interrupted会清除中断标志标志，isInterrupted不会
 * 7, 
 */
/*
 _04currentThread.java
main线程生成子线程时，main在执行run，整个过程时main线程run，current线程必是活的
main线程就是当前线程main：isAlive:true
但是this线程指的是调用isAlive方法所属对象线程Thread-0：isAlive:false可能是活的，也可能是死的
threadTest05_1 isAlive:false
threadTest05_1 isAlive:true
此时，正在执行子线程，那么当前线程就是该子线程，因为它在run，在run必为活的
所以，当前线程是Thread-0：isAlive:true
而this线程是调用isAlive方法所属对象线程Thread-0：isAlive:true
*/









public class _923总结 {
	
}
