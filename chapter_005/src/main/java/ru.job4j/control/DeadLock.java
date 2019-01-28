package ru.job4j.control;

import java.util.concurrent.CountDownLatch;

/**
 * This class is simple demonstration deadlock.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class DeadLock {
	/**
	 * Latch.
	 */
	private final CountDownLatch latch;
	
	public DeadLock(CountDownLatch latch) {
		this.latch = latch;
	}

	/**
	 * At the beginning, wait for the second thread to receive the monitor.
	 * Then we try to take his monitor.
	 * @param deadLock second Object.
	 */
	public synchronized void methodOne(DeadLock deadLock) {
		try {
			System.out.printf("Before await - %s\n", Thread.currentThread().getName());
			latch.countDown();
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("DeadLock start - %s\n", Thread.currentThread().getName());
		deadLock.methodTwo();
	}

	/**
	 * Prints "There is no deadlock" if the deadlock did not work.
	 */
	public synchronized void methodTwo() {
		System.out.printf("There is no deadlock - %s\n", Thread.currentThread().getName());
	}
}

class DeadLockRun implements Runnable {
	
	private final DeadLock deadLockOne;
	private final DeadLock deadLockTwo;
	
	public DeadLockRun(DeadLock deadLockOne, DeadLock deadLockTwo) {
		this.deadLockOne = deadLockOne;
		this.deadLockTwo = deadLockTwo;
	}
	
	@Override
	public void run() {
		deadLockOne.methodOne(deadLockTwo);
	}
}

class DeadLockStart {
	
	private final CountDownLatch latch = new CountDownLatch(2);
	
	private final DeadLock deadLockOne = new DeadLock(latch);
	private final DeadLock deadLockTwo = new DeadLock(latch);
	
	public void start() {
		Thread t1 = new Thread(new DeadLockRun(deadLockOne, deadLockTwo));
		Thread t2 = new Thread(new DeadLockRun(deadLockTwo, deadLockOne));
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Start {
	public static void main(String[] args) {
		new DeadLockStart().start();
	}
}
