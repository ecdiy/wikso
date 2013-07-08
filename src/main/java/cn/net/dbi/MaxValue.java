package cn.net.dbi;

import java.util.LinkedList;

public class MaxValue {
	public MaxValue(int[][] inaa) {
		this.aa = inaa;
		go(aa.length - 1, 2);
		System.out.println("最大路径和：" + value);
	}

	int[][] aa;
	int value = 0;

	private void go(int i, int j) {
		int[][] temp = { { 0, 0 }, { 0, 0 }, { 0, 0 } };
		if (j - 1 > -1) {
			temp[0][0] = aa[i][j - 1];
			temp[0][1] = j - 1;
		}
		temp[1][0] = aa[i][j];
		temp[1][1] = j;
		if (j + 1 < 5) {
			temp[2][0] = aa[i][j + 1];
			temp[2][1] = j + 1;
		}
		int temValue = -1;
		int temIndex = -1;
		for (int k = 0; k < 3; k++) {
			if (temp[k][0] > temValue) {
				temValue = temp[k][0];
				temIndex = temp[k][1];
			}
		}
		value += temValue;
		result.addLast(i);
		if (i - 1 > -1) {
			go(i - 1, temIndex);
		}
	}

	public LinkedList<Integer> result = new LinkedList<>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[][] aa = { { 6, 4, 3, 2, 7 }, { 4, 5, 7, 5, 5 }, { 2, 6, 4, 8, 2 },
				{ 3, 8, 1, 2, 4 }, { 7, 2, 5, 9, 4 } };
		MaxValue mv = new MaxValue(aa);

		System.out.println(mv.result.toString());
	}

}
