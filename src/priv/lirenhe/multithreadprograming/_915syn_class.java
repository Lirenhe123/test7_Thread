package priv.lirenhe.multithreadprograming;

//给static前加syn得到一个class锁，所有的对象（class的对象）都同步
//演示：一个task类生成多个task对象，多个线程使用不同的task，这样当一个线程获得一个对象当 class锁，该类的所有对象都会被锁住，其他线程不能执行给类各不同的其他对象
public class _915syn_class {

}  
