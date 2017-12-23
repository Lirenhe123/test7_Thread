package priv.lirenhe.multithreadprograming;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

//管道流的核心：1，connect，2，对管道流pipeStream进行线程化
//当写线程全部写完才会让读线程取数据（是错的）:内部大概是：写到写出流中，如果写出流中的缓冲区满了就输出到读入流中，读入流才择机抢占cpu执行
public class _919pipe_thread {
	public static void main(String[] args) {
		try {
			ReadData readData = new ReadData();
			WriteData writeData = new WriteData();

			PipedInputStream pis = new PipedInputStream();
			PipedOutputStream pos = new PipedOutputStream();
			pis.connect(pos);
			_919ThreadRead threadRead = new _919ThreadRead(readData, pis);
			_919ThreadWrite threadWrite = new _919ThreadWrite(writeData, pos);
			threadRead.setName("Read");
			threadWrite.setName("Write");

			threadRead.start();
			threadWrite.start();

			boolean flag = true;
			//通过分析打印语句，只有当写出一定数据量后才能读入
			while (flag) {
				while (!threadRead.isAlive() && !threadWrite.isAlive()) {
					flag = false;
				}
				Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
				Thread.currentThread().getThreadGroup().enumerate(threadArray);
				for (int i = 0; i < threadArray.length; i++) {
					System.out.println(threadArray[i].getName() + "-" + threadArray[i].getState());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

class _919ThreadRead extends Thread {
	ReadData readData = null;
	PipedInputStream pis = null;

	public _919ThreadRead(ReadData readData, PipedInputStream pis) {
		this.readData = readData;
		this.pis = pis;
	}

	@Override
	public void run() {
		super.run();
		readData.readData(pis);
	}
}

class _919ThreadWrite extends Thread {
	WriteData writeData = null;
	PipedOutputStream pos = null;

	public _919ThreadWrite(WriteData writeData, PipedOutputStream pos) {
		this.writeData = writeData;
		this.pos = pos;
	}

	@Override
	public void run() {
		super.run();
		writeData.writeData(pos);
	}
}

class ReadData {
	public void readData(PipedInputStream pis) {
		byte[] buffer = new byte[300];
		BufferedInputStream bis = null;
		String result = null;

		try {
			System.out.println("readData:begin");
			bis = new BufferedInputStream(pis);
			int length = 0;
			while ((length = bis.read(buffer)) != -1) {
				result = new String(buffer, "utf-8");
				System.out.println("读入流数据为：" + result);
			}
			System.out.println("readData:end");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pis != null) {
				try {
					System.out.println("读入流数据关闭");
					pis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class WriteData {
	public void writeData(PipedOutputStream pos) {
		String outPutData = null;
		System.out.println("writeData:start");
		try {
			for (int i = 0; i < 300; i++) {
				outPutData = "_" + (i + 1);
				System.out.println("第" + i + "次写出数据：" + outPutData);
				pos.write(outPutData.getBytes("utf-8"));
			}
			System.out.println("writeData:end");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pos != null) {
				try {
					System.out.println("写出流数据关闭");
					pos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}