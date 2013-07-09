package cn.net.dbi;

import java.util.HashSet;
import java.util.LinkedList;

public class MatrixLongest {
	public class T {
		public Integer factoryId;
		public HashSet<Integer> son = new HashSet<>();
	}

	T[] factors;
	int[][] matrix;
	public P pmax;

	public class P {
		Integer from, to;

		public LinkedList<Integer> max = new LinkedList<>();

		P(int from, int to) {
			this.from = from;
			this.to = to;
			// 遍历所有路径并存储
			System.out.print(from + "==>" + to + ";");
			LinkedList<Integer> list = new LinkedList<Integer>();
			list.addFirst(from);
			find(factors[from], list);
			System.out.print(max.size() + ";" + max.toString() + "\n");
		}

		void find(T from, LinkedList<Integer> eles) {
			if (eles.size() > matrix.length + 1)
				return;
			for (Integer f : from.son) {
				boolean find = false;
				for (Integer e : eles) {
					if (e == f) {
						find = true;
						break;
					}
				}
				if (find)
					continue;
				LinkedList<Integer> newlist = new LinkedList<>();
				newlist.addAll(eles);
				newlist.add(f);
				// System.out.println(eles.size() + ":" + eles.toString());
				if (f == to) {
					if (eles.size() > max.size()) {
						max = newlist;
					}
				} else {
					find(factors[f], newlist);
				}
			}
		}
	}

	public MatrixLongest(int[][] matrix) {
		this.matrix = matrix;
		factors = new T[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			T t = new T();
			t.factoryId = i;
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[i][j] != INF)
					t.son.add(j);
			}
			factors[i] = t;
			System.out.println(i + ";sons=" + t.son.toString());
		}

		for (int i = 0; i < matrix.length; i++) {
			for (int j = i + 1; j < matrix.length; j++) {
				P p = new P(i, j);
				if (pmax == null) {
					pmax = p;
				} else {
					if (p.max.size() > pmax.max.size()) {
						pmax = p;
					}
				}
			}
		}
	}

	private static int INF = 0;

	public static void main(String[] args) {
		int[][] matrix = { { INF, 30, INF, 10, 50 }, { 30, INF, 60, INF, 80 },
				{ INF, 60, INF, INF, 40 }, { 10, INF, INF, INF, 30 },
				{ 50, 80, 40, 30, INF } };
		MatrixLongest ml = new MatrixLongest(matrix);
		System.out.println(ml.pmax.max.size() + ";" + ml.pmax.max.toString());
	}
}