package cn.net.dbi.test.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.net.dbi.MatrixLongest;
import cn.net.dbi.test.model.Factor;
import cn.net.dbi.test.model.FactorRelation;
import cn.net.dbi.test.repository.FactorRelationRepository;
import cn.net.dbi.test.repository.TFactorRepository;

@Component
public class PostionConvert {
	@Autowired
	TFactorRepository factorRepository;
	@Autowired
	FactorRelationRepository factorRelationRepository;

	public Circle getTreeFacotr(long schemeId) {
		return new Circle(schemeId);
	}

	public T getMatrix(long schemeId) {
		return new Matrix(schemeId);
	}

	public abstract class T {

		public List<Factor> list;
		List<FactorRelation> relations;
		HashMap<Long, Factor> allFactor = new HashMap<>();

		int r = 80;
		int w = 800;

		public List<Factor> singleList = new LinkedList<Factor>();
		public LinkedList<LinkedList<Factor>> group = new LinkedList<LinkedList<Factor>>();
		int startX = 50;
		public int startY = 50;
		int space = 100;

		T(long schemeId) {
			list = factorRepository.findBySchemeId(schemeId);
			relations = factorRelationRepository.findBySchemeId(schemeId);
			single();
			startY += r;
			while (true) {
				HashMap<Long, Factor> map = getGroup();
				if (map.isEmpty())
					break;
				LinkedList<Factor> s = new LinkedList<>();
				s.addAll(map.values());
				tran(s);
				group.add(s);
			}
		}

		private void single() {
			HashMap<Long, Factor> singleMap = new HashMap<>();
			for (Factor f : list) {
				f.setX(-1);
				f.setY(-1);
				singleMap.put(f.getId(), f);
				allFactor.put(f.getId(), f);
			}
			for (FactorRelation fr : relations) {
				if (singleMap.containsKey(fr.getFid1()))
					singleMap.remove(fr.getFid1());
				if (singleMap.containsKey(fr.getFid2()))
					singleMap.remove(fr.getFid2());
			}
			singleList.addAll(singleMap.values());
			if (singleList.size() > 0) {
				for (int i = 0; i < singleList.size(); i++) {
					startX += space;
					if (startX > w) {
						startX = 50;
						startY += space;
					}
					singleList.get(i).setX(startX);
					singleList.get(i).setY(startY);
				}
			}
		}

		HashMap<Long, Factor> getGroup() {
			HashMap<Long, Factor> map = new HashMap<>();
			getGroupx(map);
			return map;
		}

		HashMap<Long, Factor> getGroupx(HashMap<Long, Factor> map) {
			int initSize = map.size();
			if (relations.size() > 0) {
				for (FactorRelation fst : relations) {
					if (map.size() == 0
							&& allFactor.get(fst.getFid1()).getX() == -1) {
						Factor f = allFactor.get(fst.getFid1());
						f.setX(0);
						map.put(fst.getFid1(), f);
						Factor f2 = allFactor.get(fst.getFid2());
						f2.setX(0);
						map.put(fst.getFid2(), f2);
					} else {
						if (map.containsKey(fst.getFid1())
								&& !map.containsKey(fst.getFid2())) {
							Factor f = allFactor.get(fst.getFid2());
							f.setX(0);
							map.put(fst.getFid2(), f);
						}
						if (map.containsKey(fst.getFid2())
								&& !map.containsKey(fst.getFid1())) {
							Factor f = allFactor.get(fst.getFid1());
							f.setX(0);
							map.put(fst.getFid1(), f);
						}
					}
				}
			}
			if (map.size() == initSize)
				return map;
			return getGroupx(map);
		}

		abstract void tran(List<Factor> list);
	}

	public class Matrix extends T {

		Matrix(long schemeId) {
			super(schemeId);
		}

		int pos(long fid, List<Factor> list) {
			for (int i = 0; i < list.size(); i++) {
				if (fid == list.get(i).getId())
					return i;
			}
			return -1;
		}

		@Override
		void tran(List<Factor> list) {
			int[][] matrix = new int[list.size()][list.size()];
			for (FactorRelation fr : relations) {
				int p1 = pos(fr.getFid1(), list), p2 = pos(fr.getFid2(), list);
				if (p1 > -1 && p2 > -1) {
					matrix[p1][p2] = 1;
					matrix[p2][p1] = 1;
				}
			}
			MatrixLongest ml = new MatrixLongest(matrix);

			int node = 0;
			for (int i : ml.pmax.max) {
				node++;
				Factor f = list.get(i);
				startY += r;
				f.setX(w / 2 + r * (node % 2));
				f.setY(startY);
			}

			for (Factor f : list) {
				if (f.getX() == 0) {
					int pos = pos(f.getId(), list);
					HashSet<Integer> set = ml.factors[pos].son;
					if (set != null) {
						int fst = (int) set.toArray()[0];
						f.setX(list.get(fst).getX() + r);
						f.setY(list.get(fst).getY());
					}
				}
			}

		}
	}

	public class Circle extends T {

		Circle(long schemeId) {
			super(schemeId);
		}

		public void tran(List<Factor> list) {
			int R = r;
			if (list.size() > 10) {
				startX = 50;
				R = 300;
			}
			if (startX + 2 * r > w) {
				startX = 50;
				startY += 2 * r;
			}
			for (int i = 0; i < list.size(); i++) {
				Factor f = list.get(i);
				f.setX(startX
						+ (int) (R * (1 + Math.sin(Math.PI * i * 2
								/ list.size()))));
				f.setY(startY
						+ (int) (R * (1 - Math.cos(Math.PI * i * 2
								/ list.size()))));
			}
			startY += 2 * R + r;
		}
	}

}
