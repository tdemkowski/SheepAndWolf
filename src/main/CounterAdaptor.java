package main;

public class CounterAdaptor extends lib.Counter {
	
	public synchronized static void set(int n) {
		lib.Counter.set(n);
	}
	
	public synchronized static void decrement() {
		lib.Counter.decrement();
	}
	
	public synchronized static boolean depleted() {
		return lib.Counter.depleted();
	}

}
