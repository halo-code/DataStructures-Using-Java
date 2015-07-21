package com.data.structure.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * �ڽӱ�ʽ��ʾ��ͼ
 * 
 * @author liumiao
 * 
 */
public class Graph {

	public static final double INFINITY = Double.MAX_VALUE;

	public Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();

	/**
	 * ��ȡһ������
	 * 
	 * @param name
	 *            ���������
	 * @return ����
	 */
	public Vertex getVertex(String name) {
		Vertex v = vertexMap.get(name);
		if (v == null) {
			v = new Vertex(name);
			vertexMap.put(name, v);
		}
		return v;
	}

	/**
	 * ���һ����
	 * 
	 * @param sourceName
	 *            ��ʼ���������
	 * @param destName
	 *            Ŀ�Ķ��������
	 * @param cost
	 *            ����
	 */
	public void addEdge(String sourceName, String destName, double cost) {
		Vertex source = getVertex(sourceName);
		Vertex dest = getVertex(destName);
		source.adj.add(new Edge(dest, cost));
	}

	/**
	 * �ݹ�Ĵ�ӡ·��
	 * 
	 * @param dest
	 */
	private void printPath(Vertex dest) {
		if (dest.pre != null) {
			printPath(dest.pre);
			System.out.println("to");
		}
		System.out.println(dest.name);
	}

	/**
	 * ��ӡ����ΪdestName�Ķ����·��
	 * 
	 * @param destName
	 */
	public void printPath(String destName) {
		Vertex v = vertexMap.get(destName);
		if (v == null) {
			throw new NoSuchElementException();
		} else if (v.dist == Graph.INFINITY) {
			System.out.println(destName + "is unreachable");
		} else {
			printPath(v);
			System.out.println();
		}
	}

	private void clearAll() {
		for (Vertex v : vertexMap.values()) {
			v.reset();
		}
	}

	/**
	 * ��Ȩ���·������ ��������������㴦��ÿ��Ķ��� ����
	 * 
	 * @param startName
	 *            ��ʼ���������
	 */
	public void unwight(String startName) {
		clearAll();
		Vertex start = vertexMap.get(startName);
		if (start == null) {
			throw new NoSuchElementException();
		} else {

			Queue<Vertex> queue = new LinkedList<Vertex>();
			start.dist = 0;
			queue.add(start);

			while (!queue.isEmpty()) {

				Vertex vertex = queue.remove();

				for (Edge e : vertex.adj) {
					Vertex v = e.dest;
					// ֻ��û�м����·���Ķ��㸳ֵ���ɣ��������ص㣬Ҳ�ǲ�ͬ������·�����ĵط�
					if (v.dist == Graph.INFINITY) {
						v.dist = vertex.dist + 1;
						v.pre = vertex;
						queue.add(v);
					}
				}
			}

		}
	}

	/**
	 * �Ǹ�Ȩֵ�����·�����⣬Ҳ����Dijkstra�㷨 �������ȶ��� ����֧�֣�ÿ������ֻ��һ������
	 * 
	 * @param startName
	 */
	public void dijkstra(String startName) {
		Vertex start = vertexMap.get(startName);
		if (start == null) {
			throw new NoSuchElementException();
		} else {
			// ����Ϊʲô�洢����path�������Ƕ���
			PriorityQueue<Path> queue = new PriorityQueue<Path>();
			queue.add(new Path(start, 0));
			int nodeSeen = 0;

			while (!queue.isEmpty() && nodeSeen < vertexMap.size()) {

				Path path = queue.remove();
				Vertex v = path.dest;

				if (v.scratch == 1) {
					// �Ѿ�������������
					continue;
				}

				v.scratch = 1;
				nodeSeen++;

				for (Edge e : v.adj) {
					Vertex dest = e.dest;
					double tmpCost = e.cost + v.dist;
					if (tmpCost < dest.dist) {
						dest.dist = tmpCost;
						dest.pre = v;
						queue.add(new Path(dest, dest.dist));
					}
				}

			}
		}
	}

	/**
	 * �����޻�ͼ�����·�� �������� �и�Ȩֵ�ı���Ȼ��ȷ scratch��¼�������
	 * @throws GraphException 
	 */
	public void acyclic(String startName) throws GraphException {
		Vertex start = vertexMap.get(startName);
		if (start == null) {
			throw new NoSuchElementException();
		}

		else {
			clearAll();
			Queue<Vertex> q = new LinkedList<Vertex>();
			start.dist = 0;

			// �������
			Collection<Vertex> vertexSet = vertexMap.values();
			for (Vertex v : vertexSet) {
				for (Edge e : v.adj) {
					e.dest.scratch++;
				}
			}

			// �����Ϊ0�Ľڵ���������
			for (Vertex v : vertexSet) {
				if (v.scratch == 0) {
					q.add(v);
				}
			}

			int iterations = 0;
			for (iterations = 0; !q.isEmpty(); iterations++) {
				Vertex v = q.remove();

				for (Edge e : v.adj) {
					Vertex dest = e.dest;
					double cost = e.cost;

					if (--dest.scratch == 0) {
						q.add(dest);
					}

					
					//��������ͨ���������ʱ����Ϊ�������Ҳ��0
					if (v.dist == Graph.INFINITY) {
						continue;
					}

					if (dest.dist > v.dist + cost) {
						dest.dist = v.dist + cost;
						dest.pre = v;
					}
				}
			}

			if (iterations != vertexSet.size()) {
				throw new GraphException("Graph has a cycle");
			}

		}
	}

	private class GraphException extends Exception {

		public GraphException(String name) {
			super(name);
		}

	}

}
