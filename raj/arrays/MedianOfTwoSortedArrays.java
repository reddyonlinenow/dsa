/**
 * 
 */
package com.raj.arrays;

/**
 * @author Raj
 *
 */
public class MedianOfTwoSortedArrays {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MedianOfTwoSortedArrays obj = new MedianOfTwoSortedArrays();
		int a1[] = { 1, 12, 15, 26, 38 };
		int a2[] = { 2, 13, 17, 30, 45 };
		int result = -1;
		result = obj.medianOfTwoSortedArrays(a1, a2, 0, a1.length, 0, a2.length);
		System.out.println(result);
		result = obj.median(a1, 0, a2.length - 1);
		System.out.println(result);
	}

	public int medianOfTwoSortedArrays(int[] a1, int[] a2, int l1, int r1, int l2, int r2) {
		int m1, m2, n;
		n = r1 - l1 + 1;
		if (n <= 0) {
			return -1;
		} else if (n == 1) {
			return a1[n - 1];
		} else if (n == 2) {
			int l = Math.max(a1[l1], a2[l2]);
			int m = Math.min(a1[r1], a2[r2]);
			return (l + m) / 2;
		} else {
			m1 = median(a1, l1, r1);
			m2 = median(a2, l2, r2);
			
			//TODO: complete code from here
			
			if(m1 == m2){
				return m1;
			}
			if (m1 < m2) {
				return medianOfTwoSortedArrays(a1, a2, l1, r1, l2, r2);
			}
		}

		return 0;
	}

	public int median(int[] a, int start, int end) {
		int n = end - start + 1;
		int k = n / 2;

		if (n % 2 != 0) {
			return a[k];
		} else {
			return (a[start + k] + a[start + k - 1]) / 2;
		}

	}

}
