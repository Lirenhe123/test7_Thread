package priv.lirenhe.multithreadprograming;

//JVM里的string具有常量池缓存的功能，导致两个不同的string如"AA"和"BB"相等（都使用同一个缓存池），所以不要使用string做为锁对象
//一般使用Object类，这样生成的对象不同



//如果在syn方法中使用出现死循环，那个这个对象的所有方法都无法使用。但是使用syn(Object)由于使用不同的对象，
//所以死循环出现在一个代码块里而不会影响其他代码块，因为持有不同的对象锁
public class _916syn_其它注意 {

}
