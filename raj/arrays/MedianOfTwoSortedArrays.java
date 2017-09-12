/**
 * 
 */
package com.raj.arrays;

/**
 * @author Raj
 *
 */
public class MedianOfTwoSortedArrays {

	public double findMedianSortedArrays(int a[], int b[]) {
		int m = a.length, n = b.length;
		/* A[0, 1, 2, ..., n-1, n] */
		/* A[0, 1, 2, ..., m-1, m] */

		// not using k as index because, finding kth element with 0th index is
		// not clean
		int k = (m + n - 1) / 2;
		double v = (double) findKth(a, 0, m - 1, b, 0, n - 1, k);
		System.out.println("k=" + k + ",v=" + v);
		if ((m + n) % 2 == 0) {
			int k2 = k + 1;
			double v2 = (double) findKth(a, 0, m - 1, b, 0, n - 1, k2);
			System.out.println("k2=" + k2 + ",v2=" + v2);
			v = (v + v2) / 2;
		}

		return v;
	}

	// find the kth element int the two sorted arrays
	// let us say: A[aMid] <= B[bMid], x: mid len of a, y: mid len of b, then
	// wen can know
	//
	// (1) there will be at least (x + 1 + y) elements before bMid
	// (2) there will be at least (m - x - 1 + n - y) = m + n - (x + y +1)
	// elements after aMid
	// therefore
	// if k <= x + y + 1, find the kth element in a and b, but unconsidering
	// bMid and its suffix
	// if k > x + y + 1, find the k - (x + 1) th element in a and b, but
	// unconsidering aMid and its prefix
	// https://discuss.leetcode.com/topic/5728/share-one-divide-and-conquer-o-log-m-n-method-with-clear-description/12
	public int findKth(int A[], int aL, int aR, int B[], int bL, int bR, int k) {
		if (aL > aR)
			return B[bL + k];
		if (bL > bR)
			return A[aL + k];

		int aMid = (aL + aR) / 2;
		int bMid = (bL + bR) / 2;

		if (k <= (aMid - aL) + (bMid - bL)) {
			if (A[aMid] <= B[bMid]) {
				return findKth(A, aL, aR, B, bL, bMid - 1, k);
			} else {
				return findKth(A, aL, aMid - 1, B, bL, bR, k);
			}
		} else {
			if (A[aMid] <= B[bMid]) {
				return findKth(A, aMid + 1, aR, B, bL, bR, k - (aMid - aL) - 1);
			} else {
				return findKth(A, aL, aR, B, bMid + 1, bR, k - (bMid - bL) - 1);
			}
		}
	}

	public static void main(String[] args) {
		MedianOfTwoSortedArrays obj = new MedianOfTwoSortedArrays();
		int a[] = { 1, 12, 15, 26 };
		int b[] = { 2, 13, 17, 30 };
		int result = -1;
		result = obj.medianOfTwoSortedArrays(a, b, 0, a.length - 1, 0, b.length - 1);
		System.out.println(result);
		double res = obj.findMedianSortedArrays(a, b);
		System.out.println(res);

		res = obj.findMedianSortedArrays(a, b);
		System.out.println(res);

	}

	public int medianOfTwoSortedArrays(int[] a1, int[] a2, int l1, int r1, int l2, int r2) {
		int m1, m2, n;
		n = r1 - l1 + 1;
		if (n <= 0) {
			return -1;
		} else if (n == 1) {
			return (a1[n - 1] + a2[n - 1]) / 2;
		} else if (n == 2) {
			int l = Math.max(a1[l1], a2[l2]);
			int m = Math.min(a1[r1], a2[r2]);
			return (l + m) / 2;
		} else {
			m1 = median(a1, l1, r1);
			m2 = median(a2, l2, r2);

			if (m1 < m2) {
				int k = n / 2;
				return medianOfTwoSortedArrays(a1, a2, l1 + k, r1, l2, r2 - k);
			} else if (m1 > m2) {
				int k = n / 2;
				return medianOfTwoSortedArrays(a1, a2, l1, r1 - k, l2 + k, r2);

			} else {
				return m1;
			}
		}
	}

	public int median(int[] a, int start, int end) {

		int n = end - start + 1;
		int k = start + n / 2;

		if (n % 2 != 0) {
			return a[k];
		} else {
			return (a[k] + a[k - 1]) / 2;
		}

	}

}
