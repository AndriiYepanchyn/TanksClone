package main;

public class Time {
	public static final long SECOND = 1_000_000_000l;
	
	public static long get() {
		return System.nanoTime();	
	}
}
