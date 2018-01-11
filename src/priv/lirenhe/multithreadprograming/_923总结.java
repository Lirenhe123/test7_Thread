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
 * 7, 多线程编程时要注意加syn的应用场景：多个线程使用同一个变量（或者对象）
 * 8, 非syn方法异步，syn方法同步
 * 9, 脏读思维：setValue和getValue
 * 10, 重入锁：一个线程可调用它所持对象的任意方法，既包括syn也包括非syn方法，（因为他持有该对象的锁）。
 * 			而该对象中的syn方法可调用对象中的任意方法，非syn方法可以被其他线程调用，所以不能调用自身对象中的syn方法（因为不持有该对象锁）。
 * 	    syn方法可以调用该对象父类中的其他synchronized方法。
 * 11, 管倒流：实现多线程间之间的数据传输，一个线程将数据交给另一个数据时，记得使用管倒流，而不是变量传递
 * 12, 同步不可继承：（911）syn本身是锁对象，即便fater对象的方法有syn，也无法锁住son对象引用的fater对象，这就导致在
 * 			son对象的方法不能同步时，即便父对象的方法加了syn也不能同步
 * 13, （912）：明白对象监视器的概念：syn（this）意思是监视this对象，当其他线程调用this对象中的方法时就阻塞。当是其他object
 * 			时，也是同样的原理，监视这个object，。。。。
 * 14, 同步和效率的选择：取中间方案：锁非this对象，当一个对象有多个方法需要同步时（但是方法并不共享同一个数据），锁-非this-的方法间-共享的对象数据，
 *      而不是锁this对象，从而导致方法间本不共享某数据时却锁了this对象，这样效率很低。
 * 15, 该看914
 * 
 * 
 * 
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
