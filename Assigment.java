// Name:Maha Maher Mali
// ID:1200746

import java.util.Arrays;
import java.util.Scanner;

public class Assigment {

	public static void main(final String[] args) throws InterruptedException {
		try (final Scanner s = new Scanner(System.in)) {
			System.out.println("Enter the input array as comma separated.");
			final String[] inputArrayAsString = s.nextLine().split(",");
			if (inputArrayAsString.length == 0) {
				System.out.println("Input array is incorrect!! Try re-running the program");
				System.exit(1);
			}
			final int[] arr = new int[inputArrayAsString.length];
			for (int i = 0; i < inputArrayAsString.length; i++) {
				final String numStr = inputArrayAsString[i];
				if (numStr != null) {
					try {
						arr[i] = Integer.parseInt(numStr.trim());
					} catch (final NumberFormatException nfe) {

					}
				}
			}
			// create and start the threads
			multiThreadingTest(arr);
		}
	}


	private static void multiThreadingTest(final int[] arr) throws InterruptedException {
		if (arr == null || arr.length == 0) return;
		
		
		 // create Runnable objects
		final SortEvens sortEvens = new SortEvens(arr);
		final SortOdds sortOdds = new SortOdds(arr);
		
		
		// create Thread objects from above Runnable objects
		final Thread thread1 = new Thread(sortEvens);
		final Thread thread2 = new Thread(sortOdds);

		// run the threads
		thread1.start();
		thread2.start();
		
		// join the threads until it completes
		thread1.join();
		thread2.join();

		 // get the sorted arrays
		final int[] sortedEvenArray = sortEvens.getSortedArray();
		final int[] sortedOddArray = sortOdds.getSortedArray();

		  // merge the arrays
		final MergeArrays mergeArrays = new MergeArrays(sortedEvenArray, sortedOddArray);
		final Thread thread3 = new Thread(mergeArrays);
		thread3.start();
		thread3.join();

		 // get the combined array
		final int[] combinedArray = mergeArrays.getCombinedArray();
		
		
		// print all the 3 arrays
		System.out.print("Sorted even numbers: "); 
		print(sortedEvenArray);
		System.out.print("Sorted odd numbers: "); 
		print(sortedOddArray);
		System.out.print("Sorted array: "); 
		print(combinedArray);
	}


	private static class SortEvens implements Runnable {
		private final int[] arr;// this is  array variable 
		private int[] sortedArray;// this varible to sort the  array of even number

		public SortEvens(final int[] arr) {
			this.arr = arr;
		}

		@Override
		public void run() {// used to start a new thread,the run method is invoked in the thread which executes separately
			sortedArray = Arrays.stream(arr)// stream:Returns a sequential IntStream with the specified array as its source.
					.filter(i -> i % 2 == 0)// this equation  to find even number
					//Filter: Returns a stream consisting of the elements of this stream that matchthe given predicate. 
					//// filter only even numbers
					.sorted()//Returns a stream consisting of the elements of this stream in sortedorder. 
								//sort in ascending order
					.toArray();//Returns an array containing the elements of this stream
								//convert IntStream to int[]
		}

		public int[] getSortedArray() {// this is getter to return the sorted array of even number
			return sortedArray;
		}
	}

	private static class SortOdds implements Runnable {
		private final int[] arr;
		private int[] sortedArray;

		public SortOdds(final int[] arr) {
			this.arr = arr;
		}

		@Override
		public void run() {
			sortedArray = Arrays.stream(arr)
					.filter(i -> i % 2 != 0)
					.sorted()
					.toArray();
		}

		public int[] getSortedArray() {// this is getter to return the sorted array of odd number
			return sortedArray;
		}
	}

	private static class MergeArrays implements Runnable {
		private final int[] evenArr;
		private final int[] oddArr;
		private int[] combinedArray;

		public MergeArrays(final int[] evenArr, final int[] oddArr) {
			this.evenArr = evenArr;
			this.oddArr = oddArr;
		}

		@Override
		public void run() {
			combinedArray = new int[evenArr.length + oddArr.length];
			int i = 0;
			for (final int sortedEven : evenArr) {
				combinedArray[i++] = sortedEven;
			}
			for (final int sortedOdd : oddArr) {
				combinedArray[i++] = sortedOdd;
			}
		}

		public int[] getCombinedArray() {
			return combinedArray;
		}
	}
	private static void print(final int[] array) {
		for (final int num : array) {
			System.out.printf("%d, ", num);
		}
		System.out.println();
	}
}
