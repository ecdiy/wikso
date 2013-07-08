package cn.net.dbi.test.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.net.dbi.MaxValue;
import cn.net.dbi.test.model.Factor;
import cn.net.dbi.test.model.FactorRelation;
import cn.net.dbi.test.repository.FactorRelationRepository;
import cn.net.dbi.test.repository.TFactorRepository;

@Component
public class TreeConvert {
	@Autowired
	TFactorRepository factorRepository;
	@Autowired
	FactorRelationRepository factorRelationRepository;

	public T getMaxRote(long schemeId) {
		return new MaxRote(schemeId);
	}

	public Tree getTreeFacotr(long schemeId) {
		return new Tree(schemeId);
	}

	public class T {
		int[][] matrix;

		public List<Factor> list;
		List<FactorRelation> relations;
		List<Factor> news = new LinkedList<Factor>();
		HashMap<Long, Factor> allFactor = new HashMap<>();

		int r = 80;
		int w = 800;
		int h = 800;
		public List<Factor> singleList = new LinkedList<Factor>();
		public LinkedList<LinkedList<Factor>> group = new LinkedList<LinkedList<Factor>>();
		int startX = 50, startY = 50;
		int space = 100;

		T(long schemeId) {
			list = factorRepository.findBySchemeId(schemeId);
			relations = factorRelationRepository.findBySchemeId(schemeId);
			single();
			while (true) {
				HashMap<Long, Factor> map = getGroup();
				if (map.isEmpty())
					break;
				LinkedList<Factor> s = new LinkedList<>();
				s.addAll(map.values());
				group.add(s);
			}
			// ---
			matrix = new int[list.size()][list.size()];
			for (FactorRelation fr : relations) {
				matrix[pos(fr.getFid1())][pos(fr.getFid2())] = 1;
			}
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < list.size(); j++) {
					System.out.print(matrix[i][j] + "\t");
				}
				System.out.println();
			}
			// --
			MaxValue mv = new MaxValue(matrix);

			System.out.println(mv.result.toString());
		}

		private int pos(long fid) {
			for (int i = 0; i < list.size(); i++) {
				if (fid == list.get(i).getId())
					return i;
			}
			return -1;
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

		public void circle(List<Factor> list, int r) {
			if (startX + 2 * r > w) {
				startX = 50;
				startY += 2 * r;
			}
			for (int i = 0; i < list.size(); i++) {
				Factor f = list.get(i);
				f.setX(startX
						+ (int) (r * (1 + Math.sin(Math.PI * i * 2
								/ list.size()))));
				f.setY(startY
						+ (int) (r * (1 - Math.cos(Math.PI * i * 2
								/ list.size()))));
			}
		}
	}

	public class MaxRote extends T {

		MaxRote(long schemeId) {
			super(schemeId);
		}

	}

	public class Tree extends T {

		Tree(long schemeId) {
			super(schemeId);
		}

		public void convert(Factor f) {

			// Factor maxRelationNode = factorRepository
			// .findByMaxCountRelation(schemeId);
			// maxRelationNode.setX(w / 2);
			// maxRelationNode.setY(h / 2);
			// convert(maxRelationNode);

			// singleMap.remove(f.getId());
			// // ==TODO
			// int node = 0;
			// for (FactorRelation fr : relations) {
			// if (singleMap.containsKey(fr.getFid1())
			// && fr.getFid2() == f.getId()) {
			// f.setX(50 + (int) (250 * (1 + Math.sin(Math.PI * 2 * node
			// / list.size()))));
			// f.setY(50 + (int) (250 * (1 - Math.cos(Math.PI * 2 * node
			// / list.size()))));
			// }
			// if ((singleMap.containsKey(fr.getFid2()) && fr.getFid1() == f
			// .getId())) {}
			// }

			// --
			news.add(f);
		}

	}

}
