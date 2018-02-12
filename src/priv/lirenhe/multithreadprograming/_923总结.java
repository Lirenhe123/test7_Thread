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
 * 15, (914)上面那种方法导致this对象的成员变量暴露出来，有可能导致脏读
 * 16, 不能将同步代码（在线程之类run中使用for循环）置于循环之中来实现对某个对象中某个数据的循环操作，这样将导致锁了又打开，其它线程将获取锁操作该数据，最终导致脏读
 *      从根本上解决同步问题：锁对象的某个变量，使用syn块（参照914）
 * 17, static+syn=类锁
 * 18, 所以不要使用string做为锁对象，一般使用Object类，这样生成的对象不同
 * 19, 书本p163：线程组的初步了解，线程状态的获取和enumerate的使用
 * 20, 关键是syn+while==》wait+notify。搞清楚什么条件producer wait，什么条件cosumer wait
 * 21, illegalMonitor异常是因为没有用锁对象去调用wait和notify
 * 22, 为了防止假死情况，在wait-notify使用时，应该尽量使用notifyAll。（原因：同一个生产者和消费者多线程化时，notify极有可能唤醒的
 * 			是生产者（消费者）的另一个生产者（消费者），这就导致唤醒了同类）
 * 					多个生产者和一个生产者多线程的区别。（多个生产者锁可以是一样的，成员变量绝不一样）
 * 								多线程化：同一个对象多个线程（共用这个对象），成员变量对这些线程公开的。
 * 23, 管道流的核心：1，connect，2，对管道流pipeStream进行线程化：在线程处理管倒流之前将两流connect
 * 				将pis和pos作为参数传给读写类，读写类的对象线程化
 * 24, //交叉执行的核心思想：对this对象锁的获取，该参数prevIsA决定了对this锁的获取的优先权
		//在重复思考该代码时想起将大量对象数据存入数据库时可能存在效率低问题：将要存入数据库的大量数据分割成多个对象，生成n个的线程，将这些
		//数据对象分给各个线程，可以将它们用map映射起来，然后处理：线程对各自的数据对象枷锁。(代码_920)
 * 25, 对join的理解：假如主线程运行，在运行过程中启动了另一个子线程，而该子线程接着调用了join方法，导致
 *       		，同时调用this线程wait，（相当于）子线程将join后面的代码“加入join”到主线程此刻代码后面，这也就是为什么用
 *       				join一词的原因。http://blog.csdn.net/frankarmstrong/article/details/55504161
 * 
 * 26, ThreadLocal的作用是提供线程内的局部变量，这种变量在线程的生命周期内起作用，减少同一个线程内多个函数或者组件之间一些公共变量的传递的复杂度。
 * 				每个ThreadLocal类创建一个Map，然后用线程的ID作为Map的key，实例对象作为Map的value，这样就能达到各个线程的值隔离的效果。
 * 		虽然ThreadLocal只是一个变量，但是它代表了一个map集合，调用get和set方法时是向集合存取。
 * 		在使用时注意：public static       initialvalue    ThreadLocal像是个多线程数据的公共集合
 * 		每个线程只管将该线程的数据存取导ThreadLocal就行，其他不必考虑。
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
